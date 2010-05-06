/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import lavor.entidade.Defeito;
import lavor.service.DefeitoService;
import org.springframework.context.annotation.Scope;
import javax.annotation.Resource;

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
    List<SelectItem> defeitosSelect;
    @Resource
    private DefeitoService defeitoService;

    public DefeitoMB() {
        this.defeito  = new Defeito();
        this.defeitos = new ListDataModel();
        this.defeitosSelect = new ArrayList<SelectItem>();        
    }

    public void AtualizarSelectDefeito(){
        for(Defeito def:defeitoService.Todos()){
            defeitosSelect.add(new SelectItem(def.getId(), def.getNome()));
        }
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

    public List<SelectItem> getDefeitosSelect() {
        return defeitosSelect;
    }

    public void setDefeitosSelect(List<SelectItem> defeitosSelect) {
        this.defeitosSelect = defeitosSelect;
    }

    

}
