/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedbean;

import javax.faces.model.ListDataModel;
import lavor.entidade.Equipamento;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("equipamentoMB")
@Scope("session")
public class EquipamentoMB {

    private Equipamento equipamento;
    private ListDataModel listaDeEquipamento;

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public ListDataModel getListaDeEquipamento() {
        return listaDeEquipamento;
    }

    public void setListaDeEquipamento(ListDataModel listaDeEquipamento) {
        this.listaDeEquipamento = listaDeEquipamento;
    }

    public EquipamentoMB() {
        this.equipamento        = new Equipamento();
        this.listaDeEquipamento = new ListDataModel();
    }


}
