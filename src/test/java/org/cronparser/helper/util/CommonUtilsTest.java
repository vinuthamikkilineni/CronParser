package org.cronparser.helper.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CommonUtilsTest {

  @Test
  public void testParseSuccess() {
    int val = CommonUtils.parseValue("1");
    assertEquals(1, val);
  }

  @Test
  public void testParseFailure() {
    Exception exception =
        assertThrows(
            NumberFormatException.class, () -> CommonUtils.parseValue("1A"));
    assertNotNull(exception);
  }

}
