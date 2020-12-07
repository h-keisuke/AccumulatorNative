package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class Accumulator implements RequestHandler<List<Integer>, Integer>{
  @Override
  public Integer handleRequest(List<Integer> event, Context context)
  {
    return event.stream().mapToInt(Integer::intValue).sum();
  }
}