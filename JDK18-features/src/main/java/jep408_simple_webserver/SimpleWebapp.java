package jep408_simple_webserver;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpHandlers;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;
import com.sun.net.httpserver.SimpleFileServer.OutputLevel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleWebapp {

  // Headers.of("Content-Type", "application/json"),
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

    //HttpServer fileServer = SimpleFileServer.createFileServer(LOOPBACK_ADDR, CURRENT_DIR, OutputLevel.INFO);
    //fileServer.start();
    // HttpServer.create
    HttpHandler fileHandler = SimpleFileServer.createFileHandler(CURRENT_DIR);
    // HttpHandlers

    HttpHandler jsonHandler = HttpHandlers.of(200, Headers.of("Content-Type", "application/json"), TEST_JSON);

    HttpHandler composedHandler = HttpHandlers
        .handleOrElse(request -> request.getRequestURI().toString().endsWith("test-json"), jsonHandler, fileHandler);

    Filter logFilter = SimpleFileServer.createOutputFilter(new FileOutputStream("server-logs.txt"), OutputLevel.VERBOSE);
    Filter filter = Filter.adaptRequest("Test filter", request -> {
      //request.getRequestHeaders().add("newheader", "headervalue");
      System.out.println("In Test Filter");
      return request;
    });
    HttpServer httpServer = HttpServer.create(LOOPBACK_ADDR, 10, "/", composedHandler, logFilter, filter);
    httpServer.start();
    // Filter.adapt
  }
}
