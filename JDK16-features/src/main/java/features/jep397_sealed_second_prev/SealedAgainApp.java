package features.jep397_sealed_second_prev;

public class SealedAgainApp {

  public static void main(String[] args) {

  }

  public sealed interface Animal permits Lion, Tiger, Elephant {}

  public final class Lion implements Animal {}

  public sealed class Tiger implements Animal permits Cat {}

  public final class Cat extends Tiger {}

  public non-sealed class Elephant implements Animal {}
}


