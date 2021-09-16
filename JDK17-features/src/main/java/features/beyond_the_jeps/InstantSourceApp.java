package features.beyond_the_jeps;

import java.time.Clock;
import java.time.Instant;
import java.time.InstantSource;
import lombok.RequiredArgsConstructor;

// https://github.com/ThreeTen/threeten-extra/issues/150
// https://bugs-stage.openjdk.java.net/browse/JDK-8266847
public class InstantSourceApp {

  public static void main(String[] args) {

    var coolService = new SomeService(Clock.systemUTC());
    coolService.doAwesomeProcess(Instant.now());
  }

  @RequiredArgsConstructor
  public static class SomeService {

    private final InstantSource source;

    public void doAwesomeProcess(Instant endInstant) {
      if (source.instant().isAfter(endInstant)) {
        System.out.println("HERE");
      }
      source.instant();
    }
  }
}
