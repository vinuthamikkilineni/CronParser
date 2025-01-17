package org.cronparser.helper.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.cronparser.exception.InvalidTokenException;
import org.cronparser.model.enums.Field;
import org.junit.jupiter.api.Test;

public class HyphenEvaluatorTests {

  @Test
  public void testEvaluateSuccess() {
    String[] result = HyphenEvaluator.evaluate("1-2", 1, 2, Field.MINUTE);
    assertNotNull(result);
    assertEquals(2, result.length);
  }

  @Test
  public void testEvaluateFailure() {
    Exception exception =
        assertThrows(
            InvalidTokenException.class, () -> HyphenEvaluator.evaluate("1-", 1, 2, Field.MINUTE));
    assertNotNull(exception);
  }

  @Test
  public void testEvaluateOutsideRange() {
    Exception exception =
        assertThrows(
            InvalidTokenException.class, () -> HyphenEvaluator.evaluate("1-3", 1, 2, Field.MINUTE));
    assertNotNull(exception);
  }

}
