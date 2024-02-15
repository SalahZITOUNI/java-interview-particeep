package basic;

import io.vavr.control.Option;

public class BasicTest {

  /**
   * return i ^ n for positive Integer, None otherwise
   * also return None in case of errors or potential overflow
   */
  public static Option<Integer> power(Integer i, Integer n) {
      // Check for null inputs, negative exponent, or potential overflow conditions
      if (i == null || n == null || n < 0 || (i != 0 && i != 1 && n > 30)) { // Example overflow condition
          return Option.none();
      }

      if (n == 0) {
          return Option.of(1); // Any number to the power of 0 is 1
      }
      if (i == 0) {
          return Option.of(0); // 0 to any positive power is 0
      }
      if (i == 1) {
          return Option.of(1); // 1 to any power is 1
      }

      long result = 1; // Use long to check for overflow
      for (int count = 0; count < n; count++) {
          result *= i;
          if (result > Integer.MAX_VALUE) {
              return Option.none(); // Return None in case of overflow
          }
      }
      return Option.of((int) result); // Safe cast since we checked for overflow
  }
}