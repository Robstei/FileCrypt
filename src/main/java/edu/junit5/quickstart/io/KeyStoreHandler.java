package edu.junit5.quickstart.io;

import edu.junit5.quickstart.signature.PublicSignatureKeyData;
import edu.junit5.quickstart.signature.SecretSignatureKeyData;
import edu.junit5.quickstart.symmetricEncryption.SecretEncryptionData;
import edu.junit5.quickstart.validation.SecretValidationData;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Objects;

/**
 * The type Key store handler.
 *
 * @author Robin Steil
 */
public class KeyStoreHandler {

  /**
   * Saves key in key store. Checks the file with the path pathToKey for its
   * content. The format of the file is supposed to be on of the xml formats
   * of fileCrypt. Might save multiple key in one call because file format
   * might contain multiple keys. For example the key file for symmetric
   * encryption contains the mac and the encryption key. Both will be saved
   * separately.
   * <p>
   * Creates new keyStore if providedPathToKeyStore is null in the same
   * directory as the key is.
   *
   * @param providedPathToKeyStore the provided path to key store
   * @param passwordForKeyStore    the password for key store
   * @param pathToKey              the path to key
   * @param identifierForKey       the identifier for key
   * @param passwordForKey         the password for key
   * @throws KeyStoreException            the key store exception
   * @throws NoSuchProviderException      the no such provider exception
   * @throws IOException                  the io exception
   * @throws CertificateException         the certificate exception
   * @throws NoSuchAlgorithmException     the no such algorithm exception
   * @throws ParserConfigurationException the parser configuration exception
   * @throws SAXException                 the sax exception
   */
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

    KeyStore keyStore = KeyStore.getInstance("BKS", "BC");

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
    pathToKeyStore = Objects.requireNonNullElse(providedPathToKeyStore,
                                                new File(
                                                        pathToKey).getParent() + "/keystore.keystore");
    try (FileOutputStream fileOutputStream = new FileOutputStream(
            pathToKeyStore)) {
      keyStore.store(fileOutputStream, passwordForKeyStore);
    }
  }

  /**
   * Creates key file from key store. key resulting file has a format of a
   * fileCrypt keyfile and can be used like a regular keyfile for the same
   * operation.
   *
   * @param pathToKeyStore      the path to key store
   * @param passwordForKeyStore the password for key store
   * @param identifier          the identifier
   * @param passwordForKey      the password for key
   * @throws KeyStoreException            the key store exception
   * @throws NoSuchProviderException      the no such provider exception
   * @throws IOException                  the io exception
   * @throws CertificateException         the certificate exception
   * @throws NoSuchAlgorithmException     the no such algorithm exception
   * @throws UnrecoverableKeyException    the unrecoverable key exception
   * @throws ParserConfigurationException the parser configuration exception
   * @throws TransformerException         the transformer exception
   */
  public static void createKeyFileFromKeyStore(String pathToKeyStore,
                                               char[] passwordForKeyStore,
                                               String identifier,
                                               char[] passwordForKey)
          throws KeyStoreException, NoSuchProviderException, IOException,
          CertificateException, NoSuchAlgorithmException,
          UnrecoverableKeyException, ParserConfigurationException,
          TransformerException {

    KeyStore keystore = KeyStore.getInstance("BKS", "BC");
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
