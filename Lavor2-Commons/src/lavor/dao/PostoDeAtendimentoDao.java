/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao;

import java.util.List;
import lavor.entidade.PostoDeAtendimento;

/**
 *
 * @author marcelo
 */
public interface PostoDeAtendimentoDao extends DaoGenerico<PostoDeAtendimento, Long>{
    public PostoDeAtendimento pesquisarPorRazaoSocial(String razaoSocial);

    public List pesquisarPorCidade(Long id);

}
