/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import java.io.Serializable;
import javax.faces.model.ListDataModel;
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
    private ListDataModel linhas;

    public LinhaMB() {
        this.linha  = new Linha();
        this.linhas = new ListDataModel();
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public ListDataModel getLinhas() {
        return linhas;
    }

    public void setLinhas(ListDataModel linhas) {
        this.linhas = linhas;
    }

    



}
