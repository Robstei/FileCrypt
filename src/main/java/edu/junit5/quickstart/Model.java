package edu.junit5.quickstart;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Model {

    private static Model model;
    private BouncyCastleProvider bouncyCastleProvider =
            new BouncyCastleProvider();

    /**
     * @param algorithm the algorithm with which the file will be encrypted
     * @return A SecretKey to use during the encryption
     */

    private Model() {
    }

    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

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

    public void manageEncryptWithState(){
        Key key;
        State state = State.getState();
        byte input[] = fileAsByteArray(State.getState().getPath());
        try {
            key = KeyGenerator.getInstance("AES").generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] encryptedText = encryptWithState(input ,key);
        saveByteArrayAsFile(encryptedText, State.getState().getPath() + "_encrypted");
    }

    public byte[] encryptWithState(byte[] input, Key key) {
        State state = State.getState();
        String algorithm = state.getAlgorithm().getBouncyCastleName();
        String mode = state.getMode().getBouncyCastleName();
        String padding = state.getPadding().getBouncyCastleName();
        try {
            Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding, bouncyCastleProvider);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(input);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    protected void encryptEverything() {

        byte[] input = Hex.decode("0102030405060708");
        Key key;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            key = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        try {
            byte[] a = encryptECBwithPKCS7(input, key);
            byte[] b = decryptECBwithPKCS7(a, key);

            byte[] c = encryptCBCwithCTS(input, key);
            byte[] d = decryptCBCwithCTS(c, key);
            Util.printByteArrayAsTextToConsole(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected byte[] encryptECBwithPKCS7(byte[] clearText, Key key) throws
            NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding",
                bouncyCastleProvider);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(clearText);
    }

    protected byte[] decryptECBwithPKCS7(byte[] cipherText, Key key) throws
            NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding",
                bouncyCastleProvider);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }

    protected byte[] encryptCBCwithCTS(byte[] clearText, Key key) throws
            NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/CTSPaddingg",
                bouncyCastleProvider);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(clearText);
    }

    protected byte[] decryptCBCwithCTS(byte[] cipherText, Key key) throws
            NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/CTSPadding",
                bouncyCastleProvider);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }

    protected byte[] encryptOFB(byte[] clearText, Key key) throws
            NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/CTSPadding",
                bouncyCastleProvider);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(clearText);
    }

    protected byte[] decryptOFB(byte[] cipherText, Key key) throws
            NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/CTSPadding",
                bouncyCastleProvider);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }

    protected byte[] encryptSymmetric(byte[] input, String algorithm,
                                      String mode, String padding,
                                      SecretKey key, String ivString) {
        try {
            Cipher cipher =
                    Cipher.getInstance(algorithm + "/" + mode + "/" + padding,
                            bouncyCastleProvider);
            if (ivString != null && !mode.equals("ECB")) {
                byte[] ivBytes = Hex.decode(ivString);
                cipher.init(Cipher.ENCRYPT_MODE, key,
                        new IvParameterSpec(ivBytes));
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, key);
            }

            byte[] output = cipher.doFinal(input);
            return output;

        } catch (NoSuchAlgorithmException | InvalidKeyException
                 | NoSuchPaddingException | IllegalBlockSizeException
                 | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected byte[] decryptSymmetric(byte[] encryptedFileAsBytes,
                                      String algorithm, String mode,
                                      String padding, SecretKey key,
                                      String ivString) {
        byte[] output = null;
        try {
            Cipher cipher =
                    Cipher.getInstance(algorithm + "/" + mode + "/" + padding,
                            bouncyCastleProvider);

            if (ivString != null && !mode.equals("ECB")) {
                cipher.init(Cipher.DECRYPT_MODE, key,
                        new IvParameterSpec(Hex.decode(ivString)));
            } else {
                cipher.init(Cipher.DECRYPT_MODE, key);
            }
            output = cipher.doFinal(encryptedFileAsBytes);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                 InvalidKeyException | BadPaddingException |
                 IllegalBlockSizeException |
                 InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return output;
    }

//    void newEncrypt(String algorithm, String mode, String padding) {
//        Executor executor = getExecutor(algorithm, mode, padding);
//        //executor.init();
//        executor.encrypt();
//        executor.createCipherTextAsFile();
//        executor.createEncryptionMetaDataFile();
//    }

//    Executor getExecutor(String algorithm, String mode, String padding) {
//        if (algorithm.equals("AES") && mode.equals("ECB") && padding.equals(
//                "PKCS7Executor")) {
//            return new AESWithECBWithPKCS7Executor();
//        } else if (algorithm.equals("AES") && mode.equals("ECB") && padding.equals("ZeroBytePadding")) {
//            return new AESWithECBWithZeroBytePaddingExecutor();
//        } else if (algorithm.equals("AES") && mode.equals("OFB") && padding.equals("NoPadding")) {
//            return new AESWithOFBWithNoPaddingExecutor();
//        }
//        return null;
//    }
}