package jep400_utf8;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utf8App {

  // java.io package, InputStreamReader, FileReader, OutputStreamWriter, FileWriter, and PrintStream
  // java.util package, Formatter and Scanner
  // java.net package, URLEncoder and URLDecoder

  public static void main(String[] args) throws IOException {
    try (FileWriter fw = new FileWriter("text.txt");
        BufferedWriter bw = new BufferedWriter(fw)) {
      bw.write("ハッピーコーディング！");
    }

    String text = Files.readString(Path.of("text.txt"));
    System.out.println(text);

    System.out.println("Default charset : " + Charset.forName("dsa", Charset.defaultCharset()));
    System.out.println("file.encoding   : " + System.getProperty("file.encoding"));
    System.out.println("native.encoding : " + System.getProperty("native.encoding"));  }

  // java -Dfile.encoding=UTF-8
  // COMPAT
}
