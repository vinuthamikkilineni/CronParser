package org.cronparser.helper.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonUtils {

  static Logger logger = LogManager.getLogger(CommonUtils.class);

  private CommonUtils() {
  }

  public static int parseValue(String eval) {
    int value = 0;
    try {
      if (!eval.isEmpty() && Character.isDigit(eval.charAt(0))) {
        value = Integer.parseInt(eval);
      }
    } catch (NumberFormatException e) {
        logger.error("error occurred while parsing the value {}", eval, e);
        throw e;
    }
    return value;
  }

}
