package edu.junit5.quickstart.learning;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.Provider;
import java.security.Security;

public class Test {
  public static void main(String[] args) throws Exception {

    Security.addProvider(new BouncyCastleProvider());

    for (Provider provider : Security.getProviders()) {
      if (provider.getName().equals("BC")) {
        for (Provider.Service service : provider.getServices()) {
          if (service.getType().contains(
                  "AlgorithmParameters"))
            System.out.println(service.getAlgorithm());
        }
      }
    }

    AlgorithmParameterGenerator gen = AlgorithmParameterGenerator.getInstance(
            "AES");
    AlgorithmParameters params = gen.generateParameters();
    byte[] paramAsBytes = params.getEncoded();

    AlgorithmParameters params2 = AlgorithmParameters.getInstance("AES",
                                                                  new BouncyCastleProvider());
    params2.init(paramAsBytes);
    System.out.println("jallo");

  }
}
