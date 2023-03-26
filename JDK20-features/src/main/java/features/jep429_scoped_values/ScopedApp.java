package features.jep429_scoped_values;

import static java.lang.System.out;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.random.RandomGenerator;
import jdk.incubator.concurrent.ScopedValue;
import jdk.incubator.concurrent.StructuredTaskScope;
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

  final static ScopedValue<PaymentState> PAYMENT_STATE = ScopedValue.newInstance();

  @SneakyThrows
  public static void main(String[] args) {

    var state = PaymentState.builder()
        .accountNumber("1241241gds3")
        .customerId("jaks7241")
        .amount(10_000)
        .id("1111")
        .build();

    ScopedValue.where(PAYMENT_STATE, state).run(PaymentClient::process);

    Thread.sleep(3000);
  }

  static class PaymentClient {

    @SneakyThrows
    public static void process() {
      try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
        Future<String> task1 = scope.fork(PaymentService::doTransaction);
        Future<String> task2 = scope.fork(CustomerCRM::fetchCustomerContact);

        scope.join();
        scope.throwIfFailed();

        String status = task1.resultNow();
        String customerContact = task2.resultNow();
        ScopedValue.where(PAYMENT_STATE, PAYMENT_STATE.get().masked())
            .run(() -> notifyCustomer(status, customerContact));
      }
    }

    private static void notifyCustomer(String status, String contact) {
      String paymentId = Objects.requireNonNull(PAYMENT_STATE.get()).id();
      out.println(PAYMENT_STATE.get().accountNumber());
      out.printf("Payment %s is finished with status %s%n", paymentId, status);
      out.printf("Sending SMS to %s", contact);
    }
  }

  static class PaymentService {

    @SneakyThrows
    public static String doTransaction() {
      out.println(Thread.currentThread().getName());
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
      out.println(Thread.currentThread().getName());

      TimeUnit.SECONDS.sleep(2);
      return FAKER.phoneNumber().phoneNumber();
    }
  }
}
