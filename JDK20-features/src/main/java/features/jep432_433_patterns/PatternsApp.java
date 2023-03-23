package features.jep432_433_patterns;

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
    Box<String> box = new RoundBox<>("Hello", "JavaClub");
    printBoxInfo(box);

    // 2. Record patterns in for loops
    var order = new Order(LocalDateTime.now(), OrderStatus.IN_PROGRESS, new CustomerInfo("John", "+3428979233"));
    loopOrders(order, order.withStatus(OrderStatus.CONFIRMED), order.withStatus(OrderStatus.DELIVERED));

    // 3. Removal of support for named record patterns
  }

  static void loopOrders(Order... order) {

  }

  static <T> void printBoxInfo(Box<T> box) {
    if (box instanceof RoundBox<T>(var t1, var t2)) {
      System.out.println("RoundBox: " + t1 + ", " + t2);
    } else if (box instanceof TriangleBox<T>(var t1, var t2, var t3)) {
      System.out.println("TriangleBox: " + t1 + ", " + t2 + ", " + t3);
    }else {
      System.out.println("None");
    }
  }
}
