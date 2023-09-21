package features.jep443_unnamed_patterns_and_vars;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.List;

public class UnnamedApp {

  record CustomerInfo(String name, String phone) {}

  record Order(LocalDateTime date, String status, CustomerInfo customerInfo) {}

  public static void main(String[] args) {
    Object obj = new Order(LocalDateTime.now(), "IN_PROGRESS", new CustomerInfo("Stepan", "+3428979233"));
    checkOrder(obj);

    var _ = new ArrayDeque<>().poll();
    var strings = List.of("1", "2", "3");

    strings.stream().forEach(_ -> System.out.println("hello"));
    // An enhanced for loop with side effects:
    // An assignment statement, where the result of the expression on the right hand side is not needed:
    // A catch block:
    // In try-with-resources:
    // A lambda whose parameter is irrelevant:

  }

  static void checkOrder(Object obj) {
    if (obj instanceof Order(_, _, CustomerInfo(_, var phone))) {
      System.out.printf("Calling you... (%s)%n", phone);
    }
  }
}
