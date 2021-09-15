package features.beyond_the_jeps;

import java.util.HexFormat;

public class HexFormatApp {

  public static void main(String[] args) {
    HexFormat hex = HexFormat.of();
    byte b = 127;
    String byteStr = hex.toHexDigits(b);
    // byteStr == "7f"
    byte byteVal = (byte) HexFormat.fromHexDigits(byteStr);
    // byteVal == 127
    assert(byteStr.equals("7f"));
    assert(b == byteVal);
  }
}
