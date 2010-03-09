/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

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
    private ListDataModel equipamentos;

    public EquipamentoMB() {
        this.equipamento    = new Equipamento();
        this.equipamentos   = new ListDataModel();
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public ListDataModel getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(ListDataModel equipamentos) {
        this.equipamentos = equipamentos;
    }







}
