/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lavor.dao.DefeitoDao;
import lavor.entidade.Defeito;

/**
 *
 * @author marcelo
 */
public class DefeitoDaoImp extends DaoGenericoImp<Defeito, Long> implements DefeitoDao {

    public List<Defeito> PesquisarPorNome(String nome) {
        String SQL = "SELECT d from Defeito d  WHERE nome = :nome";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nome", nome);
        List<Defeito> defeitos = this.listPesqParam(SQL, params);
        return defeitos;
    }

}
 