package edu.junit5.quickstart.signature;

import edu.junit5.quickstart.io.FileHandler;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.security.*;

/**
 * Tests for core functionality of the SignatureModel.
 *
 * @author Robin Steil
 */
public class SignatureModalTest {

  /**
   * Add provider.
   */
  @BeforeAll
  static void addProvider() {
    Security.insertProviderAt(new BouncyCastleProvider(), 1);
  }

  /**
   * Does signature match.
   *
   * @throws NoSuchAlgorithmException     the no such algorithm exception
   * @throws NoSuchProviderException      the no such provider exception
   * @throws SignatureException           the signature exception
   * @throws InvalidKeyException          the invalid key exception
   * @throws IOException                  the io exception
   * @throws ParserConfigurationException the parser configuration exception
   * @throws TransformerException         the transformer exception
   * @throws SAXException                 the sax exception
   */
  @Test
  public void doesSignatureMatch() throws NoSuchAlgorithmException,
          NoSuchProviderException, SignatureException, InvalidKeyException,
          IOException, ParserConfigurationException, TransformerException,
          SAXException {
    byte[] bytesToSign = FileHandler.getFileAsByteArray(
            "src/test/resources/signature/textfile.txt");
    SignatureModel signatureModel = new SignatureModel();
    signatureModel.generateKeys("SHA256withDSA");
    signatureModel.sign(bytesToSign);
    signatureModel.verify(signatureModel.getPublicSignatureData(),
                          signatureModel.getPublicSignatureKeyData());
    String pathToSaveAt = "src/test/resources/signature/";
    FileHandler.saveDataToXMLFile(pathToSaveAt + "publicSignatureData.xml",
                                  signatureModel.getPublicSignatureData());
    FileHandler.saveDataToXMLFile(pathToSaveAt + "publicSignatureKeyData.xml",
                                  signatureModel.getPublicSignatureKeyData());
    FileHandler.saveDataToXMLFile(pathToSaveAt + "secretSignatureKeyData.xml",
                                  signatureModel.getSecretSignatureKeyData());

    PublicSignatureData publicSignatureData = FileHandler.fillDataContainer(
            new PublicSignatureData(),
            pathToSaveAt + "publicSignatureData.xml");
    PublicSignatureKeyData publicSIgnatureKeyData =
            FileHandler.fillDataContainer(
                    new PublicSignatureKeyData(),
                    pathToSaveAt + "publicSignatureKeyData.xml");

    Assertions.assertTrue(
            signatureModel.verify(publicSignatureData, publicSIgnatureKeyData)
    );
  }


}
