package features.jep306_strictfp;

public strictfp class StrictFpApp {

  public static strictfp class ScientificCalculator {

    public double sum(double value1, double value2) {
      return value1 + value2;
    }
  }


  public strictfp static void main(String[] args) {
    var calculator = new ScientificCalculator();
    System.out.println(calculator.sum(23e10, 98e17));
  }
}
