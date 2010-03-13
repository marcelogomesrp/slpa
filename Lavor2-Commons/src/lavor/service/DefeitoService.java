/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.io.Serializable;
import java.util.List;
import lavor.dao.DefeitoDao;
import lavor.entidade.Defeito;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author marcelo
 */
public class DefeitoService implements Serializable{
    private ServiceException serviceException;
    
    private DefeitoDao defeitoDao;

    public DefeitoService() {
    }

    public DefeitoDao getDefeitoDao() {
        return defeitoDao;
    }

    public void setDefeitoDao(DefeitoDao defeitoDao) {
        this.defeitoDao = defeitoDao;
    }

    public Defeito Salvar(Defeito defeito) throws ServiceException{
        this.serviceException = new ServiceException();
        try{
            if(this.PodeSerSalvo(defeito)){
                defeito = defeitoDao.salvar(defeito);
            }            
        }catch(DataAccessException ex){
            throw new ServiceException("Ocorreu um erro ao tentar salvar", ex);
        }
        return defeito;
    }

    private boolean PodeSerSalvo(Defeito defeito) throws ServiceException {
        this.NomeValido(defeito.getNome());
        this.NomeUnico(defeito.getNome());

        if (serviceException.hasMessages()) {
            throw serviceException;
        }
        return Boolean.TRUE;
    }

    private void NomeUnico(String nome){
        List<Defeito> defeitos = this.defeitoDao.PesquisarPorNome(nome);
        if(defeitos.size() != 0){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "O nome do defeito ja esta cadastrado");
        }
    }
    private void NomeValido(String nome) {
        if(lavor.utils.StringUtils.isNullOrEmpty(nome)){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "O nome deve ser informado");
        }
    }

    public List<Defeito> Todos(){
        return this.defeitoDao.todos();
    }

    public List<Defeito> PesquisarPorNome(String nome){
        return this.defeitoDao.PesquisarPorNome(nome);
    }



}
