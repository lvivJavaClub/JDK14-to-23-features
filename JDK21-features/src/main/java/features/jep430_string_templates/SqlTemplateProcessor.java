package features.jep430_string_templates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlTemplateProcessor {

  public static StringTemplate.Processor<PreparedStatement, SQLException> initSqlProcessor(Connection conn) {
    return st -> {
      String query = String.join("?", st.fragments());
      PreparedStatement ps = conn.prepareStatement(query);
      int index = 1;
      for (Object value : st.values()) {
        switch (value) {
          case Integer i -> ps.setInt(index, i);
          case Float f -> ps.setFloat(index, f);
          case Double d -> ps.setDouble(index, d);
          case Boolean b -> ps.setBoolean(index, b);
          default -> ps.setString(index, String.valueOf(value));
        }

        ++index;
      }

      return ps;
    };
  }
}
