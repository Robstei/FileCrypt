package edu.junit5.quickstart.model;

import edu.junit5.quickstart.mode.Mode;
import edu.junit5.quickstart.mode.Modes;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class Model {

    private static Model model;
    private final Properties properties = new Properties();
    private final Modes modes = new Modes();
    private BouncyCastleProvider bouncyCastleProvider =
            new BouncyCastleProvider();

    private Model() {
    }

    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public Properties getProperties() {
        return properties;
    }

    public Mode getModeByKey(String key) {
        return modes.getModeByKey(key);
    }

    public SecretKey createKey(String algorithm) {
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

    public void manageSymmetricEncryption() {
        SymmetricEncryptionModel symmetricEncryptionModel = new SymmetricEncryptionModel();
        symmetricEncryptionModel.manageSymmetricEncryption(properties);
    }

    public OperationResult manageSymmetricDecryption() {
        SymmetricEncryptionModel symmetricEncryptionModel = new SymmetricEncryptionModel();
        return symmetricEncryptionModel.manageSymmetricDecryption(properties);
    }
}