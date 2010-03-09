/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import lavor.dao.PecaDao;
import lavor.entidade.Peca;
import lavor.utils.GenericExceptionMessageType;

/**
 *
 * @author marcelo
 */
public class PecaService {
    private PecaDao pecaDao;

    private ServiceException serviceException;

    public PecaService() {
        this.serviceException = new ServiceException();
    }

    public PecaDao getPecaDao() {
        return pecaDao;
    }

    public void setPecaDao(PecaDao pecaDao) {
        this.pecaDao = pecaDao;
    }

    private void PosicaoValida(int posicao) {
        // buscar por equipamento posicao
        serviceException.addMessage(GenericExceptionMessageType.WARNING, "A posicao já foi usada");
    }

    private boolean PodeSerSalva(Peca peca) throws ServiceException {
        this.PosicaoValida(peca.getPosicao());
        if (serviceException.hasMessages()) {
            throw serviceException;
        }
        return true;
    }

    public Peca Salvar(Peca peca) throws ServiceException{
        this.serviceException = new ServiceException();
        try{
            if(this.PodeSerSalva(peca)){
                peca = this.pecaDao.atualizar(peca);
            }
        }catch(Exception ex){
            throw new ServiceException("Ocorreu um erro ao tentar salvar", ex);
        }
        return peca;
    }









}
