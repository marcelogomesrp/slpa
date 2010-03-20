/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
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
    private List<SelectItem> revendas;

    public RevendaMB() {
        this.revenda = new Revenda();
        this.revendas = new ArrayList<SelectItem>();
    }

    public Revenda getRevenda() {
        return revenda;
    }

    public void setRevenda(Revenda revenda) {
        this.revenda = revenda;
    }

    public List<SelectItem> getRevendas() {
        return revendas;
    }

    public void setRevendas(List<SelectItem> revendas) {
        this.revendas = revendas;
    }

}
