/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Equipamento Atualizar(Equipamento equipamento){
        return equipamentoDao.atualizar(equipamento);
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

    public List<Equipamento> LocalizarTodosEquipamentos(){
        List<Equipamento> equipamentos = equipamentoDao.todos();
        return equipamentos;
    }

    public List<Equipamento> LocalizarPorNome(String nome){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nome", nome);
        String sql = "SELECT e FROM Equipamento e WHERE e.nome LIKE :nome";
        List<Equipamento> equipamentos = equipamentoDao.listPesqParam(sql, params);
        return equipamentos;
    }


}
