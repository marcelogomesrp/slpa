/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import lavor.dao.PecaValorDao;
import lavor.entidade.PecaValor;

/**
 *
 * @author marcelo
 */
public class PecaValorService {
    private PecaValorDao pecaValorDao;

    public PecaValorDao getPecaValorDao() {
        return pecaValorDao;
    }

    public void setPecaValorDao(PecaValorDao pecaValorDao) {
        this.pecaValorDao = pecaValorDao;
    }

    public PecaValor SalvorPecaValor(PecaValor pecaValor){
        return pecaValorDao.salvar(pecaValor);
    }

    public PecaValor LocalizarPorId(Long id){
        return pecaValorDao.pesquisarPorId(id);
    }
    



}
