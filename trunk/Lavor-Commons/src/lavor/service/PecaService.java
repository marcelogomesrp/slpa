/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import javax.annotation.Resource;
import lavor.dao.PecaDao;
import lavor.entidade.Peca;

/**
 *
 * @author marcelo
 */
public class PecaService {
    @Resource
    private PecaDao pecaDao;

    public PecaDao getPecaDao() {
        return pecaDao;
    }

    public void setPecaDao(PecaDao pecaDao) {
        this.pecaDao = pecaDao;
    }

    public PecaService() {
    }

    public Peca SalvarPeca(Peca peca) throws Exception{
        PecaPodeSerSalva(peca);
        peca = this.pecaDao.salvar(peca);
        return peca;
    }

    private void PecaPodeSerSalva(Peca peca) throws Exception{
        StringBuilder msg = new StringBuilder();
        if(peca.getNome().isEmpty()){
            msg.append("O nome da peca deve ser informado");
        }


        if(msg.length() > 0){
            throw new Exception(msg.toString());
        }
    }



}
