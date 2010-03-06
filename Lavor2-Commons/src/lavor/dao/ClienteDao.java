/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao;

import java.util.List;
import lavor.entidade.Cliente;

/**
 *
 * @author marcelo
 */
public interface ClienteDao extends DaoGenerico<Cliente, Long>{
    
    public List<Cliente> PesquisarPorNome(String nome);

    public List<Cliente> PesquisarPorNomePostoDeAtendimento(String nome, Long id);

}
