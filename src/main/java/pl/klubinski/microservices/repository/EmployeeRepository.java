package pl.klubinski.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klubinski.microservices.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
