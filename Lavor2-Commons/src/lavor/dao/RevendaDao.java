/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao;

import java.util.List;
import lavor.entidade.PostoDeAtendimento;
import lavor.entidade.Revenda;

/**
 *
 * @author marcelo
 */
public interface RevendaDao extends DaoGenerico<Revenda, Long>{

    public List<Revenda> PesquisarPorPosto(PostoDeAtendimento postoDeAtendimento);

    public List<Revenda> PesquisarPorCnpjEPosto(String cnpj, Long id);

}
