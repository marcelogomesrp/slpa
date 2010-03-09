/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lavor.dao.EquipamentoDao;
import lavor.entidade.Equipamento;

/**
 *
 * @author marcelo
 */
public class EquipamentoDaoImp extends DaoGenericoImp<Equipamento, Long> implements EquipamentoDao {

    public List<Equipamento> PesquisarPorNome(String nome) {
        String SQL = "SELECT e from Equipamento e  WHERE modelo = :nome";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nome", nome);
        List<Equipamento> equipamentos = this.listPesqParam(SQL, params);
        return equipamentos;
    }

    public List<Equipamento> PesquisarPorLinha(Long id) {
        String SQL = "SELECT e from Equipamento e  WHERE linha.id = :id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        List<Equipamento> equipamentos = this.listPesqParam(SQL, params);
        return equipamentos;
    }


}
