package edu.junit5.quickstart;

public class Config {
    private static Config config;
    private Algorithm[] algorithms;

    private Config() {

    }

    public static Config getInstance() {
        if (config == null) {

            Padding zeroBytes = new Padding("ZeroBytes", "ZeroBytes");


            Mode ecb = new Mode("ECB", "ECB", false, true);
            Mode cbc = new Mode("CBC", "CBC", true, true);
            Mode ctr = new Mode("CTR", "CTR", true, true);
            Mode cfb = new Mode("CFB", "CFB", true, false);
            Mode ofb = new Mode("OFB", "OFB", true, false);

            Mode[] possibleModes = {cbc, ecb, ctr, cfb, ofb};
            Algorithm AES = new Algorithm("AES", "AES", possibleModes);
            config = new Config();
        }
        return config;
    }
}
