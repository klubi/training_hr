package pl.klubinski.microservices.model;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column
  private String firstName;
  @Column
  private String lastName;

  @OneToOne(targetEntity = Department.class)
  @JoinColumn(name = "department_id")
  private Department department;

  @OneToMany(targetEntity = Role.class)
  private List<Role> roles;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "manager_id", insertable = false, updatable = false)
  private Employee manager;
}
