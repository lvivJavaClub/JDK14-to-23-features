package features.jep395_records_finalized;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import coresearch.cvurl.io.mapper.impl.JacksonMapper;
import coresearch.cvurl.io.model.CVurlConfig;
import coresearch.cvurl.io.request.CVurl;
import java.util.List;

public class RecordSerApp {

  public static void main(String[] args) {

    final CVurl httpClient = initHttpClient();

    // Source ->> https://reqres.in/
    httpClient.get("https://reqres.in/api/users?page=1")
        .asObject(UsersResponse.class)
        .data()
        .stream()
        .map("Deserialized record ->> %s"::formatted)
        .forEach(System.out::println);

    final var userCreateResp = httpClient.post("https://reqres.in/api/users")
        .body(new UserCreateRequest("Duke", "Developer"))
        .asObject(UserCreateResponse.class);

    System.out.println(userCreateResp);
  }

  private static CVurl initHttpClient() {
    var mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    return new CVurl(
        CVurlConfig.builder().genericMapper(new JacksonMapper(mapper)).build()
    );
  }

  // https://github.com/Randgalt/record-builder#RecordInterface-Example
  // There is some LifeHack with @Builder
  record TestLombok(String msg) {
  }

  public record User(int id, String email, String last_name, String avatar) {}

  public record UsersResponse(int page, int per_page, int total, int total_pages, List<User> data) {}

  public record UserCreateRequest(String name, String job) {}

  public record UserCreateResponse(String name, String job, int id) {}
}
