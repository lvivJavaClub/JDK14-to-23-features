package features.jep462_464_structured_cuncurrency_and_scoped;

import java.math.BigInteger;
import java.util.concurrent.StructuredTaskScope;
import java.util.random.RandomGenerator;

record UserState(String id, String email, boolean isAdmin) {}

public class StructuredApp {

  final static ScopedValue<UserState> USER_STATE = ScopedValue.newInstance();

  public static void main(String[] args) throws InterruptedException {

    var state1 = new UserState("1111", "admin@admin.com", true);
    ScopedValue.where(USER_STATE, state1).run(CustomersCRM::process);

    var state2 = new UserState("2222", "regular@mail.com", false);
    ScopedValue.where(USER_STATE, state2).run(CustomersCRM::process);

    Thread.sleep(3000);
  }

  static class CustomersCRM {

    public static void process() {
      System.out.println(USER_STATE.get());

      try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
        StructuredTaskScope.Subtask<Integer> ordersCountTask = scope.fork(() -> {
          Thread.sleep(3000);
          System.out.println("Calculated count");
          return RandomGenerator.getDefault().nextInt();
        });
        StructuredTaskScope.Subtask<BigInteger> calculatedRevenueTask = scope.fork(() -> {
          System.out.println("Checking access");
          Thread.sleep(1000);
          if (!USER_STATE.get().isAdmin()) {
            throw new RuntimeException();
          }
          return BigInteger.valueOf(50_000);
        });

        scope.join();
        scope.throwIfFailed();

        // previously resultNow() now in Future
        var ordersCount = ordersCountTask.get();
        var revenue = calculatedRevenueTask.get();

        System.out.println("Orders counter: " + ordersCount + " and totall revenue " + revenue);
        System.out.println("---");
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
