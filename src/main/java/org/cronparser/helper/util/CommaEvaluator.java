package org.cronparser.helper.util;

import java.util.ArrayList;
import java.util.List;
import org.cronparser.exception.InvalidTokenException;
import org.cronparser.model.enums.Field;

public class CommaEvaluator {
  private CommaEvaluator() {}


  public static String[] evaluate(String expression, int lowerLimit, int upperLimit, Field field) throws InvalidTokenException {
    String[] values = expression.split(",");
    String[] result = new String[values.length];
    int index = 0;
    if(expression.charAt(expression.length()-1) == ',') {
      throw new InvalidTokenException(
          String.format("Field =%s , invalid expression=%s",
              field.name(), expression));
    }
    try {
      for (String eval : values) {
        int current = CommonUtils.parseValue(eval);
        if (current <= upperLimit && current >= lowerLimit ) {
          result[index++] = eval;
        }
        else {
          throw new InvalidTokenException(
              String.format("Field =%s ,%d is out of range. Expected range is %d and %d",
                  field.name(), current, lowerLimit, upperLimit));
        }
      }
    }
    catch (Exception e) {
      throw new InvalidTokenException(e.getMessage());
    }
    return result;
  }

}
