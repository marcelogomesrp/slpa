/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lavor.dao.ClienteDao;
import lavor.entidade.Cliente;

/**
 *
 * @author Marcelo
 */
public class ClienteService {
    private ClienteDao clienteDao;

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public Cliente Salvar(Cliente cliente){
        cliente = clienteDao.salvar(cliente);
        return cliente;
    }

    public List<Cliente> BuscarClientePorNome(String nome){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nome", nome);
        //String SQL = "SELECT c FROM Cliente c WHERE c.nome like = :nome";
        //String SQL = "SELECT c FROM Cliente c WHERE c.nome = :nome";
        String SQL = "SELECT c FROM Cliente c WHERE c.nome like :nome";
        List<Cliente> clientes = clienteDao.listPesqParam(SQL, params);
        return clientes;
    }

}
