package features.jep394_pattern_matching_finalized;

import java.util.List;

public class PatternApp {

  static String message = "Hey hey";

  // https://github.com/openjdk/amber-docs/blob/master/site/design-notes/pattern-match-object-model.md
  // https://openjdk.java.net/jeps/8260244
  public static void main(String[] args) {
    instanceOfTest("Javaclub");
  }

  public static void instanceOfTest(Object msg) {
    if (msg instanceof String message && message.length() > 5) {
      System.out.printf("%s - is a String! so obvious:)%n", message);
    }

    // 1. || operator?
    if (msg instanceof String message) {
      System.out.printf("%s - is a String! so obvious:)%n", message);
    }
    // 2. inverted?
    if (!(msg instanceof String message)) {
      System.out.printf("%s - is a String! so obvious:)%n", "message");
    }
    // 3. reassign?
    if (msg instanceof String message) {
      message = message + "AAAAA";
      System.out.println(message);
    }
    // 4. generic?
    if (msg instanceof List<?> list) {

    }
    // 5. shadowing
    if (msg instanceof String message) {
      System.out.println(message);
    } else {
      System.out.println(message);
    }
  }
}
