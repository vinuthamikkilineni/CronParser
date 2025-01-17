package org.cronparser.helper.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.cronparser.exception.InvalidTokenException;
import org.cronparser.model.enums.Field;
import org.junit.jupiter.api.Test;

public class AsterickEvaluatorTest {

  @Test
  public void testEvaluateSuccess() {
    String[] result = AsterickEvaluator.evaluate("*", 1, 2, Field.MINUTE);
    assertNotNull(result);
    assertEquals(2, result.length);
  }

  @Test
  public void testEvaluateFailure() {

    Exception exception =
        assertThrows(
            InvalidTokenException.class,
            () -> AsterickEvaluator.evaluate("**", 1, 2, Field.MINUTE));
    assertNotNull(exception);
  }
}
