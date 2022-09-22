package features.jep405_record_patterns;

import java.time.LocalDateTime;

public class RecordPatternsApp {

  record Order(LocalDateTime date, OrderDetails details) {}
  record OrderDetails(CustomerInfo customerInfo, OrderStatus status, String comment) {}
  record CustomerInfo(String name, String phone){}
  enum OrderStatus { CONFIRMED, IN_PROGRESS, DELIVERED, CANCELLED }

  public static void main(String[] args) {
    Object order = new Order(LocalDateTime.now(),
        new OrderDetails(
            new CustomerInfo("John", "+3428979233"), OrderStatus.IN_PROGRESS, "Hurry up!"
        )
    );
  }
}
