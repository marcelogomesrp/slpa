/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.io.Serializable;
import java.lang.String;
import java.util.List;
import lavor.dao.ClienteDao;
import lavor.entidade.Cliente;
import lavor.entidade.PostoDeAtendimento;
import lavor.utils.GenericExceptionMessageType;

/**
 *
 * @author marcelo
 */
public class ClienteService implements Serializable{
    private ServiceException serviceException;
    private ClienteDao clienteDao;

    public ClienteService() {
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    private void NomeValido(String nome) {
        if(lavor.utils.StringUtils.isNullOrEmpty(nome)){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "O nome deve ser informado");
        }
    }

    private boolean PodeSerSalvo(Cliente cliente) {
        this.NomeValido(cliente.getNome());
        return Boolean.TRUE;

    }

    public Cliente Salvar(Cliente cliente) throws ServiceException{
        this.serviceException = new ServiceException();
        try{
            if(this.PodeSerSalvo(cliente)){
                //cliente = this.clienteDao.salvar(cliente);
                cliente = this.clienteDao.atualizar(cliente);
            }
        }catch(Exception ex){
            throw new ServiceException("Ocorreu um erro ao tentar salvar", ex);
        }
        return cliente;
    }

    public List<Cliente> PesquisarPorNome(String nome){
        return this.clienteDao.PesquisarPorNome(nome);
        //return this.clienteDao.todos();
    }
    
    public List<Cliente> PesquisarPorNomePostoDeAtendimento(String nome, Long id){        
        return this.clienteDao.PesquisarPorNomePostoDeAtendimento(nome, id);
        //return this.clienteDao.todos();
    }







}
