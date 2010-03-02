/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lavor.dao.LinhaDao;
import lavor.entidade.Linha;

/**
 *
 * @author marcelo
 */
public class LinhaDaoImp extends DaoGenericoImp<Linha, Long> implements LinhaDao {

    public List pesquisarPorNome(String nome) {
        String SQL = "SELECT l from Linha l  WHERE nome = :nome";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nome", nome);
        List<Linha> linhas = this.listPesqParam(SQL, params);
        return linhas;
    }

}
