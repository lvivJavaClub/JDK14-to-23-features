package n03_jep358_Helpful_NullPointerExceptions;

public class App {
  public static void main(String[] args) {
    A a = new A();
    System.out.println("Hello World!" + a.b.i.toString());
  }

  static class A {
    B b;
  }

  class B {
    Integer i;
  }
}
