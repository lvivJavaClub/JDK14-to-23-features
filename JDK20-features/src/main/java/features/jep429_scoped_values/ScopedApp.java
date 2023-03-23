package features.jep429_scoped_values;

import static java.lang.System.out;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.random.RandomGenerator;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.With;

@With
@Builder
record PaymentState(String id, String customerId, String accountNumber, int amount) {

  PaymentState masked() {
    return this.withAccountNumber(accountNumber.substring(0, 2) + "*****");
  }
}

// --enable-preview --add-modules jdk.incubator.concurrent
public class ScopedApp {

  // 1. ThreadLocal drawbacks
  // 2. ScopedValue init
  // 3. ScopedValue where
  // 4. ScopedValue bounding
  // 5. ScopedValue + StructuredTaskScope

  final static ThreadLocal<PaymentState> PAYMENT_STATE = new ThreadLocal<>();

  @SneakyThrows
  public static void main(String[] args) {

    PAYMENT_STATE.set(
        PaymentState.builder()
            .accountNumber("1241241gds3")
            .customerId("jaks7241")
            .amount(10_000)
            .id("1111")
            .build()
    );
    PaymentClient.process();

    PAYMENT_STATE.remove();
  }

  static class PaymentClient {

    public static void process() {
      String status = PaymentService.doTransaction();
      String customerContact = CustomerCRM.fetchCustomerContact();

      notifyCustomer(status, customerContact);
    }

    private static void notifyCustomer(String status, String contact) {
      String paymentId = Objects.requireNonNull(PAYMENT_STATE.get()).id();
      out.printf("Payment %s is finished with status %s%n", paymentId, status);
      out.printf("Sending SMS to %s", contact);
    }
  }

  static class PaymentService {

    @SneakyThrows
    public static String doTransaction() {
      PaymentState paymentState = PAYMENT_STATE.get();
      validateAccount(paymentState.accountNumber());
      out.printf("Performing transaction with id %s and amount %d%n", paymentState.id(), paymentState.amount());
      TimeUnit.SECONDS.sleep(2);
      return RandomGenerator.getDefault().nextBoolean() ? "success" : "fail";
    }

    @SneakyThrows
    private static void validateAccount(String accountNumber) {
      out.println("Validating account number...");
      TimeUnit.SECONDS.sleep(2);
    }
  }


  static class CustomerCRM {

    private static final Faker FAKER = new Faker(Locale.of("uk"));

    @SneakyThrows
    public static String fetchCustomerContact() {
      TimeUnit.SECONDS.sleep(2);
      return FAKER.phoneNumber().phoneNumber();
    }
  }
}
