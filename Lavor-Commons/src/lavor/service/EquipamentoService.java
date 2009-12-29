/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import lavor.dao.EquipamentoDao;
import lavor.entidade.Equipamento;

/**
 *
 * @author marcelo
 */
public class EquipamentoService {
    private EquipamentoDao equipamentoDao;

    public EquipamentoDao getEquipamentoDao() {
        return equipamentoDao;
    }

    public void setEquipamentoDao(EquipamentoDao equipamentoDao) {
        this.equipamentoDao = equipamentoDao;
    }

    public EquipamentoService() {
    }

    public Equipamento Salvar(Equipamento equipamento) throws Exception{
        EquipamentoPodeSerSalvo(equipamento);
        equipamento = equipamentoDao.salvar(equipamento);
        return equipamento;
    }

    private void EquipamentoPodeSerSalvo(Equipamento equipamento) throws Exception{
        StringBuilder msg = new StringBuilder();
        if(equipamento.getNome().isEmpty()){
            msg.append("O nome do equipamento deve ser informado");
        }
        if(msg.length() > 0){
            throw new Exception(msg.toString());
        }
    }

}
