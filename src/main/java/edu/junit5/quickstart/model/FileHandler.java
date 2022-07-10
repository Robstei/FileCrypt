package edu.junit5.quickstart.model;

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

public class FileHandler {

    public byte[] getFileAsByteArray(String path) {
        byte[] fileAsBytes = null;
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            fileAsBytes = fileInputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileAsBytes;
    }

    public void saveByteArrayAsFile(byte[] array, String filePath) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(filePath);

            fileOutputStream.write(array);
            fileOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveEncryptionKeyAsFile(SymmetricEncryptionKey symmetricEncryptionKey, String encryptedFilePath) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        String keyAsString = Hex.toHexString(symmetricEncryptionKey.getKey().getEncoded());
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element encryptionData = document.createElement("encryptionData");
            document.appendChild(encryptionData);

            Element key = document.createElement("key");
            key.appendChild(document.createTextNode(keyAsString));
            encryptionData.appendChild(key);

            Element algorithm = document.createElement("algorithm");
            algorithm.appendChild(document.createTextNode(symmetricEncryptionKey.getAlgorithm()));
            encryptionData.appendChild(algorithm);

            Element mode = document.createElement("mode");
            mode.appendChild(document.createTextNode(symmetricEncryptionKey.getMode()));
            encryptionData.appendChild(mode);

            Element padding = document.createElement("padding");
            padding.appendChild(document.createTextNode(symmetricEncryptionKey.getPadding()));
            encryptionData.appendChild(padding);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            File file = new File(encryptedFilePath + ".fek");
            transformer.transform(new DOMSource(document), new StreamResult(file));
        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public SymmetricEncryptionKey getSymmetricEncryptionKey(String path) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(path));

            //ensure that the document hierarchy isn't affected by any extra white spaces or new lines within nodes
            document.getDocumentElement().normalize();
            String keyAsString = document.getElementsByTagName("key").item(0).getTextContent();
            String algorithm = document.getElementsByTagName("algorithm").item(0).getTextContent();
            String mode = document.getElementsByTagName("mode").item(0).getTextContent();
            String padding = document.getElementsByTagName("padding").item(0).getTextContent();
            SecretKey key = new SecretKeySpec(Hex.decode(keyAsString), algorithm);
            return new SymmetricEncryptionKey(key, algorithm, mode, padding);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}