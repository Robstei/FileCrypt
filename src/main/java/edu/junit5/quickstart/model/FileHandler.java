package edu.junit5.quickstart.model;

import edu.junit5.quickstart.password.PublicPasswordData;
import edu.junit5.quickstart.state.Transformation;
import edu.junit5.quickstart.validation.PublicValidationData;
import edu.junit5.quickstart.validation.SecretValidationData;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class FileHandler {

  public static byte[] getFileAsByteArray(String path) {
    byte[] fileAsBytes = null;
    try (FileInputStream fileInputStream = new FileInputStream(path)) {
      fileAsBytes = fileInputStream.readAllBytes();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return fileAsBytes;
  }

  public static void saveByteArrayAsFile(byte[] array, String filePath) {
    try (FileOutputStream fileOutputStream = new FileOutputStream(
            filePath)) {
      fileOutputStream.write(array);
      fileOutputStream.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void savePublicData(byte[] encryptedBytes,
                                    PublicEncryptionData publicEncryptionData,
                                    PublicValidationData publicValidationData,
                                    String encryptedFilePath) {
    savePublicData(encryptedBytes, publicEncryptionData, publicValidationData,
                   null, encryptedFilePath);
  }

  //saves encryption data with public metadata as xml file
  //TODO remove bytes since they are in the publicMetaData object
  public static void savePublicData(byte[] encryptedBytes,
                                    PublicEncryptionData publicEncryptionData,
                                    PublicValidationData publicValidationData,
                                    PublicPasswordData publicPasswordData,
                                    String encryptedFilePath) {
    try {
      DocumentBuilderFactory documentBuilderFactory =
              DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder =
              documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.newDocument();

      Element publicData = document.createElement("publicData");
      document.appendChild(publicData);

      Element encryptedBytesElement = document.createElement("encryptedBytes");
      encryptedBytesElement.appendChild(
              document.createTextNode(Hex.toHexString(encryptedBytes)));
      publicData.appendChild(encryptedBytesElement);

      Element algorithm = document.createElement("algorithm");
      algorithm.appendChild(document.createTextNode(
              publicEncryptionData.getAlgorithm()));
      publicData.appendChild(algorithm);

      Element mode = document.createElement("mode");
      mode.appendChild(
              document.createTextNode(publicEncryptionData.getMode()));
      publicData.appendChild(mode);


      Element padding = document.createElement("padding");
      padding.appendChild(document.createTextNode(
              publicEncryptionData.getPadding()));
      publicData.appendChild(padding);

      Element algorithmParameters = document.createElement(
              "algorithmParameters");
      // might be null because the algorithm / mode combination does
      // not need one
      if (publicEncryptionData.getAlgorithmParameters() != null) {
        algorithmParameters.appendChild(document.createTextNode(
                Hex.toHexString(
                        publicEncryptionData.getAlgorithmParameters().getEncoded())));
      }
      publicData.appendChild(algorithmParameters);

      Element validationName = document.createElement("validationName");
      publicData.appendChild(validationName);
      Element validationComputedBytes = document.createElement(
              "validationComputedBytes");
      publicData.appendChild(validationComputedBytes);

      validationName.appendChild(document.createTextNode(
              publicValidationData.getName()));

      validationComputedBytes.appendChild(
              document.createTextNode(Hex.toHexString(
                      publicValidationData.getComputedBytes())));


      //write public password data
      Element passwordSalt = document.createElement("passwordSalt");
      publicData.appendChild(passwordSalt);
      Element passwordAlgorithmForParameter = document.createElement(
              "passwordAlgorithmForParameter");
      publicData.appendChild(passwordAlgorithmForParameter);


      if (publicPasswordData != null) {
        passwordSalt.appendChild(document.createTextNode(
                Hex.toHexString(publicPasswordData.getSalt())));
        passwordSalt.appendChild(document.createTextNode(
                publicPasswordData.getPasswordAlgorithmForParameter()));

      }

      TransformerFactory transformerFactory =
              TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      File file = new File(encryptedFilePath + ".encrypted");
      transformer.transform(new DOMSource(document), new StreamResult(file));
    } catch (ParserConfigurationException | IOException |
             TransformerException e) {
      throw new RuntimeException(e);
    }
  }

  public static void saveSecretData(
          SecretEncryptionData secretEncryptionData,
          SecretValidationData secretValidationData, String encryptedFilePath) {
    try {
      DocumentBuilderFactory documentBuilderFactory =
              DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder =
              documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.newDocument();

      Element secretData = document.createElement("secretData");
      document.appendChild(secretData);

      Element key = document.createElement("key");
      String keyAsString = Hex.toHexString(
              secretEncryptionData.getKey().getEncoded());
      key.appendChild(document.createTextNode(keyAsString));
      secretData.appendChild(key);

      Element keyAlgorithm = document.createElement("keyAlgorithm");
      secretData.appendChild(keyAlgorithm);
      keyAlgorithm.appendChild(document.createTextNode(
              secretEncryptionData.getKey().getAlgorithm()));

      Element validationKey = document.createElement("validationKey");
      secretData.appendChild(validationKey);

      Element validationAlgorithm = document.createElement(
              "validationAlgorithm");
      secretData.appendChild(validationAlgorithm);

      if (secretValidationData != null) {
        validationKey.appendChild(document.createTextNode(Hex.toHexString(
                secretValidationData.getKey().getEncoded())));
        validationAlgorithm.appendChild(
                document.createTextNode(
                        secretValidationData.getKey().getAlgorithm()));
      }

      TransformerFactory transformerFactory =
              TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      File file = new File(encryptedFilePath + ".keyfile");
      transformer.transform(new DOMSource(document),
                            new StreamResult(file));
    } catch (ParserConfigurationException | TransformerException e) {
      throw new RuntimeException(e);
    }
  }

  public static PublicEncryptionData getPublicEncryptionDataFromFile(
          String path) {
    try {
      DocumentBuilderFactory documentBuilderFactory =
              DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder =
              documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse(new File(path));
      byte[] encryptedBytes = Hex.decode(
              document.getElementsByTagName("encryptedBytes").item(
                      0).getTextContent());
      String algorithm = document.getElementsByTagName("algorithm").item(
              0).getTextContent();
      String mode = document.getElementsByTagName("mode").item(
              0).getTextContent();
      String padding = document.getElementsByTagName("padding").item(
              0).getTextContent();


      byte[] algorithmParametersAsBytes = Hex.decode(
              document.getElementsByTagName(
                      "algorithmParameters").item(0).getTextContent());
      AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance(
              algorithm, new BouncyCastleProvider());
      algorithmParameters.init(algorithmParametersAsBytes);

      Transformation transformation = new Transformation(algorithm, mode,
                                                         padding);

      return new PublicEncryptionData(encryptedBytes, transformation,
                                      algorithmParameters
      );
    } catch (ParserConfigurationException | NoSuchAlgorithmException |
             IOException | SAXException e) {
      throw new RuntimeException(e);
    }
  }

  public static PublicValidationData getPublicValidationDataFromFile(
          String path) {
    try {
      DocumentBuilderFactory documentBuilderFactory =
              DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder =
              documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse(new File(path));
      String validationName = document.getElementsByTagName(
              "validationName").item(0).getTextContent();
      byte[] validationBytes = Hex.decode(
              document.getElementsByTagName("validationComputedBytes").item(
                      0).getTextContent());
      PublicValidationData publicValidationData = new PublicValidationData(
              validationName, validationBytes);
      return publicValidationData;
    } catch (ParserConfigurationException | SAXException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static PublicPasswordData getPublicPasswordDataFromFile(String path) {
    try {

      DocumentBuilderFactory documentBuilderFactory =
              DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder =
              documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse(new File(path));

      byte[] salt = Hex.decode(
              document.getElementsByTagName("passwordSalt").item(
                      0).getTextContent());
      String passwordAlgorithmForParameter = document.getElementsByTagName(
              "passwordAlgorithmForParameter").item(
              0).getTextContent();

      PublicPasswordData publicPasswordData = new PublicPasswordData(salt,
                                                                     passwordAlgorithmForParameter);
      return publicPasswordData;
    } catch (ParserConfigurationException | SAXException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static SecretEncryptionData getSecretEncryptionDataFromFile(
          String path) {
    try {
      DocumentBuilderFactory documentBuilderFactory =
              DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder =
              documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse(new File(path));

      //ensure that the document hierarchy isn't affected by any extra
      // white spaces or new lines within nodes
      document.getDocumentElement().normalize();
      String keyAsString = document.getElementsByTagName("key").item(
              0).getTextContent();
      String keyAlgorithm = document.getElementsByTagName("keyAlgorithm").item(
              0).getTextContent();

      SecretKey key = new SecretKeySpec(Hex.decode(keyAsString),
                                        keyAlgorithm);
      return new SecretEncryptionData(key);

    } catch (ParserConfigurationException | SAXException | IOException
            e) {
      throw new RuntimeException(e);
    }
  }

  public static SecretValidationData getSecretValidationDataFromFile(
          String path) {
    try {
      DocumentBuilderFactory documentBuilderFactory =
              DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder =
              documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse(new File(path));

      byte[] validationKey = Hex.decode(document.getElementsByTagName(
              "validationKey").item(0).getTextContent());

      if (validationKey.length == 0) {
        return null;
      }

      String validationAlgorithm = document.getElementsByTagName(
              "validationAlgorithm").item(0).getTextContent();
      Key key = new SecretKeySpec(validationKey, validationAlgorithm);
      return new SecretValidationData(key);
    } catch (ParserConfigurationException | IOException | SAXException e) {
      throw new RuntimeException(e);
    }
  }
}
