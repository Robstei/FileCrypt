package edu.junit5.quickstart;

import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Arrays;

import static edu.junit5.quickstart.Util.printByteArrayAsTextToConsole;

public class Controller {
    private final Model model;

    Controller(Model model) {
        this.model = model;
    }

    public byte[] fileAsByteArray(String path) {
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

    protected void encryptFile(String algorithm, String mode, String padding,
                               String filepath) {
        SecretKey key = model.createKey(algorithm);
        byte[] input = fileAsByteArray(filepath);

        byte[] output = model.encryptAES(input, mode, padding, key);
        saveByteArrayAsFile(output, filepath + "_alt");
        saveByteArrayAsFile(key.getEncoded(), filepath + "_key");
    }

    protected void decryptFile(String algorithm, String mode, String padding,
                               String filepath, String keypath) {
        try (FileInputStream fileInputStream = new FileInputStream(filepath);
             FileInputStream fileInputStreamKey =
                     new FileInputStream(keypath)) {

            byte[] keyAsByteArray = new byte[fileInputStreamKey.available()];
            fileInputStreamKey.read(keyAsByteArray);
            SecretKey key = new SecretKeySpec(keyAsByteArray, algorithm);

            byte[] encryptedFileAsBytes = new byte[fileInputStream.available()];
            fileInputStream.read(encryptedFileAsBytes);

            byte[] output = model.decryptAES(encryptedFileAsBytes, mode,
                    padding, key);
            saveByteArrayAsFile(output, filepath + "_decrypted");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
