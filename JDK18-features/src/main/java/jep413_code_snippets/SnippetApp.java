package jep413_code_snippets;

import java.nio.file.Path;

// javadoc ../src/main/java/jep413_code_snippets/SnippetApp.java
public class SnippetApp {

  public static void main(String[] args) {
    // @snippet (internal and external)
    // @start region=name   @end
    // Attributes (lang, id)
    // @highlight substring regex   region   type (bold, italic, type="highlighted")
    // @replace regex='' replacement=""
    // @link substring="" target=""
  }

  /**
   *
   *
   * <pre>{@snippet file="Sample.java":
   *    var handler = HttpHandlers.handleOrElse(
   *        (req) -> req.getRequestMethod().equals("PUT"),  // @highlight  region="test"   regex="\breq\b"   type="highlighted"
   *        (exchange) -> {
   *            // validate and handle PUT request
   *        },
   *     (req)
   *        SimpleFileServer.createFileHandler(Path.of("/some/path")));  //@replace region="replacement" substring="/some/path"   replacement="{your path}"
   *        SimpleFileServer.createFileHandler(Path.of("/some/path"));   // @link   substring="Path.of"    target="java.nio.file.Path"
   *     // @end region="replacement"
   *     // @end region="test"
   *    );
   * }</pre>
   */
  public void someMethod(){

  }
}
