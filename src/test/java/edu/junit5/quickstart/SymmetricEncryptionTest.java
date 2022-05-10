package edu.junit5.quickstart;

import org.bouncycastle.crypto.engines.SM2Engine;
import org.junit.jupiter.api.Test;

import java.io.File;

public class SymmetricEncryptionTest {

    @Test
    void simpleFunctionality() {
        Model model = new Model();
        Controller controller = new Controller(model);

        String path = new File("test").getAbsolutePath();
        System.out.println(path);
        //controller.encryptFile("AES", "CBC", "ISO7816-4Padding", "text.txt");
    }
}
