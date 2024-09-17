package features.jep461_stream_gatherers;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class GatherApp {

  record User(String name, String language, int age) {}

  record Expenses(BigInteger amount, String currency) {}

  public static void main(String[] args) {

    // Gatherers.fold
    var foldResult = Stream.of(1, 2, 3, 4, 5)
        .gather(Gatherers.fold(() -> "", (string, element) -> string + element))
        .toList();
    System.out.println("fold: " + foldResult);

    // Gatherers.mapConcurrent
    var concurrentResult = Stream.of(3, 1, 5, 3, 2)
        .gather(Gatherers.mapConcurrent(4, n -> {
          try {
            Thread.sleep(1000);
            System.out.println("Processing: " + n);

          } catch (InterruptedException _) {
            Thread.currentThread().interrupt();
          }
          return n * 2;
        }))
        .toList();
    System.out.println("mapConcurrent: " + concurrentResult);

    // Gatherers.scan()
    var scanResult = Stream.of(1, 2, 10, 20, 30)
        .gather(Gatherers.scan(() -> "", (state, element) -> state + element))
        .toList();
    System.out.println("scan: " + scanResult);

    // Gatherers.windowFixed()
    var windowFixed = Stream.of(1, 2, 10, 20, 30)
        .gather(Gatherers.windowFixed(2))
        .toList();
    System.out.println("windowFixed: " + windowFixed);

    // Gatherers.windowSliding()
    var windowSliding = Stream.of(1, 2, 10, 20, 30)
        .gather(Gatherers.windowSliding(2))
        .toList();
    System.out.println("windowSliding: " + windowSliding);

    // count till
    System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .gather(countUpTo(2))
        .toList());

    // distinct by
    // max by
    Stream.of(
        new User("Peter", "Java", 22),
        new User("James", "Java", 30),
        new User("John", "Python", 20),
        new User("Olesia", "Python", 24),
        new User("Marta", "Go", 18)
    );

    // reduce by currency
    Stream.of(
        new Expenses(BigInteger.valueOf(100), "UAH"),
        new Expenses(BigInteger.valueOf(700), "UAH"),
        new Expenses(BigInteger.valueOf(300), "USD"),
        new Expenses(BigInteger.valueOf(500), "USD")
    );
  }

  public static <T> Gatherer<T, ?, String> countUpTo(final int max) {
    class CountState {
      AtomicInteger first = new AtomicInteger();
      AtomicInteger second = new AtomicInteger();
    }

    return Gatherer.ofSequential(
        CountState::new,
        (state, element, downstream) -> {
          if (state.first.getAndIncrement() < max) {
            return downstream.push(String.valueOf(element));
          } else {
            state.second.getAndIncrement();
            return false;
          }
        },
        (state, downstream) -> {
          downstream.push("and other " + state.second.get() + " elements");
        }
    );
  }


  public static class DistinctBy {

    /*
    *
    * Function<T, P> selector
    *
    *
    * HashSet::new;
    *
    *
    * Integrator.ofGreedy((state, item, downstream) -> {
        P extracted = selector.apply(item);
        if(!state.contains(extracted)) {
          state.add(extracted);
          downstream.push(item);
        }

        return true;
      });
    *
    * */
  }


  public static class ReduceBy {

    /*
    *  Function<T, P> selector;
    * BiFunction<T, T, T> operation;
    * HashMap::new;
    *
    * Integrator.ofGreedy((state, item, _) -> {
        state.merge(selector.apply(item), item, operation);
        return true;
      });
    *
    *
    * (state, downstream) -> state.values().forEach(downstream::push);
    * */

  }
}
