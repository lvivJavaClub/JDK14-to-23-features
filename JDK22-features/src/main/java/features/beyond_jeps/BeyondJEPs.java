package features.beyond_jeps;

import java.text.ListFormat;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class BeyondJEPs {

  public static void main(String[] args) {

    List<String> weekdays = Arrays.stream(DayOfWeek.values()).map(Objects::toString).limit(2).toList();
    ListFormat listFormatter = ListFormat.getInstance(Locale.US, ListFormat.Type.STANDARD, ListFormat.Style.NARROW);
    System.out.println(listFormatter.format(weekdays));
  }
}
