package edu.junit5.quickstart;

public class Util {
    static void printByteArrayAsTextToConsole(byte[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.write(array[i]);
            System.out.println(array[i]);

        }
        System.out.flush();
    }

    static String getConfig(ConfigNode configNode, int currentLevel) {
        if (configNode.hasChildren() == false) {
            return configNode.toString();
        }

        String values = configNode.toString();
        int newLevel = currentLevel + 1;
        for (ConfigNode child : configNode.getChildren()) {
            values += "-".repeat(newLevel * 2) + " ";
            values += getConfig(child, newLevel);
        }
        return values;
    }
}
