package features.jep403_strongly_encapsulate_internals;

import lombok.SneakyThrows;

public class StronglyApp {

  @SneakyThrows
  public static void main(String[] args) {
    // Internal Access
    System.out.println(sun.security.util.SecurityConstants.ALL_PERMISSION);
    // Code that uses reflection to access private fields of exported java.* APIs
    var ks = java.security.KeyStore.getInstance("jceks");
    var f = ks.getClass().getDeclaredField("keyStoreSpi");
    f.setAccessible(true);
    // Code that uses reflection to invoke protected methods of exported java.* APIs
    var dc = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
    dc.setAccessible(true);
  }
}
