/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lavor.dao.PecaDao;
import lavor.entidade.Equipamento;
import lavor.entidade.Peca;

/**
 *
 * @author marcelo
 */
public class PecaDaoImp extends DaoGenericoImp<Peca, Long> implements PecaDao {

    public List<Peca> PesquisarPorPosicaoEquipamento(int posicao, Equipamento equipamento) {
        String SQL = "SELECT p from Peca p  WHERE posicao = :posicao and equipamento.id = :id_equipamento ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("posicao", posicao);
        params.put("id_equipamento", equipamento.getId());
        List<Peca> pecas = this.listPesqParam(SQL, params);
        return pecas;
    }

}
