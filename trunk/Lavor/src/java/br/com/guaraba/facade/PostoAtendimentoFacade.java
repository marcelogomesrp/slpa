/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.facade;

import br.com.guaraba.modelo.PostoAtendimento;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author marcelo
 */
@Stateless
public class PostoAtendimentoFacade extends AbstractFacade<PostoAtendimento>{
    static Logger logger = Logger.getLogger(PostoAtendimentoFacade.class.getName());
    @PersistenceContext(unitName = "LavorPU")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public PostoAtendimentoFacade() {
        super(PostoAtendimento.class);
    }
    
    public PostoAtendimento FindByEmailSenha(PostoAtendimento postoAtendimento){
        String SQL = "SELECT p from PostoAtendimento p  WHERE email = :email and senha = :senha";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("email", postoAtendimento.getEmail());
        params.put("senha", postoAtendimento.getSenha());
        List<PostoAtendimento> listaPostoAtendimento = findByParam(SQL, params);
        logger.debug("FindByEmailSenha, localizado " + listaPostoAtendimento.size() + " posto de atendimento");
        if(listaPostoAtendimento.size() == 1  ){
            return listaPostoAtendimento.get(0);
        }
            //JsfUtil.addErrorMessage(new Exception("Login invalido"), "Erro ao realizar o login");
            //return this.prepareLogin();
        return new PostoAtendimento();
    }

}





