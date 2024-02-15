package collection;

import java.util.List;
import java.util.stream.Collectors;

class CollectionTest {

  /**
   * operation : x -> ((x * 2) + 3) ^ 5
   * No change as the logic is correct for the specified operation.
   */
  public static List<Double> compute1(List<Integer> input) {
    return input.stream()
            .map(x -> Math.pow((x * 2) + 3, 5))
            .collect(Collectors.toList());
  }
  /**
   * Alternative implementation for compute2 with a slight variation in style.
   */
  public static List<String> compute2(List<String> input) {
    return input.stream()
            .map(s -> {
              if (s.isEmpty()) return s;
              String processed = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
              return processed + processed;
            })
            .collect(Collectors.toList());
  }
}