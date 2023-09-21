package features.jep441_pattern_match_switch;

public class SwitchPatternsApp {

  sealed interface Edible permits FoodCategory, SpecificDish {}

  public enum FoodCategory implements Edible {FRUIT, VEGETABLE, MEAT}

  record SpecificDish(String name, String[] ingredients) implements Edible {}

  public static void main(String[] args) {
    exhaustiveSwitchWithoutEnumSupport(FoodCategory.VEGETABLE);
  }

  static void exhaustiveSwitchWithoutEnumSupport(FoodCategory category) {
    switch (category) {
      case MEAT -> throw new IllegalStateException("BUT I'm vegan!");
      case VEGETABLE -> System.out.println("it's vegetable. Yummy :)");
      case FRUIT -> System.out.println("it's fruit. Yummy :)");
    }
  }
}
