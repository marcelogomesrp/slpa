/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import lavor.dao.EquipamentoClienteDao;
import lavor.entidade.Equipamento;
import lavor.entidade.EquipamentoCliente;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author marcelo
 */
public class EquipamentoClienteService {
    private ServiceException serviceException;
    private EquipamentoClienteDao equipamentoClienteDao;

    public EquipamentoClienteService() {
    }

    public EquipamentoClienteDao getEquipamentoClienteDao() {
        return equipamentoClienteDao;
    }

    public void setEquipamentoClienteDao(EquipamentoClienteDao equipamentoClienteDao) {
        this.equipamentoClienteDao = equipamentoClienteDao;
    }

    public EquipamentoCliente Salvar(EquipamentoCliente equipamentoCliente) throws ServiceException{
        this.serviceException = new ServiceException();
        try{
            if(this.PodeSerSalvo(equipamentoCliente)){
                equipamentoCliente = this.equipamentoClienteDao.salvar(equipamentoCliente);
            }
        }catch(DataAccessException ex){
            throw new ServiceException("Ocorreu um erro ao tentar salvar", ex);
        }
        return equipamentoCliente;
    }

    private boolean PodeSerSalvo(EquipamentoCliente equipamentoCliente) throws ServiceException {
        this.EquipamentoExiste(equipamentoCliente.getEquipamento());
        if (serviceException.hasMessages()) {
            throw serviceException;
        }
        return Boolean.TRUE;

    }

    private void EquipamentoExiste(Equipamento equipamento) {
        if(equipamento.getId() == null){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "O equipamento nao foi selecionado");
        }
    }

    

}
