package features.beyon_the_jeps;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class BeyondTheJeps {

  public static void main(String[] args) {

    // 1. HashMap, HashSet, LinkedHashMap, LinkedHashSet, WeakHashMap
    // https://bugs.openjdk.org/browse/JDK-8284377
    // https://mbien.dev/blog/entry/java-19-s-map-set
    Map<String, String> map1 = new HashMap<>(120);
    Map<String, String> map2 = HashMap.newHashMap(120);
    Map<String, String> map3 = LinkedHashMap.newHashMap(120);
    Map<String, String> map4 = WeakHashMap.newWeakHashMap(120);

    Set<String> set1 = HashSet.newHashSet(100);
    Set<String> set2 = LinkedHashSet.newHashSet(100);

    // 2. ofLocalizedPattern
    // https://bugs.openjdk.org/browse/JDK-8243445
    System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(LocalDate.now()));
    System.out.println(DateTimeFormatter.ofLocalizedPattern("yMMM").format(LocalDate.now()));
  }
}
