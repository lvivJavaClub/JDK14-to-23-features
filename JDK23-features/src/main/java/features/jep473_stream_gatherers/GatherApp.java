package features.jep473_stream_gatherers;

import com.ginsberg.gatherers4j.Gatherers4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
public class GatherApp {

  public static void main(String[] args) {
    GatherApp example = new GatherApp();

    System.out.println("Fixed Window:");
    example.windowFixedExample();

    System.out.println("\nSliding Window:");
    example.windowSlidingExample();

    System.out.println("\nFold (Sum) Example:");
    example.foldExample();

    System.out.println("\nScan (Running Sum) Example:");
    example.scanExample();

    System.out.println("\nMap Concurrent Example:");
    example.mapConcurrentExample();
  }

  // Example of using the windowFixed gatherer
  public void windowFixedExample() {
    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8);

    // Group elements into fixed-size windows of size 3
    List<List<Integer>> result = numbers.gather(Gatherers.windowFixed(3)).toList();

    // Print the result
    System.out.println(result);
    // Output: [[1, 2, 3], [4, 5, 6], [7, 8]]
  }

  // Example of using the windowSliding gatherer
  public void windowSlidingExample() {
    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6);

    // Group elements into sliding windows of size 3
    List<List<Integer>> result = numbers.gather(Gatherers.windowSliding(3)).toList();

    // Print the result
    System.out.println(result);
    // Output: [[1, 2, 3], [2, 3, 4], [3, 4, 5], [4, 5, 6]]
  }

  // Example of using the fold gatherer
  public void foldExample() {
    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

    // Use fold to sum the elements
    int sum = numbers.gather(Gatherers.fold(() -> 0, Integer::sum)).collect(Collectors.summingInt(i -> i));

    // Print the result
    System.out.println(sum);
    // Output: 15
  }

  // Example of using the scan gatherer
  public void scanExample() {
    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

    // Compute running sum using scan
    List<Integer> result = numbers.gather(Gatherers.scan(() -> 0, Integer::sum)).toList();

    // Print the result
    System.out.println(result);
    // Output: [1, 3, 6, 10, 15] (running sum of numbers)
  }

  // Example of using mapConcurrent gatherer
  public void mapConcurrentExample() {
    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

    // Map concurrently up to 2 elements at a time
    List<Integer> result = numbers.gather(Gatherers.mapConcurrent(2, i -> {
      try {
        // Simulate some delay
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      return i * 2; // Double the element
    })).toList();

    // Print the result
    System.out.println(result);
    // Output: [2, 4, 6, 8, 10]
  }
}
