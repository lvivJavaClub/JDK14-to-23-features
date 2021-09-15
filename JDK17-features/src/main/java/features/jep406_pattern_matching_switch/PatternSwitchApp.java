package features.jep406_pattern_matching_switch;

sealed interface Vehicle permits LandVehicle, Boat, Aircraft {}

sealed interface LandVehicle extends Vehicle permits Truck, Bike, Car, Bus {}

record Truck() implements LandVehicle{}

record Bike() implements LandVehicle{}

record Car() implements LandVehicle{}

record Bus() implements LandVehicle{}

record Boat() implements Vehicle {}

record Aircraft() implements Vehicle {}


public class PatternSwitchApp {


  public static void main(String[] args) {

  }

}
