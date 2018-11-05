package pl.klubinski.microservices.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
@Table(name = "employees")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String firstName;
  @Column
  private String lastName;

  @OneToOne(targetEntity = Department.class)
  @JoinColumn(name = "id")
  private Department department;

  @OneToMany(targetEntity = Role.class)
  private List<Role> roles;

  @OneToOne(targetEntity = Employee.class)
  @JoinColumn(name = "id")
  private Employee manager;
}
