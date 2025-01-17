package org.cronparser.service.impl;

import java.util.HashMap;
import org.cronparser.exception.InvalidCronExpressionException;
import org.cronparser.model.ApplicationConstants;
import org.cronparser.model.enums.Field;
import org.cronparser.service.CronParserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cronparser.helper.BaseExpressionHelper;

public class CronParserServiceImpl implements CronParserService {

  private static final Logger logger = LogManager.getLogger(CronParserServiceImpl.class);

  BaseExpressionHelper expressionHelper;

  public CronParserServiceImpl(BaseExpressionHelper expressionHelper) {
     this.expressionHelper = expressionHelper;
  }


  @Override
  public void parseExpression(String expression) {

    String[] expressionParams = expression.split(" ");
    String[] evaluations = new String[expressionParams.length];

    try {
      if (checkValidExpressionParams(expressionParams)) {
        evaluations[0] = expressionHelper.evaluateField(expressionParams[0], ApplicationConstants.MINUTE_LOWER_LIMIT,ApplicationConstants.MINUTE_UPPER_LIMIT,
            Field.MINUTE);
        evaluations[1] = expressionHelper.evaluateField(expressionParams[1], ApplicationConstants.HOUR_LOWER_LIMIT,ApplicationConstants.HOUR_UPPER_LIMIT,
            Field.HOUR);
        evaluations[2] = expressionHelper.evaluateField(expressionParams[2], ApplicationConstants.DAY_LOWER_LIMIT,ApplicationConstants.DAY_UPPER_LIMIT,
            Field.DAY);
        evaluations[3] = expressionHelper.evaluateField(expressionParams[3], ApplicationConstants.MONTH_LOWER_LIMIT,ApplicationConstants.MONTH_UPPER_LIMIT,
            Field.MONTH);
        evaluations[4] = expressionHelper.evaluateField(expressionParams[4], ApplicationConstants.WEEK_LOWER_LIMIT,ApplicationConstants.WEEK_UPPER_LIMIT,
            Field.WEEK);
        evaluations[5] = expressionParams[5];
        printExpressionSchedule(evaluations);
      }
      else {
        logger.error("Invalid expression: {}",expression);
        throw new InvalidCronExpressionException("",String.format("Invalid expression:%s expected 6 fields" , expression));
      }
    }
    catch (Exception e) {
      logger.error("Error occurred while parsing cron expression:",e);
      throw e;
    }


  }

  private boolean checkValidExpressionParams(String[] expressionParams) {
    return expressionParams.length == 6;
  }

  void printExpressionSchedule(String[] evaluations) {
    System.out.println("*****************************************************");
    System.out.println("minute        "+ evaluations[0]);
    System.out.println("hour          "+ evaluations[1]);
    System.out.println("day of month  "+ evaluations[2]);
    System.out.println("month         "+ evaluations[3]);
    System.out.println("day of week   "+ evaluations[4]);
    System.out.println("command       "+ evaluations[5]);
    System.out.println("*****************************************************");
  }
}
