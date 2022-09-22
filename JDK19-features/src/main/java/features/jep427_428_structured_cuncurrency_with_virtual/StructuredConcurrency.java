package features.jep427_428_structured_cuncurrency_with_virtual;

import features.jep427_428_structured_cuncurrency_with_virtual.VolunteerRequest.Medicines;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Future.State;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import jdk.incubator.concurrent.StructuredTaskScope;

// --enable-preview --add-modules jdk.incubator.concurrent
public class StructuredConcurrency {
  // 1. Simple task submit with resultNow(), exceptionNow(), state()
  // 2. StructuredTaskScope
  // 3. StructuredTaskScope.ShutdownOnSuccess
  // 4. StructuredTaskScope.ShutdownOnFailure
  // 5. custom StructuredTaskScope

  public static void main(String[] args) {
    try (var scope = new CustomScope()) {
      List<Future<Medicines>> futureStream = IntStream.range(0, 5).mapToObj(VolunteerRequest::new).map(scope::fork).toList();
      scope.join();

      System.out.println(scope.getResult());

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  static class CustomScope extends StructuredTaskScope<Medicines>{

    private final Collection<Medicines> success = new ConcurrentLinkedDeque<>();

    @Override
    protected void handleComplete(Future<Medicines> future) {
      if(future.state().equals(State.SUCCESS)){
        success.add(future.resultNow());
      }
      super.handleComplete(future);
    }

    public Collection<Medicines> getResult(){
      return success;
    }
  }
}
