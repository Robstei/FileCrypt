package edu.junit5.quickstart.symmetricEncryption;

import edu.junit5.quickstart.data.Transformation;
import edu.junit5.quickstart.io.FileHandler;
import edu.junit5.quickstart.state.State;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;

/**
 * Tests for core functionality of the EncryptionModel.
 *
 * @author Robin Steil
 */
public class SymmetricEncryptionModelTest {

  /**
   * Add provider.
   */
  @BeforeAll
  static void addProvider() {
    Security.insertProviderAt(new BouncyCastleProvider(), 1);
  }

  /**
   * Symmetric encryption modal test.
   *
   * @throws IOException                  the io exception
   * @throws GeneralSecurityException     the general security exception
   * @throws ParserConfigurationException the parser configuration
   *                                      exception
   * @throws TransformerException         the transformer exception
   * @throws SAXException                 the sax exception
   */
  @Test
  public void symmetricEncryptionModalTest() throws IOException,
          GeneralSecurityException,
          ParserConfigurationException, TransformerException, SAXException {

    State state = State.getInstance();

    SymmetricEncryptionModel symmetricEncryptionModel =
            new SymmetricEncryptionModel();

    byte[] bytesToEncrypt = FileHandler.getFileAsByteArray(
            "src/test/resources/symmetricEncryption/textfile.txt");

    PublicPreEncryptionData publicPreEncryptionData =
            new PublicPreEncryptionData(bytesToEncrypt,
                                        new Transformation(
                                                state.getSymmetricEncryptionAlgorithm(),
                                                state.getSymmetricEncryptionMode(),
                                                state.getSymmetricEncryptionPadding()),
                                        Integer.parseInt(
                                                state.getSymmetricEncryptionKeyLength()));
    symmetricEncryptionModel.manageSymmetricEncryption(publicPreEncryptionData);
    String basePathToSaveAt = "src/test/resources/symmetricEncryption/";

    FileHandler.saveDataToXMLFile(
            basePathToSaveAt + "publicPostEncryptionData.xml",
            symmetricEncryptionModel.getPublicPostEncryptionData());
    FileHandler.saveDataToXMLFile(basePathToSaveAt + "publicEncryptionData.xml",
                                  symmetricEncryptionModel.getSecretEncryptionData());

    PublicPostEncryptionData publicPostEncryptionData =
            FileHandler.fillDataContainer(
                    new PublicPostEncryptionData(),
                    basePathToSaveAt + "publicPostEncryptionData.xml");
    SecretEncryptionData secretEncryptionData = FileHandler.fillDataContainer(
            new SecretEncryptionData(),
            basePathToSaveAt + "publicEncryptionData.xml");

    symmetricEncryptionModel.manageSymmetricDecryption(publicPostEncryptionData,
                                                       secretEncryptionData);
    Assertions.assertArrayEquals(symmetricEncryptionModel.getResult(),
                                 bytesToEncrypt);
  }
}
