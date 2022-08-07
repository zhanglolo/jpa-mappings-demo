package org.example;

import javax.persistence.*;

@Entity
public class Employee {

  @Id
  @GeneratedValue
  private Integer id;

  private String email;

  private String firstName;

  private String lastName;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "employee_account_id")
  private Account account;

  protected Employee() {
  }

  public Employee(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }
}
