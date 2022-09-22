package jep420_switch_pattern_matching;

public class SwitchApp {

  sealed interface ParentContainer<T> permits StringContainer, SubContainer {}
  record StringContainer<T>() implements ParentContainer<String> {}
  record SubContainer<T>() implements ParentContainer<T> {}

  public static void main(String[] args) {
    Integer obj = 10;
    ParentContainer objectStringContainer = new SubContainer();
    StringContainer<String> s = new StringContainer<>();
    genericHierarchy(s);
  }

  static void coverage(String unknown) {
    switch (unknown) {
      case "abcd"  -> System.out.println("Ten");
      case String s && s.length() >2 -> System.out.println("More than 5");
      case null, default -> throw new IllegalStateException("Unexpected ot null value:");
    };
  }

  static void genericHierarchy(ParentContainer<Object> parent){
    switch (parent) {
      case StringContainer<String> b -> System.out.println("String container");
    }
  }
}
