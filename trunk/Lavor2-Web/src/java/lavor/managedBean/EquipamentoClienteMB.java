/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import java.io.Serializable;
import lavor.entidade.EquipamentoCliente;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */
@Controller("equipamentoClienteMB")
@Scope("session")
public class EquipamentoClienteMB implements  Serializable{
    private EquipamentoCliente equipamentoCliente;

    public EquipamentoClienteMB() {
        this.equipamentoCliente = new EquipamentoCliente();
    }

    public EquipamentoCliente getEquipamentoCliente() {
        return equipamentoCliente;
    }

    public void setEquipamentoCliente(EquipamentoCliente equipamentoCliente) {
        this.equipamentoCliente = equipamentoCliente;
    }

}
