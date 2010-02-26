/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lavor.dao.CidadeDao;
import lavor.entidade.Cidade;
import lavor.entidade.Estado;

/**
 *
 * @author marcelo
 */
public class CidadeDaoImp extends DaoGenericoImp<Cidade, Long> implements CidadeDao {

    public List<Cidade> PesquisarPorEstado(Estado estado) {
        String SQL = "SELECT c from Cidade c  WHERE estado = :estado ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("estado", estado);
        List<Cidade> cidades = this.listPesqParam(SQL, params);
        return cidades;
    }

    public Cidade PesquisarPorCidadeEstado(String cidade, Estado estado) {
        String SQL = "SELECT c from Cidade c  WHERE cidade = :cidade and estado = :estado ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cidade", cidade);
        params.put("estado", estado);
        Cidade cidadeResult = this.pesqParam(SQL, params);
        return cidadeResult;
    }



}
