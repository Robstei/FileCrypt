package edu.junit5.quickstart;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Model {

    /**
     * @param algorithm the algorithm with which the file will be encrypted
     * @return A SecretKey to use during the encryption
     */
    protected SecretKey createKey(String algorithm) {
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(algorithm,
                    new BouncyCastleProvider());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    protected byte[] encryptSymmetric(byte[] input, String algorithm,
                                      String mode, String padding,
                                      SecretKey key) {
        try {
            Cipher cipher =
                    Cipher.getInstance(algorithm + "/" + mode + "/" + padding,
                            new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] output = cipher.doFinal(input);
            return output;

        } catch (NoSuchAlgorithmException | InvalidKeyException
                 | NoSuchPaddingException | IllegalBlockSizeException
                 | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected byte[] decryptSymmetric(byte[] encryptedFileAsBytes,
                                      String algorithm, String mode,
                                      String padding, SecretKey key) {
        byte[] output = null;
        try {
            Cipher cipher =
                    Cipher.getInstance(algorithm + "/" + mode + "/" + padding,
                            new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, key);
            output = cipher.doFinal(encryptedFileAsBytes);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                 InvalidKeyException | BadPaddingException |
                 IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return output;
    }
}
