/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import lavor.entidade.Revenda;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("revendaMB")
@Scope("session")
public class RevendaMB {

    private Revenda revenda;

    public RevendaMB() {
        this.revenda = new Revenda();
    }

    public Revenda getRevenda() {
        return revenda;
    }

    public void setRevenda(Revenda revenda) {
        this.revenda = revenda;
    }

}
