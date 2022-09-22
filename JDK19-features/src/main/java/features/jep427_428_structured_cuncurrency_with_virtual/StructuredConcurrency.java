package features.jep427_428_structured_cuncurrency_with_virtual;

import features.jep427_428_structured_cuncurrency_with_virtual.VolunteerRequest.Medicines;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StructuredConcurrency {
  // 1. Simple task submit with resultNow(), exceptionNow(), state()
  // 2. StructuredTaskScope
  // 3. StructuredTaskScope.ShutdownOnSuccess
  // 4. StructuredTaskScope.ShutdownOnFailure
  // 5. custom StructuredTaskScope

  public static void main(String[] args) {
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      Future<Medicines> request1 = executor.submit(new VolunteerRequest(1));
      Future<Medicines> request2 = executor.submit(new VolunteerRequest(2));
      Future<Medicines> request3 = executor.submit(new VolunteerRequest(3));

      System.out.println(request1.get());
      System.out.println(request2.get());
      System.out.println(request3.get());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
