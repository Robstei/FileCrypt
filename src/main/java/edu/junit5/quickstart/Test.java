package edu.junit5.quickstart;

import org.bouncycastle.jcajce.spec.ScryptKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Provider;
import java.security.Security;

/**
 * The type Test.
 */
@SuppressWarnings("ALL")
public class Test {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws Exception the exception
   */
  public static void main(String[] args) throws Exception {

    Security.insertProviderAt(new BouncyCastleProvider(), 1);

    for (Provider provider : Security.getProviders()) {
      if (provider.getName().equals("BC")) {
        for (Provider.Service service : provider.getServices()) {
          if (service.getType().contains(
                  "AlgorithmParameterGenerator"))
            System.out.println(service.getAlgorithm());
        }
      }
    }

    ScryptKeySpec scryptKeySpec = new ScryptKeySpec(" ".toCharArray(),
                                                    Hex.decode(
                                                            "01020304050607080910111213141516"),
                                                    1048576, 8, 1, 256);

    Key key = SecretKeyFactory.getInstance(
            "SCRYPT").generateSecret(scryptKeySpec);


    Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");

    AlgorithmParameters algorithmParameters = null;
    cipher.init(Cipher.ENCRYPT_MODE, key, algorithmParameters);
    byte[] output = cipher.doFinal(Hex.decode(
            "0102030405060708091011121314151601020304050607080910111213141516"
    ));
    System.out.println(" ");

//    KeyStore keyStore = KeyStore.getInstance("BCFKS", "BC");
//    try (FileInputStream fileInputStream = new FileInputStream(
//            "/home/robin/Desktop/keystore.bks")) {
//      keyStore.load(fileInputStream, "a".toCharArray());
//    }
//    Key key = new SecretKeySpec(Hex.decode
//            ("11020304050607080910111213141516"),
//                                "HMACSHA256");
//    keyStore.setKeyEntry("wrongName4", key, "a".toCharArray(), null);
//    try (FileOutputStream fos = new FileOutputStream(
//            "/home/robin/Desktop/keystore.bks");
//    ) {
//      keyStore.store(fos, "a".toCharArray());
//    }
//
//    Key newKey = keyStore.getKey("wrongName4", "a".toCharArray());
//
//    SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(
//            "1.3.14.3.2.17", "BC");

//    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BC");
//    SecretKey secretKey = keyGenerator.generateKey();
//    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA",
//                                                                     "BC");
//    KeyPair keyPair = keyPairGenerator.generateKeyPair();
//    PublicKey publicKey = keyPair.getPublic();
//    PrivateKey privateKey = keyPair.getPrivate();
//
//    X500NameBuilder x500NameBld = new X500NameBuilder(BCStyle.INSTANCE)
//            .addRDN(BCStyle.C, "DE")
//            .addRDN(BCStyle.ST, "NRW")
//            .addRDN(BCStyle.L, "Essen")
//            .addRDN(BCStyle.O, "HSD")
//            .addRDN(BCStyle.CN, "Self Signed Demo Certificate");
//    X500Name name = x500NameBld.build();
//
//    X509v1CertificateBuilder certBldr = new JcaX509v1CertificateBuilder(
//            name,
//            BigInteger.valueOf(111111111),
//            new Date(10 * 60 * 60 * 24 * 20),
//            new Date(new Date().getTime() + (10 * 60 * 60 * 24 * 30)),
//            name,
//            keyPair.getPublic());
//
//    ContentSigner signer = new JcaContentSignerBuilder("SHA256withDSA")
//            .setProvider("BC").build(keyPair.getPrivate());
//
//    X509CertificateHolder x509CertificateHolder = certBldr.build(signer);
//
//    X509Certificate x509Certificate =
//            new JcaX509CertificateConverter().setProvider(
//                    "BC").getCertificate(x509CertificateHolder);
//    Certificate[] certificates = {x509Certificate};
//
//    KeyStore keyStore = KeyStore.getInstance("BCFKS", "BC");
//    keyStore.load(null, null);
//    keyStore.setKeyEntry("myKey", secretKey, "hallo2".toCharArray(), null);
//    keyStore.setKeyEntry("myKey3", privateKey, "hallo2".toCharArray(),
//                         certificates);
//    try (FileOutputStream fos = new FileOutputStream(
//            "/home/robin/Desktop/keystore.fks");
//    ) {
//      keyStore.store(fos, "hallo".toCharArray());
//    }
//
//
//    KeyStore keystore2 = KeyStore.getInstance("BCFKS", "BC");
//    try (FileInputStream fis = new FileInputStream(
//            "/home/robin/Desktop/keystore.fks")) {
//
//      keystore2.load(fis, "hallo".toCharArray());
//      Key key = keystore2.getKey("myKey", "hallo2".toCharArray());
//      Key key2 = keystore2.getKey("myKey3", "hallo2".toCharArray());
//
//      SecretKey secretKey1 = (SecretKey) key;
//      PrivateKey privateKey1 = (PrivateKey) key2;
//    }
  }
}
