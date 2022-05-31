package edu.junit5.quickstart;

import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class SymmetricEncryptionTest {

    static Stream<AESSingleTest> getKnownAnswersAESTests() throws IOException {
        File file = new File("src/test/resources/knownAnswer/AES");
        ArrayList<AESSingleTest> AESSingleTests = new ArrayList<>();
        for (String fileName : file.list()) {
            AESTestFile AESTestfile = new AESTestFile("src/test/resources" +
                    "/knownAnswer/AES/" + fileName);
            AESSingleTests.addAll(AESTestfile.getAESSingleTests());
        }
        /**
         for (int i = 0; i < 1; i++) {
         AESSingleTests.remove(1);
         }
         */
        return AESSingleTests.stream();

    }

    @Test
    void simpleFunctionality() {
        Model model = Model.getInstance();
        byte[] plaintext = Hex.decode("f34481ec3cc627bacd5dc3fb08f273e6");
        byte[] keyBytes = Hex.decode("00000000000000000000000000000000");
        String ivString = "00000000000000000000000000000000";
        SecretKey key = new SecretKeySpec(keyBytes, "AES");
        byte[] encryptedData = model.encryptSymmetric(plaintext, "AES", "CBC"
                , "NoPadding", key, ivString);

        byte[] supposedResult = Hex.decode("0336763e966d92595a567cc9ce537f5e");
        assertArrayEquals(encryptedData, supposedResult);
    }

    @ParameterizedTest
    @MethodSource("getKnownAnswersAESTests")
    void knownAnswersAES(AESSingleTest AESSingleTest) throws IOException {

        Model model = Model.getInstance();

        if (AESSingleTest.getEncryptOrDecrypt() == Cipher.ENCRYPT_MODE && !AESSingleTest.getMode().equals("CFB1")) {

            byte[] input = AESSingleTest.getPlainTextAsBytes();
            String blockMode = AESSingleTest.getMode();
            SecretKey key =
                    new SecretKeySpec(AESSingleTest.getKeyAsBytes(), "AES");
            String ivString = AESSingleTest.getIv();

            byte[] encryptedData =
                    model.encryptSymmetric(input,
                            "AES", blockMode, "NoPadding",
                            new SecretKeySpec(AESSingleTest.getKeyAsBytes(),
                                    "AES"), AESSingleTest.getIv());
            byte[] supposedResult = AESSingleTest.getCipherTextAsBytes();


            assertArrayEquals(supposedResult, encryptedData);
        } else if (AESSingleTest.getEncryptOrDecrypt() == Cipher.DECRYPT_MODE && !AESSingleTest.getMode().equals("CFB1")) {
            byte[] cipherTextAsBytes = AESSingleTest.getCipherTextAsBytes();
            String blockMode = AESSingleTest.getMode();
            SecretKey key =
                    new SecretKeySpec(AESSingleTest.getKeyAsBytes(), "AES");
            String ivString = AESSingleTest.getIv();

            byte[] decryptedData =
                    model.decryptSymmetric(cipherTextAsBytes,
                            "AES", blockMode, "NoPadding",
                            new SecretKeySpec(AESSingleTest.getKeyAsBytes(),
                                    "AES"), AESSingleTest.getIv());
            byte[] supposedResult = AESSingleTest.getPlainTextAsBytes();

            assertArrayEquals(supposedResult, decryptedData);
        }
    }
}
