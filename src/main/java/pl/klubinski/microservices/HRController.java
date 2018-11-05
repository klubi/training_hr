package pl.klubinski.microservices;

import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.klubinski.microservices.model.Employee;

@RestController
@RequestMapping("/hr")
public class HRController {


  @GetMapping
  public List<Employee> getEmployees() {
    System.out.println("JEST!!!");
    return Collections.emptyList();
  }
}
