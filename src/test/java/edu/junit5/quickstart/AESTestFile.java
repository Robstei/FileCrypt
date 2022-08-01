package edu.junit5.quickstart;

import edu.junit5.quickstart.data.Transformation;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AESTestFile {

  private ArrayList<AESSingleTest> AESSingleTests = new ArrayList<>();
  private String padding = "NoPadding";
  private String mode;

  public AESTestFile(String path) throws IOException {
    setTestValues(path);
  }

  private void setTestValues(String path) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(
            path));

    String line;
    int lineCounter = 1;
    int encryptOrDecrypt = -1;
    String plainText = " ", key = " ", iv = " ", cipherText = " ";


    while ((line = bufferedReader.readLine()) != null) {
      String[] lineAsArray = line.split(" ");
      if (lineCounter == 3) {
        mode = lineAsArray[lineAsArray.length - 1];
      }

      if (line.contains("ENCRYPT")) {
        encryptOrDecrypt = Cipher.ENCRYPT_MODE;
      } else if (line.contains("DECRYPT")) {
        encryptOrDecrypt = Cipher.DECRYPT_MODE;
      }

      switch (lineAsArray[0]) {
        case "KEY":
          key = lineAsArray[2];
          break;
        case "IV":
          iv = lineAsArray[2];
          break;
        case "PLAINTEXT":
          plainText = lineAsArray[2];
          if (encryptOrDecrypt == Cipher.DECRYPT_MODE) {
            AESSingleTests.add(new AESSingleTest(plainText,
                                                 new Transformation("AES", mode,
                                                                    padding),
                                                 key,
                                                 iv,
                                                 cipherText,
                                                 encryptOrDecrypt));
          }
          break;
        case "CIPHERTEXT":
          cipherText = lineAsArray[2];
          if (encryptOrDecrypt == Cipher.ENCRYPT_MODE) {
            AESSingleTests.add(new AESSingleTest(plainText,
                                                 new Transformation("AES", mode,
                                                                    padding),
                                                 key,
                                                 iv,
                                                 cipherText,
                                                 encryptOrDecrypt));
          }
          break;
      }
      lineCounter++;
    }
  }

  public ArrayList<AESSingleTest> getAESSingleTests() {
    return AESSingleTests;
  }
}
