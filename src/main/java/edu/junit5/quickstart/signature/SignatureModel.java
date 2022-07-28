package edu.junit5.quickstart.signature;

import edu.junit5.quickstart.algorithm.Algorithms;

import java.security.*;

public class SignatureModel {

  // nist recommends 3072 Bit for 112 bit security Strength
  // https://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-57pt1r5.pdf
  private final int DSA_KEY_SIZE_IN_BIT = 3072;

  private PublicSignatureData publicSignatureData;
  private PublicSignatureKeyData publicSignatureKeyData;
  private SecretSignatureKeyData secretSignatureKeyData;

  public void generateKeys(String algorithm) {
    try {
      String nameForParameterGeneration =
              new Algorithms().getNameForParameterGeneration(
                      algorithm);
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(
              nameForParameterGeneration, "BC");
      keyPairGenerator.initialize(DSA_KEY_SIZE_IN_BIT);
      KeyPair keyPair = keyPairGenerator.generateKeyPair();
      publicSignatureKeyData = new PublicSignatureKeyData().fill(algorithm,
                                                                 keyPair.getPublic());
      secretSignatureKeyData = new SecretSignatureKeyData().fill(algorithm,
                                                                 keyPair.getPrivate());
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    } catch (NoSuchProviderException e) {
      throw new RuntimeException(e);
    }
  }

  //TODO implement option to sign with existing public key
  public void sign(byte[] bytesToSign) {
    try {
      Signature signature = Signature.getInstance(
              secretSignatureKeyData.getAlgorithm(), "BC");

      signature.initSign(secretSignatureKeyData.getPrivateKey());
      signature.update(bytesToSign);
      byte[] signatureAsBytes = signature.sign();
      publicSignatureData = new PublicSignatureData().fill(
              secretSignatureKeyData.getAlgorithm(), bytesToSign,
              signatureAsBytes);
    } catch (NoSuchAlgorithmException | NoSuchProviderException |
             InvalidKeyException | SignatureException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean verify(PublicSignatureData publicSignatureData,
                        PublicSignatureKeyData publicSignatureKeyData) {
    try {
      Signature signature = Signature.getInstance(
              publicSignatureData.getAlgorithm(), "BC");
      signature.initVerify(publicSignatureKeyData.getPublicKey());
      signature.update(publicSignatureData.getSignature());
      return signature.verify(publicSignatureData.getSignature());
    } catch (NoSuchAlgorithmException | NoSuchProviderException |
             InvalidKeyException | SignatureException e) {
      throw new RuntimeException(e);
    }
  }


  public PublicSignatureData getPublicSignatureData() {
    return publicSignatureData;
  }

  public PublicSignatureKeyData getPublicSignatureKeyData() {
    return publicSignatureKeyData;
  }

  public SecretSignatureKeyData getSecretSignatureKeyData() {
    return secretSignatureKeyData;
  }
}
