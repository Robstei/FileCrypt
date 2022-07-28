package edu.junit5.quickstart.learning;

import javafx.beans.property.SimpleStringProperty;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Provider;
import java.security.Security;
import java.security.Signature;

public class Test {

  ;

  public static void main(String[] args) throws Exception {
    SimpleStringProperty s1 = new SimpleStringProperty("a");
    SimpleStringProperty s2 = new SimpleStringProperty("b");
    s1.bind(s2);
    System.out.println(s1.getValue());
    System.out.println(s2.getValue());

    Security.insertProviderAt(new BouncyCastleProvider(), 1);

    for (Provider provider : Security.getProviders()) {
      if (provider.getName().equals("BC")) {
        for (Provider.Service service : provider.getServices()) {
          if (service.getAlgorithm().contains(
                  "SHA256withDSA".toUpperCase()))
            System.out.println(service.getAlgorithm());
        }
      }
    }
    Provider[] providers = Security.getProviders();
    Signature signature = Signature.getInstance("DSA",
                                                "BC");


  }

  private enum mapKey {
    encryptionAlgorithm("encryptionAlgorithm"), keyAsBytes("keyAsBytes");

    private final String myName;

    mapKey(String key) {
      this.myName = key;
    }

    private String getName() {
      return myName;
    }
  }

  class Parameter<T> {
    T value;

    Parameter(T value) {
      this.value = value;
    }
  }
}
