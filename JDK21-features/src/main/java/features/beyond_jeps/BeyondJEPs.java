package features.beyond_jeps;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;

public class BeyondJEPs {

  public static void main(String[] args) {
    // 1. Math.clamp() and StrictMath.clamp()
    System.out.println(Math.clamp(20, 10, 50));

    // 2. String indexOf(int,int,int) and indexOf(String,int,int) Methods to Support a Range of Indices
    System.out.println("1235 fdsfds".indexOf("35", 1, 6));

    // 3. splitWithDelimiters() Methods Added to String and java.util.regex.Pattern
    System.out.println(Arrays.toString("aa_bbb______ccc".splitWithDelimiters("_+", 10)));

    // 4. StringBuilder and StringBuffer repeat Methods,
    // fun with null
    System.out.println(new StringBuilder().repeat(null, 10));
    System.out.println(new StringBuffer().repeat(null, 10));

    // 5. Unicode Emoji Properties
    int possibleEmoji = Character.codePointAt("☕️", 0);
    System.out.println(Character.isEmoji(possibleEmoji));
    System.out.println(Character.isEmoji(Character.codePointAt("$@️", 0)));

    // 6. java.net.http.HttpClient Is Now AutoCloseable
    try (var httpClient = HttpClient.newHttpClient()){
      String url = "https://jsonplaceholder.typicode.com/posts/1";
      String responseBody = httpClient.send(HttpRequest.newBuilder().uri(URI.create(url)).build(), BodyHandlers.ofString()).body();
      System.out.println("Response Body:\n" + responseBody);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
