package org.cronparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cronparser.helper.BaseExpressionHelper;
import org.cronparser.service.CronParserService;
import org.cronparser.service.impl.CronParserServiceImpl;

public class CronParser {
  private static final Logger logger = LogManager.getLogger(CronParser.class);

  public static void main(String[] args) {

    if (args.length != 1) {
      logger.error("Usage: java CronParser \"<cronExpression>\"");
    }
    try {
      CronParserService cronParserService = new CronParserServiceImpl(new BaseExpressionHelper());
      cronParserService.parseExpression(args[0]);
    }
    catch (Exception e) {
      logger.error(e);
    }

  }
}