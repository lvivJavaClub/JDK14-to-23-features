package features.jep466_class_file_api;

public class TestClass {

  public void sayHello() {
    System.out.println("Hello!");
    secretMethod();
  }

  private void secretMethod() {
    System.out.println("This is a secret method.");
  }

  private void secretMethod2() {
    System.out.println("This is a secret method.");
  }
}
