package n01_jep361_Switch_Expressions;

public class App {
  public static void main(String[] args) {

    Integer i =null;

    String r = switch (i) {
      case 1 -> {
        String res = "one";
        yield res + "123";
      }
//      case null -> {
//        yield null;
//      }
      case 2 -> {
        switch (i) {
          case 2:
            System.out.println("{two}");
            break;
          default:
            System.err.println("error");
        }
        yield "two";
      }
      default -> "other";
    };
    System.out.println("Hello World! res " + r);
  }
}
