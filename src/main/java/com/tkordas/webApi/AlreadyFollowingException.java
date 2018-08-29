package com.tkordas.webApi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyFollowingException extends RuntimeException {

  public AlreadyFollowingException() {
    super("You are already following this user!");
  }

}
