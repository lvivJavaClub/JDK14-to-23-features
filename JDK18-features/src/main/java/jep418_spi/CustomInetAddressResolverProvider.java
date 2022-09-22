package jep418_spi;

import java.net.spi.InetAddressResolver;
import java.net.spi.InetAddressResolverProvider;

public class CustomInetAddressResolverProvider extends InetAddressResolverProvider {

  @Override
  public InetAddressResolver get(Configuration configuration) {
    return new CustomInetAddressResolver();
  }

  @Override
  public String name() {
    return "Custom resolver for demo";
  }
}
