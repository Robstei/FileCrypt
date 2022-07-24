package edu.junit5.quickstart.learning;

import javafx.beans.property.SimpleStringProperty;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Provider;
import java.security.Security;

public class Test {


  public static void main(String[] args) throws Exception {
    SimpleStringProperty s1 = new SimpleStringProperty("a");
    SimpleStringProperty s2 = new SimpleStringProperty("b");
    s1.bind(s2);
    System.out.println(s1.getValue());
    System.out.println(s2.getValue());

    Security.addProvider(new BouncyCastleProvider());

    for (Provider provider : Security.getProviders()) {
      if (provider.getName().equals("BC")) {
        for (Provider.Service service : provider.getServices()) {
          if (service.getAlgorithm().equals(
                  "SCRYPT"))
            System.out.println(service.getAlgorithm());
        }
      }
    }
  }
}
