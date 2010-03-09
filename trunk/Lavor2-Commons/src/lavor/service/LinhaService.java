/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.util.List;
import lavor.dao.LinhaDao;
import lavor.entidade.Linha;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author marcelo
 */
public class LinhaService {
    private LinhaDao linhaDao;
    private ServiceException serviceException;

    public LinhaService() {
        this.serviceException = new ServiceException();
    }

    public LinhaDao getLinhaDao() {
        return linhaDao;
    }

    public void setLinhaDao(LinhaDao linhaDao) {
        this.linhaDao = linhaDao;
    }

    private void NomeValido(String nome){
        if(lavor.utils.StringUtils.isNullOrEmpty(nome)){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "O nome da linha deve ser informado");
         }
    }
    private void NomeCadastrado(String nome){
        List<Linha> linhas = this.PesquisarPorNome(nome);
        if(linhas.size() > 0 ){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "O nome da linha ja foi cadastrado");
         }
    }

    private boolean PodeSerSalvo(Linha linha) throws ServiceException {
        this.NomeValido(linha.getNome());
        this.NomeCadastrado(linha.getNome());

        if (serviceException.hasMessages()) {
            throw serviceException;
        }
        return true;
    }

    private boolean PodeSerAtualizado(Linha linha) throws ServiceException {
        // TODO: testar se o nome foi troca caso sim se ele eh o unico
        this.NomeValido(linha.getNome());
        
        if (serviceException.hasMessages()) {
            throw serviceException;
        }
        return true;
    }

    public Linha Salvar(Linha linha) throws ServiceException{
        this.serviceException = new ServiceException();
        try{
            if(this.PodeSerSalvo(linha)){
                linha = this.linhaDao.salvar(linha);
            }
        }catch(DataAccessException ex){
            throw new ServiceException("Ocorreu um erro ao tentar salvar", ex);
        }
        return linha;
    }
    public void Atualizar(Linha linha) throws ServiceException {
        this.serviceException = new ServiceException();
        try{
            if(this.PodeSerAtualizado(linha)){
                linha = this.linhaDao.atualizar(linha);
            }
        }catch(DataAccessException ex){
            throw new ServiceException("Ocorreu um erro ao tentar salvar", ex);
        }
    }

    public List<Linha> PesquisarPorNome(String nome){
        List linhas = null;
        linhas = this.linhaDao.pesquisarPorNome(nome);
        return linhas;
    }

    public List<Linha> Todos(){
        return linhaDao.todos();
    }

    Linha PesquisarPorId(Long id) {
        return this.linhaDao.pesquisarPorId(id);
    }








}
