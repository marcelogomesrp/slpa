/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import lavor.entidade.Linha;
import lavor.service.LinhaService;
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
    List<SelectItem> selectLinhas;

    public LinhaMB() {
        this.linha  = new Linha();
        this.linhas = new ListDataModel();
        this.selectLinhas = new ArrayList<SelectItem>();
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

    public List<SelectItem> getSelectLinhas() {
        return selectLinhas;
    }

    public void setSelectLinhas(List<SelectItem> selectLinhas) {
        this.selectLinhas = selectLinhas;
    }





    



}
