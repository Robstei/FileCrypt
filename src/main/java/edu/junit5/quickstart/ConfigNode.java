package edu.junit5.quickstart;

import java.lang.reflect.Array;
import java.util.*;

public class ConfigNode {

    private String bouncyCastleName;
    private String UIName;
    private ConfigNode parent;
    private ArrayList<ConfigNode> children = new ArrayList<>();

    ConfigNode(String bouncyCastleName, String UIName, ConfigNode parent) {
        this.bouncyCastleName = bouncyCastleName;
        this.UIName = UIName;
        this.parent = parent;
    }

    public ConfigNode addChild(String value, String UIName) {
        ConfigNode existingChild = getChildWithValue(value);
        if (existingChild != null) {
            return existingChild;
        }

        ConfigNode child = new ConfigNode(value, UIName, this);
        children.add(child);
        return child;
    }


    public boolean hasChildren() {
        if (children.size() == 0) {
            return false;
        }
        return true;
    }

    public ConfigNode getChildWithValue(String value) {
        for (ConfigNode child : children) {
            if (child.getBouncyCastleName().equals(value)) {
                return child;
            }
        }
        return null;
    }

    public ArrayList<ConfigNode> getChildren() {
        return children;
    }

    public String toString() {
        return String.format("BouncyCastleName: %s - UIName: %s\n",
                bouncyCastleName, UIName);
    }

    public String getBouncyCastleName() {
        return bouncyCastleName;
    }


    // soll aus einem String alle notwendigen Kinder erstellen, wobei die
    // Argumente mit / seperariert sind oder soll alle Kinder auf einmal
    // erstellen
    public void addValidConfigCombination(String[][] validConfigCombination) {

        ConfigNode child = addChild(validConfigCombination[0][0],
                validConfigCombination[0][1]);


        if (validConfigCombination.length > 1) {
            String[][] newValidConfigCombination =
                    Arrays.copyOfRange(validConfigCombination, 1,
                            validConfigCombination.length);
            child.addValidConfigCombination(newValidConfigCombination);
        }
    }
}