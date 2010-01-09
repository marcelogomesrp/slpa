/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import lavor.dao.PostoDeAtendimentoDao;
import lavor.entidade.PostoDeAtendimento;


/**
 *
 * @author marcelo
 */
public class PostoDeAtendimentoService {
    PostoDeAtendimentoDao postoDeAtendimentoDao;

    public PostoDeAtendimentoService() {
        
    }

    public PostoDeAtendimentoDao getPostoDeAtendimentoDao() {
        return postoDeAtendimentoDao;
    }

    public void setPostoDeAtendimentoDao(PostoDeAtendimentoDao postoDeAtendimentoDao) {
        this.postoDeAtendimentoDao = postoDeAtendimentoDao;
    }
    

    public PostoDeAtendimento Salvar(PostoDeAtendimento postoDeAtendimento) throws Exception{
        this.PostoDeAtendimentoPodeSerSalvo(postoDeAtendimento);
        postoDeAtendimento = postoDeAtendimentoDao.salvar(postoDeAtendimento);
        return postoDeAtendimento;
    }

    private void PostoDeAtendimentoPodeSerSalvo(PostoDeAtendimento postoDeAtendimento) throws Exception{
        if(postoDeAtendimento.getNome().equalsIgnoreCase("marcelogomes")){
            throw new Exception("Posto indevido pq sou chato");
        }
        
    }

}
