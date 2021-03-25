package features.jep394_pattern_matching_finalized;

public class PatternApp {

  static String message = "Hey hey";
  // https://github.com/openjdk/amber-docs/blob/master/site/design-notes/pattern-match-object-model.md
  // https://openjdk.java.net/jeps/8260244
  public static void main(String[] args) {
    instanceOfTest("Javaclub");
  }

  public static void instanceOfTest(Object msg) {
    if (msg instanceof String) {
      var message = (String) msg;
      System.out.printf("%s - is a String! so obvious:)%n", message);
    }

    // 1. || operator?

    // 2. inverted?

    // 3. reassign?

    // 4. generic?

    // 5. shadowing
  }
}
