package edu.junit5.quickstart.io;

import edu.junit5.quickstart.signature.PublicSignatureKeyData;
import edu.junit5.quickstart.signature.SecretSignatureKeyData;
import edu.junit5.quickstart.symmetricEncryption.SecretEncryptionData;
import edu.junit5.quickstart.validation.SecretValidationData;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class KeyStoreHandler {

  public static void saveKeyInKeyStore(String providedPathToKeyStore,
                                       char[] passwordForKeyStore,
                                       String pathToKey,
                                       String identifierForKey,
                                       char[] passwordForKey) throws
          KeyStoreException, NoSuchProviderException, IOException,
          CertificateException, NoSuchAlgorithmException,
          ParserConfigurationException, SAXException {

    Key secretEncryptionKey = null;
    Key secretValidationKey = null;
    Key privateSignatureKey = null;
    Key publicSignatureKey = null;


    if (FileHandler.doesXMLFileContainKeys(
            new SecretEncryptionData().getMapKeys(), pathToKey)) {
      SecretEncryptionData secretEncryptionData = FileHandler.fillDataContainer(
              new SecretEncryptionData(), pathToKey);
      secretEncryptionKey = secretEncryptionData.getKey();
    }
    if (FileHandler.doesXMLFileContainKeys(
            new SecretValidationData().getMapKeys(), pathToKey)) {
      SecretValidationData secretValidationData = FileHandler.fillDataContainer(
              new SecretValidationData(), pathToKey);
      secretValidationKey = secretValidationData.getKey();
    }
    if (FileHandler.doesXMLFileContainKeys(
            new SecretSignatureKeyData().getMapKeys(), pathToKey)) {
      SecretSignatureKeyData secretSignatureKeyData =
              FileHandler.fillDataContainer(
                      new SecretSignatureKeyData(), pathToKey);
    }
    if (FileHandler.doesXMLFileContainKeys(
            new PublicSignatureKeyData().getMapKeys(), pathToKey)) {
      PublicSignatureKeyData publicSignatureKeyData =
              FileHandler.fillDataContainer(
                      new PublicSignatureKeyData(), pathToKey);
    }

    KeyStore keyStore = KeyStore.getInstance("BCFKS", "BC");

    if (providedPathToKeyStore == null) {
      keyStore.load(null, null);
    } else {
      try (FileInputStream fileInputStream = new FileInputStream(
              providedPathToKeyStore)) {
        keyStore.load(fileInputStream, passwordForKeyStore);
      }
    }

    if (secretEncryptionKey != null) {
      keyStore.setKeyEntry(identifierForKey + "secretencryptionkey",
                           secretEncryptionKey,
                           passwordForKey, null);
    }

    if (secretValidationKey != null) {
      keyStore.setKeyEntry(identifierForKey + "secretvalidationkey",
                           secretValidationKey,
                           passwordForKey, null);
    }


    String pathToKeyStore;
    if (providedPathToKeyStore == null) {
      pathToKeyStore = new File(pathToKey).getParent() + "/keystore.bks";
    } else {
      pathToKeyStore = providedPathToKeyStore;
    }
    try (FileOutputStream fileOutputStream = new FileOutputStream(
            pathToKeyStore)) {
      keyStore.store(fileOutputStream, passwordForKeyStore);
    }
  }

  public static void createKeyFileFromKeyStore(String pathToKeyStore,
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

      if (keystore.containsAlias(identifier + "secretencryptionkey")
              && keystore.containsAlias(identifier + "secretvalidationkey")) {
        Key secretEncryptionKey = keystore.getKey(
                identifier + "secretencryptionkey", passwordForKey);
        SecretEncryptionData secretEncryptionData =
                new SecretEncryptionData().fill(
                        secretEncryptionKey);

        Key secretValidationKey = keystore.getKey(
                identifier + "secretvalidationkey", passwordForKey);
        SecretValidationData secretValidationData =
                new SecretValidationData().fill(
                        secretValidationKey);

        FileHandler.saveDataToXMLFile(
                new File(
                        pathToKeyStore).getParent() + "/fileCrypt" +
                        ".encryptionkey",
                secretEncryptionData,
                secretValidationData);
      }
    }
  }
}
