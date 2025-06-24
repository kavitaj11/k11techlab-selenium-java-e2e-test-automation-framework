package org.k11techlab.framework.selenium.webuitestengine.commonUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

    Properties prop;
    FileInputStream input = null;

    public LoadProperties() {
        try {
            File source = new File("resources/environment.properties");

            FileInputStream input = new FileInputStream(source);

            prop = new Properties();

            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }




    public String getProperty(String key)
    {
        return prop.getProperty(key);
    }
}


