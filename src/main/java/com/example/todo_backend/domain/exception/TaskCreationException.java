package com.example.todo_backend.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TaskCreationException extends RuntimeException {
  public TaskCreationException(String message) {
    super(message);
  }
}
