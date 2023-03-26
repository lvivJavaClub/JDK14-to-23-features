package features.jep432_433_patterns;

import static features.jep432_433_patterns.OrderStatus.IN_PROGRESS;
import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;
import lombok.With;


// ---------
sealed interface Box<T> permits RoundBox, TriangleBox {}

record RoundBox<T>(T t1, T t2) implements Box<T> {}

record TriangleBox<T>(T t1, T t2, T t3) implements Box<T> {}

// ---------
@With
record Order(LocalDateTime date, OrderStatus status, CustomerInfo customerInfo) {}

record CustomerInfo(String name, String phone) {}

enum OrderStatus {CONFIRMED, IN_PROGRESS, DELIVERED, CANCELLED}

// ---------

public class PatternsApp {


  public static void main(String[] args) {
    // 1. Inference of type arguments for generic record patterns
    Box<String> box = new RoundBox<>("Hello", "CoffeeJug");
    printBoxInfo(box);

    // 2. Record patterns in for loops
    Order order = new Order(now(), IN_PROGRESS, null);
    loopOrders(new Order[]{order, order, order});

    Object obj = new Order(now(), IN_PROGRESS, new CustomerInfo("Stepan", "+3428979233"));

    if (obj instanceof Order or) {
      System.out.printf("Calling you... (%s)%n", or.customerInfo().phone());
    }

    if (obj instanceof Order(var date, var status, CustomerInfo(var name, var phone))) {
      System.out.printf("Calling you... (%s)%n", phone);
    }
    // 3. Removal of support for named record patterns
  }

  static void loopOrders(Order[] order) {

    for (Order(var d, var st, CustomerInfo(var n, var p)) : order){
      System.out.printf("Status - %s for customer - %s%n", st, n);
    }
  }

  static void printBoxInfo(Box box) {
    switch (box) {
      case RoundBox(var t1, var t2) -> System.out.printf("RoundBox: %s, %s%n", t1, t2);
      case TriangleBox(var t1, var t2, var t3) -> System.out.printf("TriangleBox: %s, %s%n", t1, t2);
    }
  }
}
