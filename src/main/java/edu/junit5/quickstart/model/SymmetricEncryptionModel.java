package edu.junit5.quickstart.model;

import edu.junit5.quickstart.validation.ValidationParams;
import edu.junit5.quickstart.validation.Validator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
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

            Validator validator = new Validator();
            ValidationParams validationParams = validator.generateValidation(encryptedFileAsByteArray, properties.getSymmetricEncryptionValidation());

            fileHandler.saveByteArrayAsFile(encryptedFileAsByteArray, properties.getSymmetricEncryptionEncryptFilePath() + ".fee");


            byte[] iv = cipher.getIV();
            SymmetricEncryptionKey encryptionKey = new SymmetricEncryptionKey(key, algorithm, mode, padding, validationParams, iv);
            fileHandler.saveEncryptionKeyAsFile(encryptionKey, properties.getSymmetricEncryptionEncryptFilePath());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    protected OperationResult manageSymmetricDecryption(Properties properties) {
        try {
            FileHandler fileHandler = new FileHandler();
            byte[] bytesToDecrypt = fileHandler.getFileAsByteArray(properties.getSymmetricEncryptionDecryptFilePath());
            SymmetricEncryptionKey symmetricEncryptionKey = fileHandler.getSymmetricEncryptionKey(properties.getSymmetricEncryptionKeyFilePath());

            Validator validator = new Validator();
            boolean valid = validator.validate(bytesToDecrypt, symmetricEncryptionKey.getValidationParams());
            if (!valid) {
                return new OperationResult(false, "Did not decrpyt file. File validation failed. The file was changed.");
            }

            String algorithm = symmetricEncryptionKey.getAlgorithm();
            String mode = symmetricEncryptionKey.getMode();
            String padding = symmetricEncryptionKey.getPadding();
            byte[] iv = symmetricEncryptionKey.getIv();

            Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding, new BouncyCastleProvider());
            if (iv.length > 0) {
                cipher.init(Cipher.DECRYPT_MODE, symmetricEncryptionKey.getKey(), new IvParameterSpec(iv));
            } else {
                cipher.init(Cipher.DECRYPT_MODE, symmetricEncryptionKey.getKey());
            }
            byte[] decryptedHexData = cipher.doFinal(bytesToDecrypt);
            fileHandler.saveByteArrayAsFile(decryptedHexData, properties.getSymmetricEncryptionDecryptFilePath() + ".decrypted");
            return new OperationResult(true);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException |
                 NoSuchPaddingException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }
}
