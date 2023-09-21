package features.jep443_unnamed_patterns_and_vars;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnnamedApp {

  record CustomerInfo(String name, String phone) {}

  record Order(LocalDateTime date, String status, CustomerInfo customerInfo) {}

  public static void main(String[] args) {
    Object obj = new Order(LocalDateTime.now(), "IN_PROGRESS", new CustomerInfo("Stepan", "+3428979233"));
    checkOrder(obj);

    // An enhanced for loop with side effects:
    // An assignment statement, where the result of the expression on the right hand side is not needed:
    // A catch block:
    // In try-with-resources:
    // A lambda whose parameter is irrelevant:
  }

  static void checkOrder(Object obj) {
    if (obj instanceof Order(var date, var status, CustomerInfo(var name, var phone))) {
      System.out.printf("Calling you... (%s)%n", phone);
    }
  }
}
