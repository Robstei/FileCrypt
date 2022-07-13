package edu.junit5.quickstart.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Properties {

    private final StringProperty symmetricEncryptionAlgorithm;
    private final StringProperty symmetricEncryptionMode;
    private final StringProperty symmetricEncryptionPadding;
    private final StringProperty symmetricEncryptionKeySize;
    private final StringProperty symmetricEncryptionEncryptFilePath;
    private final StringProperty symmetricEncryptionDecryptFilePath;
    private final StringProperty SymmetricEncryptionKeyFilePath;

    private final StringProperty SymmetricEncryptionValidation;

    public Properties() {
        symmetricEncryptionAlgorithm = new SimpleStringProperty(Defaults.symmetricEncryptionAlgorithm);
        symmetricEncryptionMode = new SimpleStringProperty(Defaults.symmetricEncryptionMode);
        symmetricEncryptionPadding = new SimpleStringProperty(Defaults.symmetricEncryptionPadding);
        symmetricEncryptionKeySize = new SimpleStringProperty(Defaults.symmetricEncryptionKeySize);
        SymmetricEncryptionValidation = new SimpleStringProperty(Defaults.symmetricEncryptionValidation);

        symmetricEncryptionEncryptFilePath = new SimpleStringProperty();
        symmetricEncryptionDecryptFilePath = new SimpleStringProperty();
        SymmetricEncryptionKeyFilePath = new SimpleStringProperty();
    }

    public String getSymmetricEncryptionAlgorithm() {
        return symmetricEncryptionAlgorithm.get();
    }

    public StringProperty symmetricEncryptionAlgorithmProperty() {
        return symmetricEncryptionAlgorithm;
    }

    public String getSymmetricEncryptionMode() {
        return symmetricEncryptionMode.get();
    }

    public StringProperty symmetricEncryptionModeProperty() {
        return symmetricEncryptionMode;
    }

    public String getSymmetricEncryptionPadding() {
        return symmetricEncryptionPadding.get();
    }

    public StringProperty symmetricEncryptionPaddingProperty() {
        return symmetricEncryptionPadding;
    }

    public String getSymmetricEncryptionKeySize() {
        return symmetricEncryptionKeySize.get();
    }

    public StringProperty symmetricEncryptionKeySizeProperty() {
        return symmetricEncryptionKeySize;
    }

    public String getSymmetricEncryptionEncryptFilePath() {
        return symmetricEncryptionEncryptFilePath.get();
    }

    public void setSymmetricEncryptionEncryptFilePath(String filePath) {
        this.symmetricEncryptionEncryptFilePath.set(filePath);
    }

    public StringProperty filePathProperty() {
        return symmetricEncryptionEncryptFilePath;
    }

    public String getSymmetricEncryptionDecryptFilePath() {
        return symmetricEncryptionDecryptFilePath.get();
    }

    public void setSymmetricEncryptionDecryptFilePath(String symmetricEncryptionDecryptFilePath) {
        this.symmetricEncryptionDecryptFilePath.set(symmetricEncryptionDecryptFilePath);
    }

    public StringProperty symmetricEncryptionDecryptFilePathProperty() {
        return symmetricEncryptionDecryptFilePath;
    }

    public String getSymmetricEncryptionKeyFilePath() {
        return SymmetricEncryptionKeyFilePath.get();
    }

    public void setSymmetricEncryptionKeyFilePath(String symmetricEncryptionKeyFilePath) {
        this.SymmetricEncryptionKeyFilePath.set(symmetricEncryptionKeyFilePath);
    }

    public StringProperty symmetricEncryptionKeyFilePathProperty() {
        return SymmetricEncryptionKeyFilePath;
    }

    public String getSymmetricEncryptionValidation() {
        return SymmetricEncryptionValidation.get();
    }

    public void setSymmetricEncryptionValidation(String symmetricEncryptionValidation) {
        this.SymmetricEncryptionValidation.set(symmetricEncryptionValidation);
    }

    public StringProperty symmetricEncryptionValidationProperty() {
        return SymmetricEncryptionValidation;
    }
}
