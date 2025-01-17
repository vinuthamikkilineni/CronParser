package org.cronparser.helper.util;

import java.util.ArrayList;
import java.util.List;
import org.cronparser.exception.InvalidTokenException;
import org.cronparser.model.enums.Field;

public class HyphenEvaluator {

  private HyphenEvaluator() {}

  public static String[] evaluate(String expression,int lowerLimit, int upperLimit, Field field) throws InvalidTokenException {
    if(expression.charAt(expression.length()-1) == '/') {
      throw new InvalidTokenException(
          String.format("Field =%s , invalid expression=%s",
              field.name(), expression));
    }
    String[] values = expression.split("-");
    List<String> result = new ArrayList<>();
    try {
        if(values.length != 2) {
          throw new InvalidTokenException(String.format("Field:%s Invalid expression=%s", field.name(), expression));
        }
        int start = CommonUtils.parseValue(values[0]);
        int end = CommonUtils.parseValue(values[1]);
        if(start < lowerLimit || end > upperLimit || start > end) {
          throw new InvalidTokenException(String.format("Field =%s ,%d or %d is out of range. Expected range is %d and %d", field.name(), start, end,lowerLimit,upperLimit));
        }
        for(int i = start; i <= end; i++) {
          result.add(String.valueOf(i));
        }
    }
    catch (Exception e) {
      throw new InvalidTokenException(e.getMessage());
    }
    return result.toArray(new String[0]);
  }



}
