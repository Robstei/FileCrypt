package edu.junit5.quickstart.mode;

import java.util.HashMap;

public class Modes {
    final private HashMap<String, Mode> availableModes = new HashMap<>();

    public Modes() {
        availableModes.put(new ECB().getBouncyCastleName(), new ECB());
        availableModes.put(new CBC().getBouncyCastleName(), new CBC());
        availableModes.put(new OFB().getBouncyCastleName(), new OFB());
        availableModes.put(new GCM().getBouncyCastleName(), new GCM());
        };

    public Mode getModeByKey(String key) {
        return availableModes.get(key);
    }
}
