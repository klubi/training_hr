package pl.klubinski.microservices;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.klubinski.microservices.model.Employee;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeesControllerIT {

  @Autowired
  MockMvc mockMvc;
  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void shouldReturnAllEmployees() throws Exception {
    mockMvc
        .perform(get("/employees"))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldGetAllEmployeesForManager() throws Exception {
    MvcResult result = mockMvc
        .perform(get("/employees"))
        .andExpect(status().isOk()).andReturn();

    Employee[] employees = objectMapper.readValue(result.getResponse().getContentAsString(), Employee[].class);
    Employee manager = Arrays.stream(employees).filter(e -> e.getManagerId() == null).findAny().orElseThrow(() -> new IllegalStateException("No manager!"));

    mockMvc.perform(get("/employees/" + manager.getId().toString() + "/subordinates"))
        .andExpect(status().isOk())
        .andExpect(content().string(not(containsString("\"manager\": null"))));

  }
}
