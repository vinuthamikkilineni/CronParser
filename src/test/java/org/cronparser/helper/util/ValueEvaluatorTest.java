package org.cronparser.helper.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.cronparser.exception.InvalidTokenException;
import org.cronparser.model.enums.Field;
import org.junit.jupiter.api.Test;

public class ValueEvaluatorTest {

  @Test
  public void testEvaluateSuccess() {
    String[] result = ValueEvaluator.evaluate("1", 1, 2);
    assertNotNull(result);
    assertEquals(1, result.length);
  }

  @Test
  public void testEvaluateFailure() {
    Exception exception =
        assertThrows(
            InvalidTokenException.class, () -> ValueEvaluator.evaluate("23S", 1, 2));
    assertNotNull(exception);
  }

  @Test
  public void testEvaluateOutsideRange() {
    Exception exception =
        assertThrows(
            InvalidTokenException.class, () -> HyphenEvaluator.evaluate("3", 1, 2, Field.MINUTE));
    assertNotNull(exception);
  }

}
