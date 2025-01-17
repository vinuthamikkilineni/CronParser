package org.cronparser.helper;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cronparser.exception.InvalidCronExpressionException;
import org.cronparser.helper.util.AsterickEvaluator;
import org.cronparser.helper.util.CommaEvaluator;
import org.cronparser.helper.util.ForwardSlashEvaluator;
import org.cronparser.helper.util.HyphenEvaluator;
import org.cronparser.helper.util.ValueEvaluator;
import org.cronparser.model.enums.Field;

public class BaseExpressionHelper {

  private static final Logger logger = LogManager.getLogger(BaseExpressionHelper.class);

  public String evaluateField(String expression, int lowerLimit, int upperLimit, Field field) {
    try {
      String[] result;
      if (expression.isEmpty()) {
        logger.error("Empty expression in {}", field.name());
        throw new InvalidCronExpressionException(field.name(), "Invalid length");
      }

      if (expression.contains("/")) {
        result = ForwardSlashEvaluator.evaluate(expression, lowerLimit, upperLimit, field);
      } else if (expression.charAt(0) == '*') {
        result = AsterickEvaluator.evaluate(expression, lowerLimit, upperLimit, field);
      } else if (expression.contains("-")) {
        result = HyphenEvaluator.evaluate(expression, lowerLimit, upperLimit, field);
      } else if (expression.contains(",")) {
        result = CommaEvaluator.evaluate(expression, lowerLimit, upperLimit, field);
      } else if (Character.isDigit(expression.charAt(0))) {
        result = ValueEvaluator.evaluate(expression, lowerLimit, upperLimit);
      } else {
        throw new InvalidCronExpressionException(field.name(), "Invalid Characters");
      }
      return Arrays.toString(result);
    } catch (Exception e) {
      logger.error("error occurred while evaluating expression in={}, field={}", expression,
          field.name());
      throw new InvalidCronExpressionException(field.name(), e.getMessage());
    }
  }
}
