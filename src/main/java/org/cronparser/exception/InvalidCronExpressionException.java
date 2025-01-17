package org.cronparser.exception;

import lombok.Getter;

public class InvalidCronExpressionException extends RuntimeException {

  public InvalidCronExpressionException(String field, String message) {
    this.field = field;
    this.message = message;
  }

  @Getter
  private String field;
  private String message;

  @Override
  public String getMessage() {
    return message;
  }

}
