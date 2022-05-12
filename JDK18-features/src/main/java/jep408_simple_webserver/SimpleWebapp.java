package jep408_simple_webserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleWebapp {

  private static final String TEST_JSON = """
      {
        "country": "Ukraine",
        "goal": "Victory"
      }
      """;
  private static final Path CURRENT_DIR = Paths.get(".").toAbsolutePath().normalize();
  private static final InetSocketAddress LOOPBACK_ADDR = new InetSocketAddress(InetAddress.getLoopbackAddress(), 8080);

  public static void main(String[] args) throws IOException {
    // java -m jdk.httpserver

    // SimpleFileServer

    // HttpServer.create

    // HttpHandlers

    // Filter.adapt

  }
}
