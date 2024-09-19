package features.jep467_markdown;

public class MarkdownApp {

  public static void main(String[] args) {

  }

  /**
   * This method calculates the area of a circle given the radius.
   * <p>
   * Example usage:
   * <pre>{@code
   * double area = calculateCircleArea(5.0);
   * System.out.println(area);  // Output: 78.53981633974483
   * }</pre>
   *
   * @param radius the radius of the circle, must be positive
   * @return the calculated area of the circle
   * @throws IllegalArgumentException if the radius is negative
   */
  public double calculateCircleArea1(double radius) {
    if (radius < 0) {
      throw new IllegalArgumentException("Radius must be positive");
    }
    return Math.PI * radius * radius;
  }

  /// This method calculates the area of a circle given the radius.
  ///
  /// Example usage:
  ///
  /// ```java
   /// double area = calculateCircleArea(5.0);
   /// System.out.println(area);  // Output: 78.53981633974483
   /// ```
  ///
  /// @param radius the radius of the circle, must be positive
   /// @return the calculated area of the circle
   /// @throws IllegalArgumentException if the radius is negative
  public double calculateCircleArea2(double radius) {
    if (radius < 0) {
      throw new IllegalArgumentException("Radius must be positive");
    }
    return Math.PI * radius * radius;
  }
}
