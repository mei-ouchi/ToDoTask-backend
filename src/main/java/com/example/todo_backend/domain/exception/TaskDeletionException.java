package com.example.todo_backend.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TaskDeletionException extends RuntimeException {
  public TaskDeletionException(String message) {
    super(message);
  }
}
