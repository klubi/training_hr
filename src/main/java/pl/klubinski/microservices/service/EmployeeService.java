package pl.klubinski.microservices.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klubinski.microservices.model.Employee;
import pl.klubinski.microservices.repository.EmployeeRepository;

@Service
public class EmployeeService {

  private EmployeeRepository employeeRepository;

  @Autowired
  EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public List<Employee> getSubordinates(Long id) {
    return employeeRepository.findAll().stream().filter(e -> e.getManager().getId().equals(id)).collect(Collectors.toList());
  }

  public List<Employee> getEmployees(Long departmentId) {
    return employeeRepository.findAll().stream().filter(e -> e.getDepartment().getId().equals(departmentId)).collect(Collectors.toList());
  }
}