/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author marcelo
 */
public class FacesUtils_teste {
public static void mensInfo(String message) {
        mensagem(message, FacesMessage.SEVERITY_INFO);
    }

    public static void mensErro(String message) {
        mensagem(message, FacesMessage.SEVERITY_ERROR);
    }

    public static void mensagem(String message,
            FacesMessage.Severity severity) {

        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, message, null));
    }

    public static String get(String param) {

        return (String) FacesContext.getCurrentInstance().
                getExternalContext().
                getRequestParameterMap().get(param);
    }
}
