package edu.junit5.quickstart;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class KeyStoreModal {

  public void saveKeyInKeyStore(String pathToKey, char[] passwordForKeyStore,
                                char[] passwordForKey) {
  }

  public void createKeyFileFromKeyStore(String pathToKeyStore,
                                        char[] passwordForKeyStore,
                                        String identifier,
                                        char[] passwordForKey)
          throws KeyStoreException, NoSuchProviderException, IOException,
          CertificateException, NoSuchAlgorithmException,
          UnrecoverableKeyException {

    KeyStore keystore = KeyStore.getInstance("BCFKS", "BC");
    try (FileInputStream fis = new FileInputStream(
            pathToKeyStore)) {

      keystore.load(fis, passwordForKeyStore);
      Key key = keystore.getKey(identifier, passwordForKey);

    }
  }
}
