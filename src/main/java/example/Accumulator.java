package example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Accumulator{
  private static final ObjectMapper mapper = new ObjectMapper();
  private static final TypeFactory factory = mapper.getTypeFactory();
  private static final CollectionType mapType = factory.constructCollectionType(List.class, Integer.class);

  public static void main(String[] args) throws IOException {

    try ( final var in = new BufferedReader(new InputStreamReader(System.in))) {
      final var jsonString = in.lines().collect(Collectors.joining(""));
      final List<Integer> list = mapper.readValue(jsonString, mapType);
      System.out.println(list.stream().mapToInt(Integer::intValue).sum());
    }
  }
}