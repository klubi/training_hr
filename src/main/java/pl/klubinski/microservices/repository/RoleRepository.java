package pl.klubinski.microservices.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.klubinski.microservices.model.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {

}
