package edu.junit5.quickstart;

import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;

public class SymmetricEncryptionTest {

    @Test
    void simpleFunctionality() {
        Model model = new Model();
        Controller controller = new Controller(model);

        String path = new File("test").getAbsolutePath();
        System.out.println(path);
        byte[] plaintext = Hex.decode("f34481ec3cc627bacd5dc3fb08f273e6");
        byte[] keyBytes = Hex.decode("00000000000000000000000000000000");
        String ivString = "00000000000000000000000000000000";
        SecretKey key = new SecretKeySpec(keyBytes, "AES");
        byte[] encrpytedData = model.encryptSymmetric(plaintext, "AES", "CBC"
                , "NoPadding", key, ivString);

        byte[] supposedResult = Hex.decode("0336763e966d92595a567cc9ce537f5e");
        assertArrayEquals(encrpytedData, supposedResult);
    }
}
