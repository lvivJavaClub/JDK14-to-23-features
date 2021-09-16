package features.jep415_context_specific_des_filters.serlib;

import features.jep415_context_specific_des_filters.DesFiltersApp.User;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputFilter;
import java.io.ObjectInputFilter.Status;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import lombok.SneakyThrows;

public class SerdeLib {

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
  public static <T> T deserialize(byte[] streamBytes) {
    try (var bais = new ByteArrayInputStream(streamBytes);
        var ois = new ObjectInputStream(bais)) {
      ois.setObjectInputFilter(ObjectInputFilter.allowFilter(claz -> !claz.equals(User.class), Status.REJECTED));
      return (T) ois.readObject();
    }
  }
}
