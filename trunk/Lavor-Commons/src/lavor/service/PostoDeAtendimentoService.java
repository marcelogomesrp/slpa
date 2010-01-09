/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public PostoDeAtendimento BuscarPorEmailESenha(String email, String senha){
        PostoDeAtendimento postoDeAtendimento = null;
        String SQL = "SELECT pa from PostoDeAtendimento pa  WHERE email = :email and senha = :senha";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("email", email);
        params.put("senha", senha);
        postoDeAtendimento = (PostoDeAtendimento) postoDeAtendimentoDao.pesqParam(SQL, params);


//        String SQL = "SELECT p FROM PostoDeAtendimento p ";
//        List<PostoDeAtendimento> postos = postoDeAtendimentoDao.listPesq(SQL);

        return postoDeAtendimento;



    }

}
