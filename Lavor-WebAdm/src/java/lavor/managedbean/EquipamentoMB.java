/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedbean;

import java.io.Serializable;
import java.util.List;
import lavor.entidade.Equipamento;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */
@Controller("equipamentoMB")
@Scope("session")
public class EquipamentoMB implements Serializable{

    private Equipamento equipamento;
    private List<Equipamento> listaDeEquipamentos;
    //private Integer numero;
    private Long numero;

    public EquipamentoMB() {
        this.equipamento = new Equipamento();

    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public List<Equipamento> getListaDeEquipamentos() {
        return listaDeEquipamentos;
    }

    public void setListaDeEquipamentos(List<Equipamento> listaDeEquipamentos) {
        this.listaDeEquipamentos = listaDeEquipamentos;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }



}
