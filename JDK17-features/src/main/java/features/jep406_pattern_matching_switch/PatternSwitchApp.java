package features.jep406_pattern_matching_switch;

sealed interface Vehicle permits LandVehicle, Boat, Aircraft, Spaceship {}

record LandVehicle() implements Vehicle {}

record Boat() implements Vehicle {}

record Spaceship() implements Vehicle {}

record Aircraft(int maxFlightHeight) implements Vehicle {
}


public class PatternSwitchApp {

  public static void test(Vehicle o){
    switch (o){
      case LandVehicle l -> System.out.println("Land");
      case Boat l -> System.out.println("Boat");
      case Aircraft l && l.maxFlightHeight() > 2000 -> System.out.println("Aircraft");
      case Aircraft l -> System.out.println("Aircraft");
      case Spaceship l -> System.out.println("Aircraft");
    }
  }

  public static void main(String[] args) {
    System.out.println("");
  }

  static String formatter(Object o) {
    String formatted = "unknown";
    if (o instanceof Integer i) {
      formatted = String.format("int %d", i);
    } else if (o instanceof Long l) {
      formatted = String.format("long %d", l);
    } else if (o instanceof Double d) {
      formatted = String.format("double %f", d);
    } else if (o instanceof String s && s.length() > 10) {
      formatted = String.format("String %s", s);
    }
    return formatted;
  }

  static String formatterWithSwitch(Object o) {
    return switch (o) {
      case String s && s.length() > 10 -> "large string";
      case String s -> "small string";
      case Long l -> String.format("long %d", l);
      case Double d -> String.format("double %f", d);
      case Aircraft s -> String.format("String %s", s);
      case null, default -> "unknown";
    };
  }
}
