/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao;

import java.util.List;
import lavor.entidade.Cidade;
import lavor.entidade.Estado;

/**
 *
 * @author marcelo
 */
public interface CidadeDao extends DaoGenerico<Cidade, Long>{
    public List<Cidade> PesquisarPorEstado(Estado estado);
    public Cidade PesquisarPorCidadeEstado(String cidade, Estado estado);
}
