package features.beyond_the_jeps;

import java.time.Clock;
import java.time.Instant;
import java.time.InstantSource;
import lombok.RequiredArgsConstructor;

public class InstantSourceApp {

  public static void main(String[] args) {
    var system = InstantSource.system();
    System.out.println(system.millis());
    System.out.println(system.millis());
    System.out.println(system.millis());
    System.out.println(system.millis());
    System.out.println(system.millis());
    System.out.println(system.millis());
  }

  @RequiredArgsConstructor
  public class SomeService {

    private final Clock source;

    public void process(Instant endInstant) {
      if (source.instant().isAfter(endInstant)) {
        // rest of code
      }
    }
  }
}
