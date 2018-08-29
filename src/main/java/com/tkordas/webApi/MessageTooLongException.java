package com.tkordas.webApi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MessageTooLongException extends RuntimeException {

  public MessageTooLongException(int maxLength) {
    super("Message cannot be longer than " + maxLength + " characters");
  }

}
