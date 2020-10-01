package n02_jep305_Pattern_Matching_for_instanceof_Preview;

public class App {
  public static void main(String[] args) {
    Object obj = "1";

    if (!(obj instanceof String s)) {
      System.err.println("Hello World!");
    } else if (!(obj instanceof Long l)) {
      System.err.println("Hello World!" + s.length());
    } else {
      System.out.println("Hello World! " + l.longValue());
    }
  }
}
