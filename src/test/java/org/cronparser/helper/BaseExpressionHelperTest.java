package org.cronparser.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.cronparser.exception.InvalidCronExpressionException;
import org.cronparser.exception.InvalidTokenException;
import org.cronparser.helper.util.ValueEvaluator;
import org.cronparser.model.enums.Field;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BaseExpressionHelperTest {

  @InjectMocks
  private BaseExpressionHelper baseExpressionHelper;

  @Test
  public void  testAsterick() {
    String data = baseExpressionHelper.evaluateField("*",1,2, Field.MINUTE);
    assertEquals(data,"[1, 2]");
  }

  @Test
  public void  testSlash() {
    String data = baseExpressionHelper.evaluateField("*/2",1,4, Field.MINUTE);
    assertEquals(data,"[1, 3]");
  }

  @Test
  public void  testHyphen() {
    String data = baseExpressionHelper.evaluateField("1-2",1,2, Field.MINUTE);
    assertEquals(data,"[1, 2]");
  }

  @Test
  public void  testInvalidChar() {
    Exception exception =
        assertThrows(
            InvalidCronExpressionException.class, () -> baseExpressionHelper.evaluateField("AST",1,4, Field.MINUTE));
    assertNotNull(exception);
  }

  @Test
  public void  testComma() {
    String data = baseExpressionHelper.evaluateField("1,2",1,2, Field.MINUTE);
    assertEquals(data,"[1, 2]");
  }


}
