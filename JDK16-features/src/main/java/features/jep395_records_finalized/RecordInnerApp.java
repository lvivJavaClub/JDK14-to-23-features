package features.jep395_records_finalized;

public class RecordInnerApp {

  public static void main(String[] args) {
    var outer = new OuterClass();
    var inner = outer.new InnerClass();
    var staticInnerInner = new OuterClass.InnerClass.InnerInnerStaticClass();
    System.out.println("Secret: " + staticInnerInner.innerSecret.secret());
  }

  record Person(String name) {}

  record Persons(String... names) {}
}

class OuterClass {

  class InnerClass {

    // only allowed on JDK 16
    static class InnerInnerStaticClass {

      InnerRecord innerSecret;
      InnerInnerStaticClass() {
        this.innerSecret = new InnerRecord("only allowed in Java 16");
      }
    }

    record InnerRecord(String secret) {
    }
  }
}
