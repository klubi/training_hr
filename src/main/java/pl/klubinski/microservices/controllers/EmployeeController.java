package pl.klubinski.microservices.controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.klubinski.microservices.model.Employee;
import pl.klubinski.microservices.service.EmployeeService;

@RestController
@RequestMapping(value = "employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping(value = "{id}/subordinates")
  public List<Employee> getSubortinates(@PathVariable UUID id) {
    return employeeService.getSubordinates(id);
  }

  @GetMapping
  public List<Employee> getEmployees(@RequestParam(required = false) UUID departmentId) {
    return employeeService.getEmployees(departmentId);
  }
}
