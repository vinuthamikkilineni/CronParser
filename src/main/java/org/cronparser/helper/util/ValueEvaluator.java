package org.cronparser.helper.util;

import org.cronparser.exception.InvalidTokenException;

public class ValueEvaluator {

  private ValueEvaluator() {
  }

  public static String[] evaluate(String expression, int lowerBound, int upperBound)
      throws InvalidTokenException {
    try {
      int current = CommonUtils.parseValue(expression);
      if (current < lowerBound || current > upperBound) {
        throw new InvalidTokenException(
            String.format("%d is out of range. Expected range is %d and %d",
                current, lowerBound, upperBound));
      }
    } catch (NumberFormatException e) {
      throw new InvalidTokenException(
          String.format("invalid character encountered: %s", e.getMessage()));
    }
    return new String[]{expression};
  }

}
