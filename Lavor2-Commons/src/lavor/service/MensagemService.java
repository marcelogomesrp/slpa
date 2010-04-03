/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.util.List;
import lavor.dao.MensagemDao;
import lavor.entidade.Mensagem;

/**
 *
 * @author marcelo
 */
public class MensagemService {
    private MensagemDao mensagemDao;
    private ServiceException serviceException;

    public MensagemDao getMensagemDao() {
        return mensagemDao;
    }

    public void setMensagemDao(MensagemDao mensagemDao) {
        this.mensagemDao = mensagemDao;
    }

    

    public MensagemService() {
        this.serviceException = new ServiceException();
    }



    public Mensagem Salvar(Mensagem mensagem){
        return this.mensagemDao.salvar(mensagem);
    }

    public List<Mensagem> Todas(){
        return this.mensagemDao.todos();
    }

    public void Excluir(Mensagem mensagem){
        this.mensagemDao.excluir(mensagem);
    }

    public Mensagem Atualizar(Mensagem mensagem){
        return this.mensagemDao.atualizar(mensagem);
    }


}
