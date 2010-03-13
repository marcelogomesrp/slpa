/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao;

import java.util.List;
import lavor.entidade.Defeito;

/**
 *
 * @author marcelo
 */
public interface DefeitoDao extends DaoGenerico<Defeito, Long>{

    public List<Defeito> PesquisarPorNome(String nome);

}
