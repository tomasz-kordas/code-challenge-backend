package com.tkordas.model;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {

  @Id
  @GeneratedValue
  private Long id;

  private String username;

  public User() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
