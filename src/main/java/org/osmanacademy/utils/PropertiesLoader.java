package org.osmanacademy.utils;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties load(String fileName) throws Exception {
        Properties prop = new Properties();
        try (InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new Exception("Sorry, unable to find " + fileName);
            }
            prop.load(input);
            return prop;
        } catch (Exception ex) {
           throw new Exception(ex.getMessage());
        }
    }
}
