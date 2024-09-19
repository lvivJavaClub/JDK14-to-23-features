package features.jep477_implicitly_declared_classes;

import java.time.LocalDate;
import java.util.Scanner;

public class SimpleMain {

  public static void main(String[] args) {
    System.out.println("Hello! Today is " + LocalDate.now());
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter temperature in Celsius: ");
    double celsius = scanner.nextDouble();
    double fahrenheit = (celsius * 9/5) + 32;
    System.out.println("Temperature in Fahrenheit: " + fahrenheit);
  }
}
