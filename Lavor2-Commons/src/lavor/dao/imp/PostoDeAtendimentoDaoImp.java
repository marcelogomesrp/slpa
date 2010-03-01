/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lavor.dao.PostoDeAtendimentoDao;
import lavor.entidade.PostoDeAtendimento;
import lavor.entidade.Usuario;

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

    public List pesquisarPorCidade(Long id) {
        String SQL = "SELECT p from PostoDeAtendimento p WHERE cidade.id = :id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        List<PostoDeAtendimento> postosDeAtendimento = listPesqParam(SQL, params);
         //List<PostoDeAtendimento> postosDeAtendimento  = this.todos();
        return postosDeAtendimento;        
    }

    public PostoDeAtendimento pesquisarPorUsuario(Usuario usuario) {
        String SQL = "SELECT p from PostoDeAtendimento p WHERE usuario.id = :id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", usuario.getId());
        List<PostoDeAtendimento> postosDeAtendimento = listPesqParam(SQL, params);
        if(postosDeAtendimento.size() == 1 ){
            return postosDeAtendimento.get(0);
        }
        return new PostoDeAtendimento();
    }

}
