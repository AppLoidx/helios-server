package com.apploidxxx.core.auth;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Arthur Kupriyanov
 */
public class PasswordChecker {
    private static final String salt;
    private static final String PASS_CONF_FILENAME = "PASSWORD_CONF.properties";
    static {
        Properties properties = new Properties();
        String saltT = null;
        try {
            properties.load(PasswordChecker.class.getClassLoader().getResourceAsStream(PASS_CONF_FILENAME));
            saltT = properties.getProperty("LOCAL_PARAMETER_FOR_PASSWORD");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла " + PASS_CONF_FILENAME);
        }

        if (saltT==null) salt = "SALT";
        else salt = saltT;
    }
    public static String hashPassword(String origin){
        return String.valueOf((origin + salt).hashCode() * 31);
    }
    public static boolean checkEquals(String password1, String password2){
        return hashPassword(password1).equals(hashPassword(password2));
    }
}
