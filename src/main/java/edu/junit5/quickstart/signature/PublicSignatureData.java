package edu.junit5.quickstart.signature;

import edu.junit5.quickstart.data.AbstractCryptoData;
import org.bouncycastle.util.encoders.Hex;

import java.util.HashMap;
import java.util.Map;

public class PublicSignatureData implements AbstractCryptoData<PublicSignatureData> {

  private static final String ALGORITHM = "signatureAlgorithm";
  private static final String SIGNED_BYTES = "signatureSignedBytes";
  private static final String SIGNATURE = "signatureSignature";
  private static final String[] XMLKeys = {ALGORITHM, SIGNED_BYTES, SIGNATURE};
  private String algorithm;
  private byte[] signedBytes;
  private byte[] signature;

  public PublicSignatureData fill(String algorithm, byte[] signedBytes,
                                  byte[] signature) {
    this.algorithm = algorithm;
    this.signedBytes = signedBytes;
    this.signature = signature;
    return this;
  }

  @Override
  public PublicSignatureData fillFromMap(Map<String, String> map) {
    String algorithm = map.get(ALGORITHM);
    byte[] signedBytes = Hex.decode(map.get(SIGNED_BYTES));
    byte[] signature = Hex.decode(map.get(SIGNATURE));
    return fill(algorithm, signedBytes, signature);
  }

  @Override
  public Map<String, String> getValuesAsMap() {
    Map<String, String> map = new HashMap<>();
    map.put(ALGORITHM, algorithm);
    map.put(SIGNED_BYTES, Hex.toHexString(signedBytes));
    map.put(SIGNATURE, Hex.toHexString(signature));
    return map;
  }

  public String getAlgorithm() {
    return algorithm;
  }

  public byte[] getSignedBytes() {
    return signedBytes;
  }

  public byte[] getSignature() {
    return signature;
  }

  @Override
  public String[] getMapKeys() {
    return XMLKeys;
  }
}
