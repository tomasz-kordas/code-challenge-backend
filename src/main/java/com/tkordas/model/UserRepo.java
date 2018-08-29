package com.tkordas.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends CrudRepository<User, Long> {

  @Query("select u from User u where u.username=:username ")
  User getUserByUsername(@Param("username") String username);

}
