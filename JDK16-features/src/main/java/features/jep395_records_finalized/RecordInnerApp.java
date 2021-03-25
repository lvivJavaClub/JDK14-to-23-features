package features.jep395_records_finalized;

import lombok.Builder;
import lombok.NonNull;

public class RecordInnerApp {

  public static void main(String[] args) {

    var person = Person.builder().name("Jaca").email("example.com").build();
    System.out.println(person);

    var person2 = new Person(null, "some@email.com");
    System.out.println(person2);
  }

  record Person(String name, String email) {

    @Builder
    Person(@NonNull String name, String email) {
      this.name = name;
      this.email = email;
    }
  }

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
