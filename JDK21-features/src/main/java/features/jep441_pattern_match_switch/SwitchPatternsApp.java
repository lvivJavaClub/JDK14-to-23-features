package features.jep441_pattern_match_switch;

import java.util.Arrays;
import java.util.List;

public class SwitchPatternsApp {

  sealed interface Edible permits FoodCategory, SpecificDish {}

  public enum FoodCategory implements Edible {FRUIT, VEGETABLE, MEAT}

  record SpecificDish(String name, List<String> ingredients) implements Edible {}

  public static void main(String[] args) {
    exhaustiveSwitchWithoutEnumSupport(new SpecificDish("some name", List.of("none")));
  }

  static void exhaustiveSwitchWithoutEnumSupport(Edible edible) {
    switch (edible) {
      case FoodCategory.MEAT -> throw new IllegalStateException("BUT I'm vegan!");
      case FoodCategory.VEGETABLE -> System.out.println("it's vegetable. Yummy :)");
      case FoodCategory.FRUIT -> System.out.println("it's fruit. Yummy :)");
      case SpecificDish(var name, var ingredients) -> System.out.println(ingredients);
    }
  }
}
