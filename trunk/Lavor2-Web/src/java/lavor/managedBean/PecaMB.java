/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import java.io.Serializable;
import javax.faces.model.ListDataModel;
import lavor.entidade.Peca;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */
@Controller("pecaMB")
@Scope("session")
public class PecaMB implements  Serializable{

    private Peca peca;
    private ListDataModel pecas;

    public PecaMB() {
        this.peca   = new Peca();
        this.pecas  = new ListDataModel();
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public ListDataModel getPecas() {
        return pecas;
    }

    public void setPecas(ListDataModel pecas) {
        this.pecas = pecas;
    }
 

}
