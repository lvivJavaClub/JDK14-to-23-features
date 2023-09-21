package features.jep431_sequenced_collections;

import java.util.*;

// "Life can only be understood backwards; but it must be lived forwards."

/*
interface SequencedCollection<E> extends Collection<E> {
    // new method
    SequencedCollection<E> reversed();
    // methods promoted from Deque
    void addFirst(E);
    void addLast(E);
    E getFirst();
    E getLast();
    E removeFirst();
    E removeLast();
}
 */


/*
interface SequencedMap<K,V> extends Map<K,V> {
    // new methods
    SequencedMap<K,V> reversed();
    SequencedSet<K> sequencedKeySet();
    SequencedCollection<V> sequencedValues();
    SequencedSet<Entry<K,V>> sequencedEntrySet();
    V putFirst(K, V);
    V putLast(K, V);
    // methods promoted from NavigableMap
    Entry<K, V> firstEntry();
    Entry<K, V> lastEntry();
    Entry<K, V> pollFirstEntry();
    Entry<K, V> pollLastEntry();
}
 */

public class SequencedCollectionsApp {

  private static final List<String> TEST_LIST = new ArrayList<>(List.of("one_L", "two_L", "three_L"));
  private static final Deque<String> TEST_DEQUE = new ArrayDeque<>(List.of("one_D", "two_D", "three_D"));
  private static final SortedSet<String> TEST_SORTED_SET = new TreeSet<>(List.of("one_S", "two_S", "three_", "zero_S"));
  private static final SortedMap<String, Integer> TEST_SORTED_MAP = new TreeMap<>(Map.of("one_M", 1, "two_M", 2, "zero_M", 0));

  // Se
  public static void main(String[] args) {
    List<String> items = List.of("one", "two");
    // 1. SequencedCollection<String>, SequencedSet<String>, SequencedMap<String, Integer>

    listDemo();
    dequeDemo();
    setDemo();
    mapDemo();

    /*
      2. Collections.unmodifiableSequencedCollection(sequencedCollection);
         Collections.unmodifiableSequencedSet(sequencedSet);
         Collections.unmodifiableSequencedMap(sequencedMap);
    */

    items.reversed();
  }

  private static void mapDemo() {
    System.out.println("MAP demo");
    System.out.println(TEST_SORTED_MAP);
    Map.Entry<String, Integer> firstEntry = TEST_SORTED_MAP.entrySet().iterator().next();
    System.out.println("First: " + firstEntry);

    Iterator<Map.Entry<String, Integer>> iterator = TEST_SORTED_MAP.entrySet().iterator();
    Map.Entry<String, Integer> lastEntry = null;
    while (iterator.hasNext()) {
      lastEntry = iterator.next();
    }
    System.out.println("Last: " + lastEntry);
    System.out.println("reversed: none...");
    System.out.println("---");
  }

  private static void setDemo() {
    System.out.println("SET demo");
    System.out.println("First: " + TEST_SORTED_SET.getFirst());
    System.out.println("Last: " + TEST_SORTED_SET.getLast());
    System.out.println(TEST_SORTED_SET);
    System.out.print("reversed: none");

    System.out.println("---");
  }

  private static void dequeDemo() {
    System.out.println("DEQUE demo");
    System.out.println("First: " + TEST_DEQUE.getFirst());
    System.out.println("Last: " + TEST_DEQUE.getLast());

    TEST_DEQUE.addFirst("minus one");
    TEST_DEQUE.addLast("infinity");

    System.out.println(TEST_DEQUE);
    System.out.print("reversed: ");
    for (var it = TEST_DEQUE.descendingIterator(); it.hasNext();) {
      System.out.print(it.next() + ", ");
    }

    System.out.println();
    System.out.println("---");
  }

  private static void listDemo() {
    System.out.println("LIST demo");
    System.out.println("First: " + TEST_LIST.get(0));
    System.out.println("Last: " + TEST_LIST.get(TEST_LIST.size() - 1));

    TEST_LIST.add(0, "minus one");
    TEST_LIST.add("infinity");
    System.out.println(TEST_LIST);
    System.out.print("reversed: ");
    for (var it = TEST_LIST.listIterator(TEST_LIST.size()); it.hasPrevious();) {
      System.out.print(it.previous() + ", ");
    }
    System.out.println();
    System.out.println("---");
  }
}
