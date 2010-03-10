/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import lavor.dao.EquipamentoClienteDao;

/**
 *
 * @author marcelo
 */
public class EquipamentoClienteService {

    private EquipamentoClienteDao equipamentoClienteDao;

    public EquipamentoClienteService() {
    }

    public EquipamentoClienteDao getEquipamentoClienteDao() {
        return equipamentoClienteDao;
    }

    public void setEquipamentoClienteDao(EquipamentoClienteDao equipamentoClienteDao) {
        this.equipamentoClienteDao = equipamentoClienteDao;
    }

    

}
