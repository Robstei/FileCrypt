package edu.junit5.quickstart.signature;

import edu.junit5.quickstart.algorithm.Algorithms;

import java.security.*;

/**
 * This class allows to sign, validate signatures and generates keyPairs for
 * signatures.
 *
 * @author Robin Steil
 */
public class SignatureModel {


  private PublicSignatureData publicSignatureData;
  private PublicSignatureKeyData publicSignatureKeyData;
  private SecretSignatureKeyData secretSignatureKeyData;

  /**
   * Generates keys and sets them in the instance fields
   * publicSignatureKeyData and secretSignatureKeyData.
   *
   * @param algorithm the algorithm
   * @throws NoSuchAlgorithmException the no such algorithm exception
   * @throws NoSuchProviderException  the no such provider exception
   */
  public void generateKeys(
          String algorithm) throws GeneralSecurityException {
    String nameForParameterGeneration =
            new Algorithms().getNameForParameterGeneration(
                    algorithm);
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(
            nameForParameterGeneration, "BC");

    // nist recommends 3072 Bit for 112 bit security Strength
    // https://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-57pt1r5.pdf
    int DSA_KEY_SIZE_IN_BIT = 3072;

    keyPairGenerator.initialize(DSA_KEY_SIZE_IN_BIT);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    publicSignatureKeyData = new PublicSignatureKeyData().fill(algorithm,
                                                               keyPair.getPublic());
    secretSignatureKeyData = new SecretSignatureKeyData().fill(algorithm,
                                                               keyPair.getPrivate());
  }

  /**
   * Sings the provided bytes with the provided secret signature data and
   * sets the result in the publicSignatureData.
   *
   * @param bytesToSign            the bytes to sign
   * @param secretSignatureKeyData the secret signature key data
   * @throws NoSuchAlgorithmException the no such algorithm exception
   * @throws NoSuchProviderException  the no such provider exception
   * @throws InvalidKeyException      the invalid key exception
   * @throws SignatureException       the signature exception
   */
  public void sign(byte[] bytesToSign,
                   SecretSignatureKeyData secretSignatureKeyData)
          throws GeneralSecurityException {
    Signature signature = Signature.getInstance(
            secretSignatureKeyData.getAlgorithm(), "BC");

    signature.initSign(secretSignatureKeyData.getPrivateKey());
    signature.update(bytesToSign);
    byte[] signatureAsBytes = signature.sign();
    publicSignatureData = new PublicSignatureData().fill(
            secretSignatureKeyData.getAlgorithm(), bytesToSign,
            signatureAsBytes);
  }

  /**
   * Sings the provided bytes with this instances secret signature data and
   * sets the result in the publicSignatureData.
   *
   * @param bytesToSign the bytes to sign
   * @throws NoSuchAlgorithmException the no such algorithm exception
   * @throws NoSuchProviderException  the no such provider exception
   * @throws InvalidKeyException      the invalid key exception
   * @throws SignatureException       the signature exception
   */
  public void sign(byte[] bytesToSign) throws GeneralSecurityException {
    sign(bytesToSign, secretSignatureKeyData);
  }

  /**
   * Verify if a given signature from the data container
   * secretSignatureKeyData is valid for the public key from
   * publicSignatureKeyData.
   *
   * @param publicSignatureData    the public signature data
   * @param publicSignatureKeyData the public signature key data
   * @return the boolean
   * @throws NoSuchAlgorithmException the no such algorithm exception
   * @throws NoSuchProviderException  the no such provider exception
   * @throws InvalidKeyException      the invalid key exception
   * @throws SignatureException       the signature exception
   */
  public boolean verify(PublicSignatureData publicSignatureData,
                        PublicSignatureKeyData publicSignatureKeyData)
          throws GeneralSecurityException {

    Signature signature = Signature.getInstance(
            publicSignatureData.getAlgorithm(), "BC");
    signature.initVerify(publicSignatureKeyData.getPublicKey());
    signature.update(publicSignatureData.getSignedBytes());
    return signature.verify(publicSignatureData.getSignature());
  }

  /**
   * Gets public signature data.
   *
   * @return the public signature data
   */
  public PublicSignatureData getPublicSignatureData() {
    return publicSignatureData;
  }

  /**
   * Gets public signature key data.
   *
   * @return the public signature key data
   */
  public PublicSignatureKeyData getPublicSignatureKeyData() {
    return publicSignatureKeyData;
  }

  /**
   * Gets secret signature key data.
   *
   * @return the secret signature key data
   */
  public SecretSignatureKeyData getSecretSignatureKeyData() {
    return secretSignatureKeyData;
  }
}
