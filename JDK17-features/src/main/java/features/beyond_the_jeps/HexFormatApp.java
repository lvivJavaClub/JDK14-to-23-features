package features.beyond_the_jeps;

import java.util.Arrays;
import java.util.HexFormat;
import lombok.SneakyThrows;

public class HexFormatApp {

  @SneakyThrows
  public static void main(String[] args) {

    // Simple Conversions
    HexFormat hexFormat = HexFormat.of();
    String hexDigits = hexFormat.toHexDigits(10);
    System.out.println(hexFormat.formatHex(hexFormat.parseHex(hexDigits)));

    // With Delimiter, Prefix and Suffix
    HexFormat formatterWithDelim = HexFormat.ofDelimiter(":").withPrefix("#").withSuffix("$");
    byte[] bytes = {1, 2, 3, 4, 125, 127, 100};
    String formattedString = formatterWithDelim.formatHex(bytes);
    System.out.println(formattedString);
    byte[] theSameBytes = formatterWithDelim.parseHex(formattedString);
    System.out.println(Arrays.toString(theSameBytes));
  }
}
