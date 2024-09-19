@SuppressWarnings("preview")
void main() {
  println("Hello! Today is " + LocalDate.now());
  var celsius = readln("Enter temperature in Celsius: ");
  double fahrenheit = (Double.valueOf(celsius) * 9 / 5) + 32;
  println("Temperature in Fahrenheit: " + fahrenheit);
}
