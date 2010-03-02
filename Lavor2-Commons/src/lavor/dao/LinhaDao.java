/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao;

import java.util.List;
import lavor.entidade.Linha;

/**
 *
 * @author marcelo
 */
public interface LinhaDao extends DaoGenerico<Linha, Long>{

    public List pesquisarPorNome(String nome);

}
