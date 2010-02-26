/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao.imp;

import java.util.HashMap;
import java.util.Map;
import lavor.dao.PostoDeAtendimentoDao;
import lavor.entidade.PostoDeAtendimento;

/**
 *
 * @author marcelo
 */
public class PostoDeAtendimentoDaoImp extends DaoGenericoImp<PostoDeAtendimento, Long> implements PostoDeAtendimentoDao {


    public PostoDeAtendimento pesquisarPorRazaoSocial(String razaoSocial) {
        String SQL = "SELECT p from PostoDeAtendimento p  WHERE razaoSocial = :razaoSocial";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("razaoSocial", razaoSocial);
        PostoDeAtendimento postoDeAtendimento = this.pesqParam(SQL, params);
        return postoDeAtendimento;
    }

}
