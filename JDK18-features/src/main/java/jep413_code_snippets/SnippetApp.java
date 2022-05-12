package jep413_code_snippets;

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
   * <pre>{@code
   *    var handler = HttpHandlers.handleOrElse(
   *        (req) -> req.getRequestMethod().equals("PUT"),
   *        (exchange) -> {
   *            // validate and handle PUT request
   *        },
   *        SimpleFileServer.createFileHandler(Path.of("/some/path")))
   *    );
   * }</pre>
   */
  public void someMethod(){

  }
}
