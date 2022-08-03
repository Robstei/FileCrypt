package edu.junit5.quickstart.validation;

import edu.junit5.quickstart.io.FileHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Tests for core functionality of the ValidationModel.
 *
 * @author Robin Steil
 */
public class ValidationModelTest {

  /**
   * Validate macs.
   *
   * @param name the name
   * @throws NoSuchAlgorithmException     the no such algorithm exception
   * @throws InvalidKeyException          the invalid key exception
   * @throws ParserConfigurationException the parser configuration exception
   * @throws TransformerException         the transformer exception
   * @throws IOException                  the io exception
   * @throws SAXException                 the sax exception
   */
  @ParameterizedTest
  @ValueSource(strings = {"AESCMAC", "HMACSHA256"})
  public void validateMACS(
          String name) throws NoSuchAlgorithmException, InvalidKeyException,
          ParserConfigurationException, TransformerException, IOException,
          SAXException {
    ValidationModel validationModel = new ValidationModel();
    byte[] bytesToValidate = FileHandler.getFileAsByteArray(
            "src/test/resources/validation/textfile.txt");
    validationModel.generateValidation(bytesToValidate, name);

    String basePathToSaveAt = "src/test/resources/validation/";
    FileHandler.saveDataToXMLFile(basePathToSaveAt + "publicValidationData.xml",
                                  validationModel.getPublicValidationData());

    FileHandler.saveDataToXMLFile(basePathToSaveAt + "secretValidationData.xml",
                                  validationModel.getSecretValidationData());

    PublicValidationData publicValidationData = FileHandler.fillDataContainer(
            new PublicValidationData(),
            basePathToSaveAt + "publicValidationData.xml");
    SecretValidationData secretValidationData = FileHandler.fillDataContainer(
            new SecretValidationData(),
            basePathToSaveAt + "secretValidationData.xml");

    Assertions.assertTrue(
            validationModel.validate(bytesToValidate, publicValidationData,
                                     secretValidationData));
  }

  /**
   * Validate digests.
   *
   * @param name the name
   * @throws NoSuchAlgorithmException     the no such algorithm exception
   * @throws InvalidKeyException          the invalid key exception
   * @throws ParserConfigurationException the parser configuration exception
   * @throws TransformerException         the transformer exception
   * @throws IOException                  the io exception
   * @throws SAXException                 the sax exception
   */
  @ParameterizedTest
  @ValueSource(strings = {"SHA-256"})
  public void validateDigests(
          String name) throws NoSuchAlgorithmException, InvalidKeyException,
          ParserConfigurationException, TransformerException, IOException,
          SAXException {
    ValidationModel validationModel = new ValidationModel();
    byte[] bytesToValidate = FileHandler.getFileAsByteArray(
            "src/test/resources/validation/textfile.txt");
    validationModel.generateValidation(bytesToValidate, name);

    String basePathToSaveAt = "src/test/resources/validation/";
    FileHandler.saveDataToXMLFile(basePathToSaveAt + "publicValidationData.xml",
                                  validationModel.getPublicValidationData());

    PublicValidationData publicValidationData = FileHandler.fillDataContainer(
            new PublicValidationData(),
            basePathToSaveAt + "publicValidationData.xml");
    SecretValidationData secretValidationData = FileHandler.fillDataContainer(
            new SecretValidationData(),
            basePathToSaveAt + "secretValidationData.xml");

    Assertions.assertTrue(
            validationModel.validate(bytesToValidate, publicValidationData,
                                     secretValidationData));
  }
}
