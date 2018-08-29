package com.tkordas.webApi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyFollowingListException extends RuntimeException {

  public EmptyFollowingListException() {
    super("You are not following any user!");
  }

}
