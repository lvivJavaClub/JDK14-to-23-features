package jep418_spi;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class SpiApp {

  public static void main(String[] args) throws UnknownHostException {
    InetAddress[] addresses = InetAddress.getAllByName("savelife.in.ua");
    System.out.println("addresses = " + Arrays.toString(addresses));
  }
}
