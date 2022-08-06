package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {

  @Id
  @GeneratedValue
  private Integer id;

  private String num;

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
}
