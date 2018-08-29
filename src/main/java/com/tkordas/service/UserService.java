package com.tkordas.service;

import com.tkordas.model.User;
import com.tkordas.model.UserRepo;
import com.tkordas.webApi.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

  private final UserRepo userRepo;

  @Autowired
  public UserService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public User createUser(String username) {
    User user = new User();
    user.setUsername(username);
    return userRepo.save(user);
  }

  public User getUserByUsername(String username) {
    return userRepo.getUserByUsername(username);
  }

  public User validateAndGetUser(String username) {
    User user = getUserByUsername(username);
    if (user == null) {
      throw new UserNotFoundException(username);
    }
    return user;
  }


}
