package features.jep405_record_patterns;

import java.time.LocalDateTime;

public class RecordPatternsApp {

  record Order(LocalDateTime date, OrderDetails details) {}

  record OrderDetails(CustomerInfo customerInfo, OrderStatus status, String comment) {}

  record CustomerInfo(String name, String phone) {}

  enum OrderStatus {CONFIRMED, IN_PROGRESS, DELIVERED, CANCELLED}

  public static void main(String[] args) {
    Object order = new Order(LocalDateTime.now(),
        new OrderDetails(
            new CustomerInfo("John", "+3428979233"), OrderStatus.IN_PROGRESS, "Hurry up!"
        )
    );

    if (order instanceof Order(var time,OrderDetails(CustomerInfo(var name,var phone),var status,var comment))
        && OrderStatus.IN_PROGRESS.equals(status)
    ) {
      System.out.println("Order status: " + status);
      System.out.println("Time: " + time);
      System.out.printf("Calling customer %s with phone %s%n", name, phone);
      System.out.println("Comment: " + comment);
    }
  }
}
