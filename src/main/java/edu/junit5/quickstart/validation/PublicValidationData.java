package edu.junit5.quickstart.validation;

public class PublicValidationData {

  private final String name;
  private final byte[] computedBytes;

  public PublicValidationData(String name, byte[] computedBytes) {
    this.name = name;
    this.computedBytes = computedBytes;
  }

  public String getName() {
    return name;
  }

  public byte[] getComputedBytes() {
    return computedBytes;
  }
}
