package edu.junit5.quickstart.io;

import edu.junit5.quickstart.data.CryptoData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

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
import java.util.HashMap;
import java.util.Map;

/**
 * This class offers static methods to save and load data container
 * (instances of CryptoData) and regular files.
 *
 * @author Robin Steil
 */
public class FileHandler {

  /**
   * Converts the file with the given path as a byte array.
   *
   * @param path path of the file
   * @return file with the given path as a byte array
   */
  public static byte[] getFileAsByteArray(String path) throws IOException {
    byte[] fileAsBytes;
    try (FileInputStream fileInputStream = new FileInputStream(path)) {
      fileAsBytes = fileInputStream.readAllBytes();
    }
    return fileAsBytes;
  }

  /**
   * Saves given byte array as file with given path.
   *
   * @param array    the byte array to save as a file
   * @param filePath the path of the new file
   */
  public static void saveByteArrayAsFile(byte[] array,
                                         String filePath) throws IOException {
    try (FileOutputStream fileOutputStream = new FileOutputStream(
            filePath)) {
      fileOutputStream.write(array);
      fileOutputStream.flush();
    }
  }


  /**
   * Checks whether an XML file contains every key as tag.
   *
   * @param keys the keys as String array to check for
   * @param path the path of the file to check
   * @return true if file contains every value in keys as tag, false otherwise
   * @throws ParserConfigurationException the parser configuration exception
   * @throws IOException                  the io exception
   * @throws SAXException                 the sax exception
   */
  public static boolean doesXMLFileContainKeys(String[] keys,
                                               String path) throws ParserConfigurationException, IOException, SAXException {
    Document document =
            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
                    new File(path));
    for (String key : keys) {
      Node node = document.getElementsByTagName(key).item(0);
      if (node == null) {
        return false;
      }
    }
    return true;
  }

  /**
   * Fills a data container with the values of an XML file with the given path.
   *
   * @param <T>           the type of the data container which also
   *                      implements CryptoData
   * @param dataContainer the data container instance
   * @param path          the path of the XMl file
   * @return the data container instance with values set from the XML file
   */
  public static <T extends CryptoData<T>> T fillDataContainer(
          T dataContainer,
          String path) throws ParserConfigurationException, IOException,
          SAXException {
    Document document =
            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
                    new File(path));
    String[] keys = dataContainer.getMapKeys();
    Map<String, String> map = new HashMap<>();
    for (String key : keys) {
      String value = document.getElementsByTagName(key).item(
              0).getTextContent();
      map.put(key, value);
    }
    return dataContainer.fillFromMap(map);

  }

  /**
   * Saves data to an XML file.
   *
   * @param path            the path of the XML file
   * @param cryptoDataArray an array of all crypto data container that are
   *                        supposed to be saved in the file
   */
  public static void saveDataToXMLFile(String path,
                                       CryptoData<?
                                               extends CryptoData<?>>... cryptoDataArray) throws ParserConfigurationException, TransformerException {
    Document document =
            DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    Element root = document.createElement("cryptoData");
    document.appendChild(root);

    for (CryptoData<?> cryptoData : cryptoDataArray) {
      for (Map.Entry<String, String> entry :
              cryptoData.getValuesAsMap().entrySet()) {
        Element element = document.createElement(entry.getKey());
        element.appendChild(document.createTextNode(entry.getValue()));
        root.appendChild(element);
      }
    }
    Transformer transformer =
            TransformerFactory.newInstance().newTransformer();
    transformer.transform(new DOMSource(document),
                          new StreamResult(new File(path)));
  }
}
