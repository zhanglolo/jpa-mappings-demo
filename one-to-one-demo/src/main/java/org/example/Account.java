package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Account {

  @Id
  @GeneratedValue
  private Integer id;

  private String num;

  @OneToOne(mappedBy = "account")
  private Employee employee;

  protected Account() {
  }

  public Account(String num) {
    this.num = num;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNum() {
    return num;
  }

  public void setNum(String number) {
    this.num = number;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
}
