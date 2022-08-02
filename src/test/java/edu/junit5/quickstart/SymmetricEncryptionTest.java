package edu.junit5.quickstart;

import edu.junit5.quickstart.data.Transformation;
import edu.junit5.quickstart.symmetricEncryption.SymmetricEncryptionModel;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * The type Symmetric encryption test.
 */
public class SymmetricEncryptionTest {
  /**
   * Gets known answers aes tests.
   *
   * @return the known answers aes tests
   * @throws IOException the io exception
   */
  static Stream<AESSingleTest> getKnownAnswersAESTests() throws IOException {
    File file = new File("src/test/resources/knownAnswer/AES");
    ArrayList<AESSingleTest> AESSingleTests = new ArrayList<>();
    for (String fileName : Objects.requireNonNull(file.list(),
                                                  "no test for AES found in " +
                                                          "src/test/resources" +
                                                          "/knownAnswer/AES")) {
      AESTestFile AESTestfile = new AESTestFile("src/test/resources" +
                                                        "/knownAnswer/AES/" + fileName);
      AESSingleTests.addAll(AESTestfile.getAESSingleTests());
    }
    return AESSingleTests.stream();
  }

  /**
   * Add provider.
   */
  @BeforeAll
  static void addProvider() {
    Security.insertProviderAt(new BouncyCastleProvider(), 1);
  }

  /**
   * Known answers aes.
   *
   * @param AESSingleTest the aes single test
   */
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
             InvalidParameterSpecException | NoSuchPaddingException |
             IllegalBlockSizeException | BadPaddingException |
             InvalidKeyException | InvalidAlgorithmParameterException e) {
      throw new RuntimeException(e);
    }
  }
}
