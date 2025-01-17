package org.cronparser.helper.util;

import java.util.ArrayList;
import java.util.List;
import org.cronparser.exception.InvalidTokenException;
import org.cronparser.model.enums.Field;

public class ForwardSlashEvaluator {

  private ForwardSlashEvaluator() {

  }

  public static String[] evaluate(String expression, int lowerLimit, int upperLimit, Field field) throws InvalidTokenException {
    if(expression.charAt(expression.length()-1) == '/') {
      throw new InvalidTokenException(
          String.format("Field =%s , invalid expression=%s",
              field.name(), expression));
    }
    String[] values = expression.split("/");
    List<String> result = new ArrayList<>();
    try {
      if(values.length != 2) {
        throw new InvalidTokenException(String.format("Field:%s Invalid expression=%s", field.name(), expression));
      }
      int current = CommonUtils.parseValue(values[1]);
      if(current < lowerLimit || current > upperLimit ) {
        throw new InvalidTokenException(String.format("Field =%s ,%d  is out of range. Expected range is %d and %d", field.name(), current,lowerLimit,upperLimit));
      }
      int increment=0;
      if(values[0].charAt(0) == '*' && values[0].length() == 1) {
        increment = lowerLimit;
      }
      else if(Character.isDigit(values[0].charAt(0))) {
        int schedule = CommonUtils.parseValue(values[0]);
        if(schedule!=0 && (schedule < lowerLimit || schedule > upperLimit)) {
          throw new InvalidTokenException(String.format("Field =%s ,%d  is out of range. Expected range is %d and %d", field.name(), schedule,lowerLimit,upperLimit));
        }
        if(schedule == 0) {
          increment = lowerLimit;
        }
        else {
          increment += schedule;
        }
      }
      else {
        throw new InvalidTokenException(String.format("Invalid token occurrence in field: %s  expression:%s ", field.name(), values[0]));
      }
      for(int i = increment; i <= upperLimit; i+=current) {
        result.add(String.valueOf(i));
      }
    }
    catch (Exception e) {
      throw new InvalidTokenException(e.getMessage());
    }
    return result.toArray(new String[0]);
  }

}
