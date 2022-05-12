package jep420_switch_pattern_matching;

public class SwitchApp {

  sealed interface ParentContainer permits StringContainer, SubContainer {}
  record StringContainer() implements ParentContainer {}
  record SubContainer() implements ParentContainer {}

  public static void main(String[] args) {
    Integer obj = 10;
    coverage(obj);
    ParentContainer objectStringContainer = new SubContainer();
    genericHierarchy(objectStringContainer);
  }

  static void coverage(Integer unknown) {
    switch (unknown) {
      case 10  -> System.out.println("Ten");
      case Integer i && i > 5 -> System.out.println("More than 5");
      case null, default -> throw new IllegalStateException("Unexpected ot null value:");
    };
  }

  static void genericHierarchy(ParentContainer parent){
    switch (parent) {
      case SubContainer a -> System.out.println("Medium container");
      case StringContainer b -> System.out.println("String container");
    }
  }
}
