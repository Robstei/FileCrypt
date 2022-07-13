package edu.junit5.quickstart.validation;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Validator {

    private ValidationParams generateValidationWithDigest(byte[] bytesToGenerateValidationFor, String validationName) {
        ValidationParams validationParams;
        MessageDigest digest;
        byte[] result;
        try {
            digest = MessageDigest.getInstance(validationName, new BouncyCastleProvider());
            digest.update(bytesToGenerateValidationFor);
            result = digest.digest();
            validationParams = new ValidationParams(validationName, result, null);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return validationParams;
    }

    private ValidationParams generateValidationWithMac(byte[] bytesToGenerateValidationFor, String validationName) {
        ValidationParams validationParams;
        Mac mac;
        byte[] result;
        try {
            mac = Mac.getInstance(validationName, new BouncyCastleProvider());
            KeyGenerator keyGenerator = KeyGenerator.getInstance(validationName);
            Key key = keyGenerator.generateKey();
            mac.init(key);
            result = mac.doFinal(bytesToGenerateValidationFor);
            validationParams = new ValidationParams(validationName, result, key);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return validationParams;
    }

    public ValidationParams generateValidation(byte[] bytesToGenerateValidationFor, String validationName) {
        ValidationParams validationParams = null;
        Validations validations = new Validations();
        if (validations.getValidationType(validationName) == ValidationType.DIGGEST) {
            validationParams = generateValidationWithDigest(bytesToGenerateValidationFor, validationName);
        } else if (validations.getValidationType(validationName) == ValidationType.MAC) {
            validationParams = generateValidationWithMac(bytesToGenerateValidationFor, validationName);
        }
        return validationParams;
    }

    private boolean validateWithDigest(byte[] bytesToValidate, ValidationParams validationParams) {
        byte[] bytesToValidateAfterComputation;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(validationParams.getName());
            bytesToValidateAfterComputation = messageDigest.digest(bytesToValidate);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return Arrays.constantTimeAreEqual(bytesToValidateAfterComputation, validationParams.getComputedBytes());
    }

    private boolean validateWithMac(byte[] bytesToValidate, ValidationParams validationParams) {
        try {
            Mac mac = Mac.getInstance(validationParams.getName());
            mac.init(validationParams.getKey());
            byte[] result = mac.doFinal(bytesToValidate);
            return Arrays.constantTimeAreEqual(result, validationParams.getComputedBytes());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validate(byte[] bytesToValidate, ValidationParams validationParams) {
        boolean valid = false;
        Validations validations = new Validations();
        if (validations.getValidationType(validationParams.getName()) == ValidationType.DIGGEST) {
            valid = validateWithDigest(bytesToValidate, validationParams);
        } else if (validations.getValidationType(validationParams.getName()) == ValidationType.MAC) {
            valid = validateWithMac(bytesToValidate, validationParams);
        }
        return valid;
    }
}
