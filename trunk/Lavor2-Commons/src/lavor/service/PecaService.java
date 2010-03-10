/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.util.ArrayList;
import java.util.List;
import lavor.dao.PecaDao;
import lavor.entidade.Equipamento;
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

    private List<Peca> PesquisarPorPosicaoEquipamento(int posicao, Equipamento equipamento) {
        return this.pecaDao.PesquisarPorPosicaoEquipamento(posicao, equipamento);
    }

    private void PosicaoValida(int posicao, Equipamento equipamento) {
        List<Peca> pecas = new ArrayList<Peca>();
        pecas = this.PesquisarPorPosicaoEquipamento(posicao, equipamento);
        if(pecas.size() != 0){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "A posicao já foi usada");
        }
    }

    private boolean PodeSerSalva(Peca peca) throws ServiceException {
        this.PosicaoValida(peca.getPosicao(), peca.getEquipamento());
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

    public List<Peca> PesquisarPorEquipamento(Equipamento equipamento) {
        return this.pecaDao.PesquisarPorEquipamento(equipamento);
    }













}
