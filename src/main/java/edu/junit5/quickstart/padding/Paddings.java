package edu.junit5.quickstart.padding;

/**
 * Registry of all paddings implemented in FileCrypt.
 *
 * @author Robin Steil
 */
public class Paddings {

  final private Padding[] availablePaddings;

  /**
   * Instantiates a new Paddings.
   */
  public Paddings() {
    this.availablePaddings = new Padding[]{
            new NoPadding(),
            new PKCS7Padding(),
            new ZeroBytePadding(),
            new CTS(),
    };
  }

  /**
   * Get available paddings padding [ ].
   *
   * @return the padding [ ]
   */
  public Padding[] getAvailablePaddings() {
    return availablePaddings;
  }
}
