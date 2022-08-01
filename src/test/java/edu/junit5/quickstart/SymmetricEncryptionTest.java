package edu.junit5.quickstart;

import edu.junit5.quickstart.data.Transformation;
import edu.junit5.quickstart.symmetricEncryption.SymmetricEncryptionModel;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;
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

  @BeforeAll
  static void addProvider() {
    Security.insertProviderAt(new BouncyCastleProvider(), 1);
  }

  //    @Test
//    void simpleFunctionality() {
//        Model model = Model.getInstance();
//        byte[] plaintext = Hex.decode("f34481ec3cc627bacd5dc3fb08f273e6");
//        byte[] keyBytes = Hex.decode("00000000000000000000000000000000");
//        String ivString = "00000000000000000000000000000000";
//        SecretKey key = new SecretKeySpec(keyBytes, "AES");
//        byte[] encryptedData = model.encryptSymmetric(plaintext, "AES", "CBC"
//                , "NoPadding", key, ivString);
//
//        byte[] supposedResult = Hex.decode
//        ("0336763e966d92595a567cc9ce537f5e");
//        assertArrayEquals(encryptedData, supposedResult);
//    }
//
  @ParameterizedTest
  @MethodSource("getKnownAnswersAESTests")
  void knownAnswersAES(AESSingleTest AESSingleTest) {
    try {

      if (AESSingleTest.getEncryptOrDecrypt() == Cipher.ENCRYPT_MODE && !AESSingleTest.getTransformation().getModeName().equals(
              "CFB1")) {

        byte[] input = AESSingleTest.getPlainTextAsBytes();

        SecretKey key =
                new SecretKeySpec(AESSingleTest.getKeyAsBytes(), "AES");

        Transformation transformation = AESSingleTest.getTransformation();

        AlgorithmParameters algorithmParameters = null;

        if (transformation.getNameForParameterGeneration() != null) {

          algorithmParameters = AlgorithmParameters.getInstance(
                  transformation.getNameForParameterGeneration(), "BC");

          algorithmParameters.init(
                  new IvParameterSpec(Hex.decode(AESSingleTest.getIv())));
        }

        SymmetricEncryptionModel symmetricEncryptionModel =
                new SymmetricEncryptionModel();

        byte[] encryptedData =
                symmetricEncryptionModel.encryptSymmetric(input,
                                                          transformation,
                                                          key,
                                                          algorithmParameters);
        byte[] supposedResult = AESSingleTest.getCipherTextAsBytes();

        Assertions.assertArrayEquals(supposedResult, encryptedData);
      } else if (AESSingleTest.getEncryptOrDecrypt() == Cipher.DECRYPT_MODE && !AESSingleTest.getTransformation().getModeName().equals(
              "CFB1")) {

        byte[] cipherTextAsBytes = AESSingleTest.getCipherTextAsBytes();

        Transformation transformation = AESSingleTest.getTransformation();

        SecretKey key =
                new SecretKeySpec(AESSingleTest.getKeyAsBytes(), "AES");

        AlgorithmParameters algorithmParameters = null;

        if (transformation.getNameForParameterGeneration() != null) {

          algorithmParameters = AlgorithmParameters.getInstance(
                  transformation.getNameForParameterGeneration(), "BC");

          algorithmParameters.init(
                  new IvParameterSpec(Hex.decode(AESSingleTest.getIv())));
        }

        SymmetricEncryptionModel symmetricEncryptionModel =
                new SymmetricEncryptionModel();
        byte[] decryptedData =
                symmetricEncryptionModel.decryptSymmetric(cipherTextAsBytes,
                                                          transformation, key,
                                                          algorithmParameters);
        byte[] supposedResult = AESSingleTest.getPlainTextAsBytes();

        Assertions.assertArrayEquals(supposedResult, decryptedData);
      }
    } catch (NoSuchAlgorithmException | NoSuchProviderException |
             InvalidParameterSpecException e) {
      throw new RuntimeException(e);
    }
  }
}
