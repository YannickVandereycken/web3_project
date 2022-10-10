package ui.view;

import java.util.Properties;

public abstract class Credentials {
    static public void setPass(Properties dbProperties) {
        dbProperties.setProperty("user", "");
        dbProperties.setProperty("password", "");
    }
}