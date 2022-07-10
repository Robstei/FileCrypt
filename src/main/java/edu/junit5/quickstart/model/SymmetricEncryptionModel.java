package edu.junit5.quickstart.model;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class SymmetricEncryptionModel {
    protected void manageSymmetricEncryption(Properties properties) {
        FileHandler fileHandler = new FileHandler();
        byte[] fileAsByteArray = fileHandler.getFileAsByteArray(properties.getSymmetricEncryptionEncryptFilePath());
        String algorithm = properties.getSymmetricEncryptionAlgorithm();
        String mode = properties.getSymmetricEncryptionMode();
        String padding = properties.getSymmetricEncryptionPadding();
        try {
            Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding,
                    new BouncyCastleProvider());
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm, new BouncyCastleProvider());
            keyGenerator.init(Integer.parseInt(properties.getSymmetricEncryptionKeySize()));
            Key key = keyGenerator.generateKey();
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedFileAsByteArray = cipher.doFinal(fileAsByteArray);
            fileHandler.saveByteArrayAsFile(encryptedFileAsByteArray, properties.getSymmetricEncryptionEncryptFilePath() + ".fee");
            SymmetricEncryptionKey encryptionKey = new SymmetricEncryptionKey(key, algorithm, mode, padding);
            fileHandler.saveEncryptionKeyAsFile(encryptionKey, properties.getSymmetricEncryptionEncryptFilePath());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    protected void manageSymmetricDecryption(Properties properties) {
        try {
            FileHandler fileHandler = new FileHandler();
            byte[] bytesToDecrypt = fileHandler.getFileAsByteArray(properties.getSymmetricEncryptionDecryptFilePath());
            SymmetricEncryptionKey symmetricEncryptionKey = fileHandler.getSymmetricEncryptionKey(properties.getSymmetricEncryptionKeyFilePath());
            String algorithm = symmetricEncryptionKey.getAlgorithm();
            String mode = symmetricEncryptionKey.getMode();
            String padding = symmetricEncryptionKey.getPadding();
            Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding, new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, symmetricEncryptionKey.getKey());
            byte[] decryptedHexData = cipher.doFinal(bytesToDecrypt);
            fileHandler.saveByteArrayAsFile(decryptedHexData, properties.getSymmetricEncryptionDecryptFilePath() + ".decrypted");
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException |
                 NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
