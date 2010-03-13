/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import lavor.entidade.Defeito;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



/**
 *
 * @author marcelo
 */

@Controller("defeitoMB")
@Scope("session")
public class DefeitoMB {

    private Defeito defeito;
    private ListDataModel defeitos;

    public DefeitoMB() {
        this.defeito  = new Defeito();
        this.defeitos = new ListDataModel();
    }

    public Defeito getDefeito() {
        return defeito;
    }

    public void setDefeito(Defeito defeito) {
        this.defeito = defeito;
    }

    public ListDataModel getDefeitos() {
        return defeitos;
    }

    public void setDefeitos(ListDataModel defeitos) {
        this.defeitos = defeitos;
    }
    

}
