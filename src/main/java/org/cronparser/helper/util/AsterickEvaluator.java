package org.cronparser.helper.util;

import java.util.ArrayList;
import java.util.List;
import org.cronparser.exception.InvalidTokenException;
import org.cronparser.model.enums.Field;

public class AsterickEvaluator {


  private AsterickEvaluator() {}

  public static String[] evaluate(String expression,int lowerLimit, int upperLimit, Field field) throws InvalidTokenException {
    if(expression == null || expression.length() != 1) {
      throw new InvalidTokenException(String.format("Field:%s Invalid expression=%s", field.name(), expression));
    }
    List<String> result = new ArrayList<>();
    for(int i = lowerLimit; i <= upperLimit; i++) {
      result.add(String.valueOf(i));
    }
    return result.toArray(new String[0]);
  }

}
