/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lavor.dao.RevendaDao;
import lavor.entidade.PostoDeAtendimento;
import lavor.entidade.Revenda;

/**
 *
 * @author marcelo
 */
public class RevendaDaoImp extends DaoGenericoImp<Revenda, Long> implements RevendaDao {

    public List<Revenda> PesquisarPorPosto(PostoDeAtendimento postoDeAtendimento) {
        String SQL = "SELECT r from Revenda r WHERE postoDeAtendimento.id = :id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", postoDeAtendimento.getId());
        List<Revenda> revendas = listPesqParam(SQL, params);
        return revendas;
    }

}
