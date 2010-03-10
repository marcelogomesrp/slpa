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
    private Boolean equipamentoAtivo;

    public EquipamentoMB() {
        this.equipamento    = new Equipamento();
        this.equipamentos   = new ListDataModel();
        this.equipamentoAtivo = Boolean.FALSE;
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

    public Boolean getEquipamentoAtivo() {
        return equipamentoAtivo;
    }

    public void setEquipamentoAtivo(Boolean equipamentoAtivo) {
        this.equipamentoAtivo = equipamentoAtivo;
    }







}
