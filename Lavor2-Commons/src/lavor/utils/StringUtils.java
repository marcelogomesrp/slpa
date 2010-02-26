/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lavor.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author marcelo
 */
public class StringUtils {

    public static boolean isNullOrEmpty(String string) {
        return isNullOrEmpty(string, true);
    }

    public static boolean isNullOrEmpty(String string, boolean trim) {
        return (string == null || ((trim) ? string.trim().length() == 0 : string.length() == 0));
    }

    public static String GerarMD5(String input) throws Exception {
        String dstr = null;
        byte[] digest;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            digest = md.digest();
            dstr = new BigInteger(1, digest).toString(16);
            if (dstr.length() % 2 > 0) {
                dstr = "0" + dstr;
            }
        } catch (Exception e) {
            throw new Exception("Erro inesperado em makeMD5(): " + e.toString(),
                    e);
        }
        return dstr;
    }
}
