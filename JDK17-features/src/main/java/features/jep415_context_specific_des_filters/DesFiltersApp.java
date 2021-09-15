package features.jep415_context_specific_des_filters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.function.BinaryOperator;
import lombok.SneakyThrows;

record User(String name, String email) implements java.io.Serializable {}

public class DesFiltersApp {

  public static void main(String[] args) {

    byte[] serialBytes = serialize(new User("John Doe", "user@example.com"));
    ObjectInputFilter filter = ObjectInputFilter.Config.createFilter("!features.jep415_context_specific_des_filters.User");
    //ObjectInputFilter.Config.setSerialFilterFactory(new MySimpleFilterFactory());
    ObjectInputFilter.Config.setSerialFilter(filter);
    User usr = deserialize(serialBytes);
    System.out.println(usr);
  }

  @SneakyThrows
  public static <T> byte[] serialize(T obj) {
    try (var baos = new ByteArrayOutputStream();
        var oos = new ObjectOutputStream(baos)) {
      oos.writeObject(obj);
      oos.flush();
      return baos.toByteArray();
    }
  }

  @SneakyThrows
  static <T> T deserialize(byte[] streamBytes) {
    try (var bais = new ByteArrayInputStream(streamBytes);
        var ois = new ObjectInputStream(bais)) {
      ois.setObjectInputFilter(ObjectInputFilter.Config.createFilter("features.jep415_context_specific_des_filters.User"));
      return (T) ois.readObject();
    }
  }

  static class MySimpleFilterFactory implements BinaryOperator<ObjectInputFilter> {
    public ObjectInputFilter apply(ObjectInputFilter curr, ObjectInputFilter next) {
      System.out.println("Current filter: " + curr);
      System.out.println("Requested filter: " + next);
      return ObjectInputFilter.merge(next, curr);
    }
  }
}
