package edu.junit5.quickstart;

public class Config {

    private static ConfigNode config;

    private static void loadConfig() {
        ConfigEnum[][][] configArray = {
                {
                        {Algorithm.AES},
                        {KeySize.128,KeySize.192,KeySize.256},
                        {Mode.CBC, Mode.ECB},
                        {Padding.ISO7816_4Padding, Pading.NoPadding}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"ECB", "(ECB) Electronic Code Book"},
                        {"ISO10126-2Padding", "ISO10126-2Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"ECB", "(ECB) Electronic Code Book"},
                        {"PKCS5Padding", "PKCS5Padding/PKCS7Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"ECB", "(ECB) Electronic Code Book"},
                        {"TBCPadding", "TBCPadding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"ECB", "(ECB) Electronic Code Book"},
                        {"X9.23Padding", "X9.23Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"ECB", "(ECB) Electronic Code Book"},
                        {"ZeroBytePadding", "ZeroBytePadding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CBC", "CBC (Cipher Block Chaining)"},
                        {"ISO7816-4Padding", "ISO7816-4Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CBC", "CBC (Cipher Block Chaining)"},
                        {"ISO10126-2Padding", "ISO10126-2Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CBC", "CBC (Cipher Block Chaining)"},
                        {"PKCS5Padding", "PKCS5Padding/PKCS7Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CBC", "CBC (Cipher Block Chaining)"},
                        {"TBCPadding", "TBCPadding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CBC", "CBC (Cipher Block Chaining)"},
                        {"X9.23Padding", "X9.23Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CBC", "CBC (Cipher Block Chaining)"},
                        {"ZeroBytePadding", "ZeroBytePadding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CBC", "CBC (Cipher Block Chaining)"},
                        {"CTSPadding", "CTSPadding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CTR", "CTR (Counter mode)"},
                        {"NoPadding", "NoPadding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CFB", "CFB (Cipher FeedBack mode)"},
                        {"NoPadding", "NoPadding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"OFG", "Output Feedback mode"},
                        {"NoPadding", "NoPadding"}
                },
                {
                        {"ARC4.", "ARC4."}
                },
                {
                        {"ChaCha7539", "ChaCha7539"}
                }
        };

        config = new ConfigNode("root", "root", null);

        for (String[][] validConfigCombination : configArray) {
            config.addValidConfigCombination(validConfigCombination);
        }
    }

    public static ConfigNode getConfig() {
        if (config == null) {
            loadConfig();
        }
        return config;
    }
}



/*
rivate static void loadConfig() {
        String[][][] configArray = {
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"ECB", "(ECB) Electronic Code Book"},
                        {"ISO7816-4Padding", "ISO7816-4Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"ECB", "(ECB) Electronic Code Book"},
                        {"ISO10126-2Padding", "ISO10126-2Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"ECB", "(ECB) Electronic Code Book"},
                        {"PKCS5Padding", "PKCS5Padding/PKCS7Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"ECB", "(ECB) Electronic Code Book"},
                        {"TBCPadding", "TBCPadding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"ECB", "(ECB) Electronic Code Book"},
                        {"X9.23Padding", "X9.23Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"ECB", "(ECB) Electronic Code Book"},
                        {"ZeroBytePadding", "ZeroBytePadding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CBC", "CBC (Cipher Block Chaining)"},
                        {"ISO7816-4Padding", "ISO7816-4Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CBC", "CBC (Cipher Block Chaining)"},
                        {"ISO10126-2Padding", "ISO10126-2Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CBC", "CBC (Cipher Block Chaining)"},
                        {"PKCS5Padding", "PKCS5Padding/PKCS7Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CBC", "CBC (Cipher Block Chaining)"},
                        {"TBCPadding", "TBCPadding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CBC", "CBC (Cipher Block Chaining)"},
                        {"X9.23Padding", "X9.23Padding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CBC", "CBC (Cipher Block Chaining)"},
                        {"ZeroBytePadding", "ZeroBytePadding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CBC", "CBC (Cipher Block Chaining)"},
                        {"CTSPadding", "CTSPadding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CTR", "CTR (Counter mode)"},
                        {"NoPadding", "NoPadding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"CFB", "CFB (Cipher FeedBack mode)"},
                        {"NoPadding", "NoPadding"}
                },
                {
                        {"AES", "AES (Advanced Encryption Standard)"},
                        {"OFG", "Output Feedback mode"},
                        {"NoPadding", "NoPadding"}
                },
                {
                        {"ARC4.", "ARC4."}
                },
                {
                        {"ChaCha7539", "ChaCha7539"}
                }
        };
 */