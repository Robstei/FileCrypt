package edu.junit5.quickstart.validation;

import java.util.HashMap;

/**
 * Registry of all validations implemented in FileCrypt.
 *
 * @author Robin Steil
 */
public class Validations {
  private final HashMap<String, Validation> availableValidations =
          new HashMap<>();

  /**
   * Instance with reference to every implemented validation algorithm
   */
  public Validations() {
    availableValidations.put(new SHA256().getBouncyCastleName(), new SHA256());
    availableValidations.put(new AESCMAC().getBouncyCastleName(),
                             new AESCMAC());
    availableValidations.put(new HMACSHA256().getBouncyCastleName(),
                             new HMACSHA256());
  }

  /**
   * Gets validation type.
   *
   * @param name the name
   * @return the validation type
   */
  public ValidationType getValidationType(String name) {
    return availableValidations.get(name).getValidationType();
  }

  /**
   * Gets key initializer.
   *
   * @param name the name
   * @return the key initializer
   */
  public String getKeyInitializer(String name) {
    return availableValidations.get(name).getKeyInitializer();
  }
}
