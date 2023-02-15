package com.github.dudekmat.complaintservice.shared;

public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }
}
