/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lavor.dao.ClienteDao;
import lavor.entidade.Cliente;

/**
 *
 * @author marcelo
 */
public class ClienteDaoImp extends DaoGenericoImp<Cliente, Long> implements ClienteDao {

    public List<Cliente> PesquisarPorNome(String nome) {
        String SQL = "SELECT c from Cliente c WHERE nome like :nome";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nome", nome);
        List<Cliente> clientes = listPesqParam(SQL, params);
        return clientes;
    }

    public List<Cliente> PesquisarPorNomePostoDeAtendimento(String nome, Long id) {
        String SQL = "SELECT c from Cliente c WHERE nome like :nome and postoDeAtendimento.id = :id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nome",  nome);
        params.put("id",    id);
        List<Cliente> clientes = listPesqParam(SQL, params);
        return clientes;
        
    }

    public List<Cliente> PesquisarPorTelefoneEPostoDeAtendimento(String telefone, Long id) {
        String SQL = "SELECT c from Cliente c WHERE telefone = :telefone and postoDeAtendimento.id = :id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("telefone",  telefone);
        params.put("id",    id);
        List<Cliente> clientes = listPesqParam(SQL, params);
        return clientes;
    }


}
