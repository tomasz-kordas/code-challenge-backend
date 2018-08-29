package com.tkordas.model;

import javax.persistence.*;

@Entity
@Table(name = "FOLLOWERS")
public class Follower {

  @Id
  @GeneratedValue
  private Long id;

  private Long target;

  private Long follower;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTarget() {
    return target;
  }

  public void setTarget(Long target) {
    this.target = target;
  }

  public Long getFollower() {
      return follower;
  }

  public void setFollower(Long follower) {
    this.follower = follower;
  }
}
