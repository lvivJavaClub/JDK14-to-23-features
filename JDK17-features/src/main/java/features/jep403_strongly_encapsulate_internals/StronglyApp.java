package features.jep403_strongly_encapsulate_internals;


import java.security.KeyStoreException;

public class StronglyApp {

  public static void main(String[] args) throws Exception {
    // Internal Access
    //System.out.println(sun.security.util.SecurityConstants.ALL_PERMISSION);
    // Code that uses reflection to access private fields of exported java.* APIs
    //privateFieldsAccess();
    // Code that uses reflection to invoke protected methods of exported java.* APIs
    //protectedInternalMethods();
  }

  private static void privateFieldsAccess() throws KeyStoreException, NoSuchFieldException {
    var ks = java.security.KeyStore.getInstance("jceks");
    var f = ks.getClass().getDeclaredField("keyStoreSpi");
    f.setAccessible(true);
  }

  private static void protectedInternalMethods() throws NoSuchMethodException {
    var dc = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
    dc.setAccessible(true);
  }
}
