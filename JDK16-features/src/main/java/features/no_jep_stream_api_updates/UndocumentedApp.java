package features.no_jep_stream_api_updates;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UndocumentedApp {

  // API diff 15->16 https://javaalmanac.io/jdk/16/apidiff/15/
  public static void main(String[] args) {
    final List<String> counterEchos = gimmeSomeList(0, 10);
    counterEchos.add("Damn! Stop the count!");
    counterEchos.forEach(System.out::println);

    testMultiMap();

    final var flat = Stream.of("1", "2", List.of("3", "4", "5", List.of("6", "7",  List.of("6", "7"))))
        .mapMulti(UndocumentedApp::expandIterable)
        .toList();

    System.out.println(flat);
  }

  // https://bugs.openjdk.java.net/browse/JDK-8180352
  public static List<String> gimmeSomeList(int start, int end) {
    return IntStream.rangeClosed(start, end)
        .mapToObj("I am #%d"::formatted)
        .collect(Collectors.toUnmodifiableList());
  }

  //https://download.java.net/java/early_access/jdk16/docs/api/java.base/java/util/stream/Stream.html#mapMulti(java.util.function.BiConsumer)
  public static void testMultiMap(){

    Stream.of("Java", "Python", "JavaScript", "C#", "Ruby")
        .<String>mapMulti((str, consumer) -> {
          if (str.length() > 4) {
            consumer.accept(str + "222");  // lengths larger than 4
          }
        })
        .forEach(i -> System.out.print(i + " "));
  }

  static void expandIterable(Object obj, Consumer<Object> consumer) {
    if (obj instanceof Iterable<?> iterable) {
      for (var item : iterable) {
        expandIterable(item, consumer);
      }
    } else if (obj != null) {
      consumer.accept(obj);
    }
  }
}
