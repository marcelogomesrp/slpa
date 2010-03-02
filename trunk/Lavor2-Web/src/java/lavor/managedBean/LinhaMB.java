/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import java.io.Serializable;
import lavor.entidade.Linha;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("linhaMB")
@Scope("session")
public class LinhaMB implements  Serializable{
    
    private Linha linha;

    public LinhaMB() {
        this.linha = new Linha();
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    



}
