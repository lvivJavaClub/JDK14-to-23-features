package features.jep455_primitives_in_patterns;

public class PrimitiveApp {

  public static void main(String[] args) {
    // 1. Pattern Matching for switch Supports Primitive Type Patterns
    int errorCode = getErrorCodeFromExternalSystem();
    handleErrorCode(errorCode);

    // 2. Enhanced Support for Primitive Types in Record Patterns
    TransactionRecord transaction = new TransactionRecord("TX123", 1500.75, TransactionType.CREDIT);
    processTransaction(transaction);

    // 3. Pattern Matching for instanceof Supports Primitive Types
    double rawSensorData = getSensorData();
    processSensorData(rawSensorData);

    // 4. Expanded Primitive Support in switch
    double purchaseAmount = 5000.50;
    calculateDiscount(purchaseAmount);

    boolean featureToggle = isFeatureEnabled();
    handleFeatureToggle(featureToggle);
  }

  // Example 1: Pattern Matching for switch Supports Primitive Type Patterns
  public static void handleErrorCode(int errorCode) {
    // Before JEP 455
    String message = switch (errorCode) {
      case 404 -> "Resource not found";
      case 500 -> "Internal server error";
      case 200 -> "Operation successful";
      default -> "Unknown error code: " + errorCode;
    };
    System.out.println("Error message: " + message);


    // Simulating error handling based on different codes returned by an external system
   /* String message = switch (errorCode) {
      case 404 -> "Resource not found";
      case 500 -> "Internal server error";
      case 200 -> "Operation successful";
      case int unknown -> "Unknown error code: " + unknown; // Handles any unexpected error codes
    };
    System.out.println("Error message: " + message);*/
  }

  // Example 2: Enhanced Support for Primitive Types in Record Patterns
  public static void processTransaction(TransactionRecord transaction) {
    // Before JEP 455
    if (transaction != null) {
      String id = transaction.id();
      double amount = transaction.amount();
      TransactionType type = transaction.type();

      if (amount == (int) amount) { // Check if amount can be an integer
        int intAmount = (int) amount;
        System.out.printf("Processing transaction ID: %s with amount $%d as %s (whole number format)%n", id, intAmount, type);
      } else {
        System.out.printf("Processing transaction ID: %s with amount $%s as %s (decimal format)%n", id, amount, type);
      }
    } else {
      System.out.println("Invalid transaction format.");
    }
  }

  // Example 3: Pattern Matching for instanceof Supports Primitive Types
  public static void processSensorData(double sensorValue) {
    // Before JEP 455
    if ((int) sensorValue == sensorValue) {
      int intValue = (int) sensorValue;
      System.out.println("Sensor data is an integer value: " + intValue);
    } else if ((float) sensorValue == sensorValue) {
      float floatValue = (float) sensorValue;
      System.out.println("Sensor data is a float value: " + floatValue);
    } else {
      System.out.println("Sensor data is a double value: " + sensorValue);
    }

    /*// Simulating processing of sensor data that could come in various types
    if (sensorValue instanceof int intValue) {
      System.out.println("Sensor data is an integer value: " + intValue);
      // Perform integer-specific processing
    } else if (sensorValue instanceof float floatValue) {
      System.out.println("Sensor data is a float value: " + floatValue);
      // Perform float-specific processing
    } else {
      System.out.println("Sensor data is a double value: " + sensorValue);
      // Handle as double
    }*/
  }

  // Example 4.1: Expanded Primitive Support in switch for double
  public static void calculateDiscount(double amount) {
    // Before JEP 455
    double discount;
    if (amount == 0.0) {
      discount = 0.0;
    } else if (amount > 0 && amount <= 100) {
      discount = 5.0; // Small discount
    } else if (amount > 100 && amount <= 1000) {
      discount = 10.0; // Medium discount
    } else if (amount > 1000) {
      discount = 20.0; // Large discount
    } else {
      discount = 0.0;
    }
    System.out.println("Discount for purchase amount $" + amount + ": " + discount + "%");


   /* // Simulating discount calculation based on purchase amount
    double discount = switch (amount) {
      case 0.0 -> 0.0;
      case double a when a > 0 && a <= 100 -> 5.0; // Small discount
      case double b when b > 100 && b <= 1000 -> 10.0; // Medium discount
      case double c when c > 1000 -> 20.0; // Large discount
      default -> 0.0;
    };
    System.out.println("Discount for purchase amount $" + amount + ": " + discount + "%");*/
  }

  // Example 4.2: Expanded Primitive Support in switch for boolean
  public static void handleFeatureToggle(boolean featureEnabled) {
    // Before JEP 455
    if (featureEnabled) {
      System.out.println("Feature is enabled.");
    } else {
      System.out.println("Feature is disabled.");
    }

    /*// Simulating a feature toggle switch
    switch (featureEnabled) {
      case true -> System.out.println("Feature is enabled.");
      case false -> System.out.println("Feature is disabled.");
    }*/
  }

  // Mock methods to simulate external data or states
  public static int getErrorCodeFromExternalSystem() {
    return 500; // Simulating an error code returned by an external system
  }

  public static double getSensorData() {
    return 42.0; // Simulating sensor data
  }

  public static boolean isFeatureEnabled() {
    return true; // Simulating a feature toggle state
  }

  public record TransactionRecord(String id, double amount, TransactionType type) {
  }

  enum TransactionType {
    DEBIT, CREDIT
  }
}

