package pl.klubinski.microservices.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.klubinski.microservices.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {

}
