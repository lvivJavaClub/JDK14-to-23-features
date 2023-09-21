package features.jep453_structured_cuncurrency;

import java.util.concurrent.StructuredTaskScope;

record PaymentState(String id, String customerId, String accountNumber, int amount) {}

public class StructuredApp {

  final static ScopedValue<PaymentState> PAYMENT_STATE = ScopedValue.newInstance();

  public static void main(String[] args) throws InterruptedException {

    var state1 = new PaymentState("1111", "1241241gds3", "jaks7241", 10_000);
    ScopedValue.where(PAYMENT_STATE, state1).run(PaymentClient::process);

    var state2 = new PaymentState("5666", "bbq1", "124d", 5_000);
    ScopedValue.where(PAYMENT_STATE, state2).run(PaymentClient::process);

    Thread.sleep(3000);
  }

  static class PaymentClient {

    public static void process() {
      try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
        StructuredTaskScope.Subtask<String> trxStatusFetch = scope.fork(() -> "DONE");
        StructuredTaskScope.Subtask<String> customerContactFetch = scope.fork(() -> "0999 412 55");

        scope.join();
        scope.throwIfFailed();

        // previously resultNow() now in Future
        String status = trxStatusFetch.get();
        String customerContact = customerContactFetch.get();

        System.out.println("Current PAYMENT_STATE: " + PAYMENT_STATE.get());
        System.out.println("Notifying customer " + customerContact + " about status " + status);
        System.out.println("---");
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
