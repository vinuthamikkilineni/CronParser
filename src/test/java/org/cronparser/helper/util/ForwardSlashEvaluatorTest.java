package org.cronparser.helper.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.cronparser.exception.InvalidTokenException;
import org.cronparser.model.enums.Field;
import org.junit.jupiter.api.Test;

public class ForwardSlashEvaluatorTest {

  @Test
  public void testEvaluateSuccess() {
    String[] result = ForwardSlashEvaluator.evaluate("1/2", 1, 2, Field.MINUTE);
    assertNotNull(result);
    assertEquals(1, result.length);
  }

  @Test
  public void testEvaluateFailure() {
    Exception exception =
        assertThrows(
            InvalidTokenException.class,
            () -> ForwardSlashEvaluator.evaluate("1/", 1, 2, Field.MINUTE));
    assertNotNull(exception);
  }

  @Test
  public void testEvaluateOutsideRange() {
    Exception exception =
        assertThrows(
            InvalidTokenException.class,
            () -> ForwardSlashEvaluator.evaluate("1/3", 1, 2, Field.MINUTE));
    assertNotNull(exception);
  }


  @Test
  public void testEvaluateAsterick() {
    String[] result = ForwardSlashEvaluator.evaluate("*/1", 1, 2, Field.MINUTE);
    assertNotNull(result);
    assertEquals(2, result.length);
  }

  @Test
  public void testEvaluateZero() {
    String[] result = ForwardSlashEvaluator.evaluate("0/1", 0, 2, Field.MINUTE);
    assertNotNull(result);
    assertEquals(3, result.length);
  }

}
