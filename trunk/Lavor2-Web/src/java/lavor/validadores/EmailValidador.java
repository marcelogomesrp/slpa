/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lavor.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author marcelo
 */
public class EmailValidador implements Validator {

    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String enteredEmail = (String) o;
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(enteredEmail);

        boolean matchFound = m.matches();

        if (!matchFound) {
            FacesMessage message = new FacesMessage();
            message.setDetail("E-mail incorreto!");
            message.setSummary("O endereco de email informado nao e valido");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

    }
}
