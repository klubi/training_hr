package pl.klubinski.microservices.model;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Employee {

  private String firstName;
  private String lastName;
  private List<Department> departments;
  private List<Role> roles;
}
