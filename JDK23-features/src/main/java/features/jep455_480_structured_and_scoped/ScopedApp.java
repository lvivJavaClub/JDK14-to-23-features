package features.jep455_480_structured_and_scoped;

import java.time.Instant;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.TimeoutException;

@SuppressWarnings("ALL")
public class ScopedApp {

  private static final ScopedValue<String> REQUEST_ID = ScopedValue.newInstance();

  public static void main(String[] args) throws Exception {
    // Simulate API service handling a request
    new ScopedApp().handleApiRequest("Request-XYZ");
  }

  public void handleApiRequest(String requestId) throws Exception {

    // Bind the requestId to be shared across the entire task scope
    ScopedValue.runWhere(REQUEST_ID, requestId, () -> {
      try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

        var userInfoTask = scope.fork(() -> fetchUserInfo());
        var transactionTask = scope.fork(() -> fetchTransactionData());

        scope.joinUntil(Instant.now().plusSeconds(6)).throwIfFailed();

        System.out.println("Request ID: " + REQUEST_ID.get() + " - User Info: " + userInfoTask.get());
        System.out.println("Request ID: " + REQUEST_ID.get() + " - Transactions: " + transactionTask.get());

      } catch (TimeoutException te) {
        System.err.println("API request timed out for request ID: " + REQUEST_ID.get());
      } catch (Exception e) {
        System.err.println("Error processing API request for request ID: " + REQUEST_ID.get() + ": " + e.getMessage());
      }
    });
  }

  // Simulate fetching user information from an API
  private String fetchUserInfo() throws InterruptedException {
    System.out.println("Fetching user info with request ID: " + REQUEST_ID.get());
    Thread.sleep(2000); // Simulate API call delay
    return "User Info [Name: John Doe, Age: 29]";
  }

  // Simulate fetching transaction data from an API
  private String fetchTransactionData() throws InterruptedException {
    System.out.println("Fetching transaction data with request ID: " + REQUEST_ID.get());
    Thread.sleep(3000); // Simulate API call delay
    return "Transaction Data [Amount: $1000, Date: 2024-09-18]";
  }
}
