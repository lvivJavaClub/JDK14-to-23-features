package features.jep415_context_specific_des_filters;

import static features.jep415_context_specific_des_filters.serlib.SerdeLib.deserialize;
import static features.jep415_context_specific_des_filters.serlib.SerdeLib.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputFilter;
import java.io.ObjectInputFilter.Status;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.function.BinaryOperator;
import lombok.SneakyThrows;


public class DesFiltersApp {

  public static record User(String name, String email) implements java.io.Serializable {}

  public static void main(String[] args) {

    byte[] serialBytes = serialize(new User("John Doe", "user@example.com"));
    ObjectInputFilter.Config.setSerialFilterFactory((prev, curr) -> null);
    User usr = deserialize(serialBytes);
    System.out.println(usr);
  }
}
