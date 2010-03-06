/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import lavor.dao.EquipamentoDao;

/**
 *
 * @author marcelo
 */
public class EquipamentoService {
    private EquipamentoDao equipamentoDao;

    public EquipamentoService() {
    }

    public EquipamentoDao getEquipamentoDao() {
        return equipamentoDao;
    }

    public void setEquipamentoDao(EquipamentoDao equipamentoDao) {
        this.equipamentoDao = equipamentoDao;
    }




}
