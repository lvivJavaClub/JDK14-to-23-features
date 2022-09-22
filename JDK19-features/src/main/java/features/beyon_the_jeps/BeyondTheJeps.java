package features.beyon_the_jeps;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class BeyondTheJeps {

  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

    // 1. HashMap, HashSet, LinkedHashMap, LinkedHashSet, WeakHashMap
    // https://bugs.openjdk.org/browse/JDK-8284377
    // https://mbien.dev/blog/entry/java-19-s-map-set

    // 2. ofLocalizedPattern
    // https://bugs.openjdk.org/browse/JDK-8243445
    System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(LocalDate.now()));
    System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(LocalDate.now()));
    System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(LocalDate.now()));
    System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(LocalDate.now()));
  }
}
