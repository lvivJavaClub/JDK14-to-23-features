package features.jep430_string_templates;

import org.json.JSONObject;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static features.jep430_string_templates.JsonTemplateProcessor.JSON;
import static java.lang.StringTemplate.RAW;
import static java.util.FormatProcessor.FMT;

public class StringTemplatesApp {

  record User(String firstName, String lastName, LocalDate birthDate, String address, List<String> hobbies) {}

  public static void main(String[] args) {

    var user = new User("John", "Doe", LocalDate.of(1991, 8, 24),
        "Ukraine, Lviv", List.of("Sport", "Programming", "Fishing"));

    formerConcatenationSolution(user);
    newConcatenationSolution(user);
    formatterTemplate();

    try {
      customJsonTemplate();
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }

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

    PreparedStatement sqlQuery = SQL. "SELECT * FROM test_person where email = \{ email }" ;
    var resultSet = sqlQuery.executeQuery();
    resultSet.next();
    System.out.println(resultSet.getString("name"));
  }

  private static void customJsonTemplate() {
    var user = new User("John", "Doe", LocalDate.of(1991, 8, 24),
        "Ukraine, Lviv", List.of("Sport", "Programming", "Fishing"));
    JSONObject userObj = JSON."""
        {
        "firstName": "\{user.firstName}",
        "lastName": "\{user.lastName}"
        "date": "\{user.birthDate}"
        }
        """;

    System.out.println(userObj);
  }

  private static void formatterTemplate() {
    record Shape(String name, int corners) {}

    var shapes = new Shape[]{
        new Shape("Circle", 0),
        new Shape("Triangle", 3),
        new Shape("Dodecagon", 12)
    };

    // Show FMT
    var table = FMT. """
          Name     Corners
          %-12s\{ shapes[0].name() }  %3d\{ shapes[0].corners() }
          %-12s\{ shapes[1].name() }  %3d\{ shapes[1].corners() }
          %-12s\{ shapes[2].name() }  %3d\{ shapes[2].corners() }
                """ ;

    System.out.println(table);
  }

  private static void newConcatenationSolution(User user) {
    var greeting = STR. """
            Hello JavaClub \uD83D\uDC4B,
            My name is %{user.firstName + " "+ user.lastName}, I'm from \{ user.address }.
            My date of birth is \{ user.birthDate }, so I am \{ Period.between(user.birthDate, LocalDate.now()).getYears() } years old. My hobbies: \{ user.hobbies }
        """ ;

    System.out.println(greeting);

    StringTemplate rowTmpl = RAW. """
            Hello JavaClub \uD83D\uDC4B,
            My name is \{ user.firstName + " " + user.lastName }, I'm from \{ user.address }.
            My date of birth is \{ user.birthDate }, so I am \{ Period.between(user.birthDate, LocalDate.now()).getYears() } years old. My hobbies: \{ user.hobbies }
        """ ;

    // RAW
    // fragments
    // values
    // interpolate
  }

  private static void formerConcatenationSolution(User user) {
    // StringBuffer and StringBuilder
    // java.text.MessageFormat
    // String::format and String::formatted
    var greeting = "Hello JavaClub \uD83D\uDC4B, My name is %s %s, I'm from %s. My date of birth is %s, so I am %d years old. My hobbies: %s".formatted(user.firstName, user.lastName, user.address, user.birthDate, Period.between(user.birthDate, LocalDate.now()).getYears(), user.hobbies);
    System.out.println(greeting);
  }
}
