package org.cronparser.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.cronparser.exception.InvalidCronExpressionException;
import org.cronparser.exception.InvalidTokenException;
import org.cronparser.helper.BaseExpressionHelper;
import org.cronparser.model.enums.Field;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CronParserServiceImplTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  @InjectMocks
  private CronParserServiceImpl cronParserService;
  @Mock
  private BaseExpressionHelper baseExpressionHelper;

  @Test
  public void testCronParserSuccess() {
    System.setOut(new PrintStream(outContent));
    when(baseExpressionHelper.evaluateField(anyString(), anyInt(), anyInt(),
        any(Field.class))).thenReturn("0").thenReturn("0").thenReturn("0").thenReturn("0")
        .thenReturn("0");
    cronParserService.parseExpression("0 0 0 0 0 0");
    assertNotNull(outContent.toString());
    assertTrue(outContent.toString().contains("minute        0"));
    assertTrue(outContent.toString().contains("hour          0"));
    assertTrue(outContent.toString().contains("day of month  0"));
    assertTrue(outContent.toString().contains("month         0"));
    assertTrue(outContent.toString().contains("day of week   0"));
    assertTrue(outContent.toString().contains("command       0"));
    System.setOut(originalOut);
  }


  @Test
  public void testCronParserInvalidLength() {
    Exception exception =
        assertThrows(
            InvalidCronExpressionException.class,
            () -> cronParserService.parseExpression("0 0 0 0 0"));
    assertNotNull(exception);
  }

  @Test
  public void testCronParserFailedInvalidExpression() {
    when(baseExpressionHelper.evaluateField(anyString(), anyInt(), anyInt(),
        any(Field.class))).thenReturn("0").thenReturn("0").thenReturn("0").thenReturn("0")
        .thenThrow(
            new InvalidTokenException("Invalid expression"));
    Exception exception =
        assertThrows(
            InvalidTokenException.class, () -> cronParserService.parseExpression("0 0 0 0 0 0"));
    assertNotNull(exception);
  }


}
