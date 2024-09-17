package features.jep459_string_templates;


import java.sql.*;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static java.lang.StringTemplate.RAW;
import static java.util.FormatProcessor.FMT;

public class StringTemplatesApp {

  public static void main(String[] args) {

    formerConcatenationSolution();
    formatterTemplate();

    try {
      customSqlTemplate();
    } catch (SQLException e) {
      System.out.println("SQL Error: " + e);
    }
  }

  private static void customSqlTemplate() throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres");
    var email = "emily.davis@example.com";
    var SQL = SqlTemplateProcessor.initSqlProcessor(connection);

    PreparedStatement sqlQuery = SQL."SELECT * FROM test_person where email = \{email}";
    var resultSet = sqlQuery.executeQuery();
    resultSet.next();
    System.out.println(resultSet.getString("name"));
  }

  private static void formatterTemplate() {
    record Shape(String name, int corners) {}

    var shapes = new Shape[]{
        new Shape("Circle", 0),
        new Shape("Triangle", 3),
        new Shape("Dodecagon", 12)
    };

    // Show FMT
    var table = FMT."""
          Name     Corners
          %-12s\{shapes[0].name()}  %3d\{shapes[0].corners()}
          %-12s\{shapes[1].name()}  %3d\{shapes[1].corners()}
          %-12s\{shapes[2].name()}  %3d\{shapes[2].corners()}
                """;

    System.out.println(table);
  }

  private static void formerConcatenationSolution() {
    var language = "Java";
    var eventType = "JavaClub";

    var greeting1 = "Hello " + language + "community! Welcome to " + eventType + "event!";
    // 2. StringBuffer and StringBuilder
    var greeting2 = new StringBuilder()
        .append("Hello ").append(language)
        .append(" community! Welcome to ")
        .append(eventType).append(" event!")
        .toString();
    // 3. String::format and String::formatted
    var greeting3 = "Hello %s community! Welcome to %s event!".formatted(language, eventType);
    // 4. java.text.MessageFormat
    var greeting4 = MessageFormat.format("Hello {0} community! Welcome to {1} event!", language, eventType);
  }
}
