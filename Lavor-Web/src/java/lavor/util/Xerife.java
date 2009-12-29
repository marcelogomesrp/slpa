/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.util;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcelo
 */
public class Xerife implements PhaseListener {

    public void afterPhase(PhaseEvent event) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void beforePhase(PhaseEvent event) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String usuario = (String) session.getAttribute("usuario");
        System.out.println("Xerife em acao");
        System.out.println("User " + usuario);
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}
