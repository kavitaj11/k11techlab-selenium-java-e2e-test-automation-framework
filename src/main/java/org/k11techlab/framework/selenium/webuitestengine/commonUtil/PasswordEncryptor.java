package org.k11techlab.framework.selenium.webuitestengine.commonUtil;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class PasswordEncryptor {

    public static String getEncryptedPassword(String plainPassword) {
        String encryptedPassword = "";
        try {
            encryptedPassword = Base64.encodeBase64String(plainPassword.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptedPassword;
    }
}
