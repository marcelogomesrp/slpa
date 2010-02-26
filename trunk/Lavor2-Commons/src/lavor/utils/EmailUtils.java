/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author marcelo
 */
public class EmailUtils {
    
        public static boolean isEmailValido(String email) {
                Pattern padrao = Pattern.compile(".+@.+\\.[a-z]+");
                Matcher pesquisa = padrao.matcher(email);
                return pesquisa.matches();
        }


}
