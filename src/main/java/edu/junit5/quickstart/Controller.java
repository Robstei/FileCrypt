package edu.junit5.quickstart;

import edu.junit5.quickstart.model.Model;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;

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
}
