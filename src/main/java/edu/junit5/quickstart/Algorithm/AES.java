package edu.junit5.quickstart.Algorithm;

import edu.junit5.quickstart.Mode.Mode;
import edu.junit5.quickstart.Mode.OFB;

public class AES extends Algorithm{

    public AES(){
        super("AES","AES", new Mode[]{
                new OFB()
        });
    }
}
