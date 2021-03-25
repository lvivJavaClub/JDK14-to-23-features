package features.jep390_value_class_warn;

/**
 * -XX:+UnlockDiagnosticVMOptions -XX:DiagnoseSyncOnValueBasedClasses=1 will treat the operation as a fatal error. -XX:DiagnoseSyncOnValueBasedClasses=2 will turn on logging
 */

public class ValueApp {

  public static void main(String[] args) {
    // 1. constructor usage
    Integer a = new Integer(10);
    // 2. synchronized
    synchronized(a){
      System.out.println("Dont use it");
    }
  }
}
