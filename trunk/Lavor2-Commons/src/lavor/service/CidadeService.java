/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.io.Serializable;
import java.util.List;
import lavor.dao.CidadeDao;
import lavor.entidade.Cidade;
import lavor.entidade.Estado;
import lavor.utils.GenericExceptionMessageType;

/**
 *
 * @author marcelo
 */
public class CidadeService implements Serializable{
    private CidadeDao cidadeDao;
    private ServiceException serviceException;

    public CidadeService() {
        this.serviceException = new ServiceException();
    }

    public CidadeDao getCidadeDao() {
        return cidadeDao;
    }

    public void setCidadeDao(CidadeDao cidadeDao) {
        this.cidadeDao = cidadeDao;
    }

    public List<Cidade> PesquisarPorEstado(Estado estado){
        return this.cidadeDao.PesquisarPorEstado(estado);
    }

    public Cidade PesquisarPorCidadeEstado(String Cidade, Estado estado){
        Cidade cidade = this.cidadeDao.PesquisarPorCidadeEstado(Cidade, estado);
        return cidade;
    }
    
    private boolean PodeSerSalvo(Cidade cidade) {
        Cidade cidadeCadastrada = this.PesquisarPorCidadeEstado(cidade.getCidade(), cidade.getEstado());
        if(cidadeCadastrada != null){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "Cidade já cadastrada");
        }
        return true;
        
    }

    public Cidade Salvar(Cidade cidade) throws ServiceException{
        this.serviceException = new ServiceException();
        try{
            if(this.PodeSerSalvo(cidade)){
                cidade = this.cidadeDao.salvar(cidade);
            }
        }catch(Exception ex){
            throw new ServiceException("Ocorreu um erro ao tentar salvar", ex);
        }
        return cidade;
    }

}
