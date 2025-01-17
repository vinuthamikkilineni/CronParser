package org.cronparser.exception;

public class InvalidTokenException extends RuntimeException {
  private String message;
  public InvalidTokenException(String message){
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

}
