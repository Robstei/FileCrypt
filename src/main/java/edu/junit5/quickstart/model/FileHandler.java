package edu.junit5.quickstart.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

  public static <T extends AbstractCryptoData<T>> T fillDataContainer(
          T dataContainer,
          String path) {
    try {
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
    } catch (ParserConfigurationException | IOException | SAXException e) {
      throw new RuntimeException(e);
    }

  }

  public static void saveDataToXMLFile(String path,
                                       AbstractCryptoData<?
                                               extends AbstractCryptoData<?>>... abstractCryptoDataArray) {
    try {
      Document document =
              DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
      Element root = document.createElement("cryptoData");
      document.appendChild(root);

      for (AbstractCryptoData<?> abstractCryptoData : abstractCryptoDataArray) {
        for (Map.Entry<String, String> entry :
                abstractCryptoData.getValuesAsMap().entrySet()) {
          Element element = document.createElement(entry.getKey());
          element.appendChild(document.createTextNode(entry.getValue()));
          root.appendChild(element);
        }
      }
      Transformer transformer =
              TransformerFactory.newInstance().newTransformer();
      transformer.transform(new DOMSource(document),
                            new StreamResult(new File(path)));
    } catch (ParserConfigurationException | TransformerException e) {
      throw new RuntimeException(e);
    }
  }
}
