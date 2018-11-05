package pl.klubinski.microservices;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import pl.klubinski.microservices.model.Department;
import pl.klubinski.microservices.model.Employee;
import pl.klubinski.microservices.model.Role;
import pl.klubinski.microservices.repository.DepartmentRepository;
import pl.klubinski.microservices.repository.EmployeeRepository;
import pl.klubinski.microservices.repository.RoleRepository;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SpringBootApplication
public class MicroservicesApplication {

  @Autowired
  @Lazy
  EmployeeRepository employeeRepository;

  @Autowired
  @Lazy
  DepartmentRepository departmentRepository;

  @Autowired
  @Lazy
  RoleRepository roleRepository;

  public static void main(String[] args) {
    SpringApplication.run(MicroservicesApplication.class, args);
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("pl.klubinski.microservices.controllers"))
        .paths(regex("/.*"))
        .build();
  }

  @EventListener(ApplicationReadyEvent.class)
  public void seedDB() {
    for (int i = 0; i < 20; i++) {
      Role role = new Role();
      role = roleRepository.saveAndFlush(role);
      Department department = new Department();
      department.setId(UUID.randomUUID());
      department = departmentRepository.saveAndFlush(department);
      Employee employee = new Employee();
      employee.setId(UUID.randomUUID());
      employee.setDepartment(department);
      employee.setRoles(Collections.singletonList(role));
      employeeRepository.saveAndFlush(employee);
    }
  }
}

