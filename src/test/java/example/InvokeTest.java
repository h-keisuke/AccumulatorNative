package example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.amazonaws.services.lambda.runtime.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class InvokeTest {
  private static final Logger logger = LoggerFactory.getLogger(InvokeTest.class);

  @Test
  void invokeTest() {
    logger.info("Invoke TEST");
    List<Integer> event = List.of(10, 20, 30);
    Context context = new TestContext();
    Accumulator handler = new Accumulator();
    Integer result = handler.handleRequest(event, context);
    assertEquals(60, result);
  }

}
