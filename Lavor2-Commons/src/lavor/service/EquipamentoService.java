/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.util.List;
import lavor.dao.EquipamentoDao;
import lavor.entidade.Equipamento;
import lavor.entidade.Linha;
import lavor.utils.GenericExceptionMessageType;

/**
 *
 * @author marcelo
 */
public class EquipamentoService {
    private EquipamentoDao equipamentoDao;
    ServiceException serviceException;
    private LinhaService linhaService;

    public EquipamentoService() {
        this.serviceException = new ServiceException();
    }

    public EquipamentoDao getEquipamentoDao() {
        return equipamentoDao;
    }

    public void setEquipamentoDao(EquipamentoDao equipamentoDao) {
        this.equipamentoDao = equipamentoDao;
    }

    public LinhaService getLinhaService() {
        return linhaService;
    }

    public void setLinhaService(LinhaService linhaService) {
        this.linhaService = linhaService;
    }

     
    private void ModeloValido(String modelo) {
        if(lavor.utils.StringUtils.isNullOrEmpty(modelo)){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "O modelo deve ser informado");
        }
    }

    private void ModeloUnico(String modelo) {
        List<Equipamento> equipamentos = this.PesquisarPorNome(modelo);
        if(equipamentos.size() > 0 ){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "O modelo já esta na base de dados");
        }
    }

    private Equipamento PesquisarPorID(Long id) {
        return this.equipamentoDao.pesquisarPorId(id);
    }

    private boolean EquipamentoValido(Equipamento equipamento) throws ServiceException {
       this.ModeloValido(equipamento.getModelo());
       this.ModeloUnico(equipamento.getModelo());
        if (serviceException.hasMessages()) {
            throw serviceException;
        }

       return Boolean.TRUE;
    }
 
    public Equipamento Salvar(Equipamento equipamento) throws ServiceException{
        this.serviceException = new ServiceException();
        if(this.EquipamentoValido(equipamento)){
            Linha ln = linhaService.PesquisarPorId(equipamento.getLinha().getId());
            equipamento.setLinha(ln); 
            //equipamento = equipamentoDao.atualizar(equipamento);
            equipamento = equipamentoDao.salvar(equipamento);
            
        }
        return equipamento;
    }

    public Equipamento Atualizar(Equipamento equipamento) throws ServiceException {
        this.serviceException = new ServiceException();

        Equipamento equipamentoOriginal = this.PesquisarPorID(equipamento.getId());
        if(this.PodeSerAtualizado(equipamento, equipamentoOriginal)){
            equipamento = equipamentoDao.atualizar(equipamento);
        }
        return equipamento;
    }

    public List<Equipamento> PesquisarPorNome(String nome){
        return this.equipamentoDao.PesquisarPorNome(nome);
    }

    public List<Equipamento> PesquisarPorLinha(Long id) {
        return this.equipamentoDao.PesquisarPorLinha(id);
    }

    private boolean PodeSerAtualizado(Equipamento equipamento, Equipamento equipamentoOriginal) throws ServiceException {
        if(!equipamentoOriginal.getModelo().equals(equipamento.getModelo())){
            this.ModeloValido(equipamento.getModelo());
            this.ModeloUnico(equipamento.getModelo());
        }
        if (serviceException.hasMessages()) {
            throw serviceException;
        }
        return Boolean.TRUE;
    }

    public Equipamento PesquisarPorId(Equipamento equipamento){
        return this.equipamentoDao.pesquisarPorId(equipamento.getId());
    }













}
