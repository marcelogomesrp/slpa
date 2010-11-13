/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.controller;

import br.com.guaraba.controller.util.JsfUtil;
import br.com.guaraba.facade.PostoAtendimentoFacade;
import br.com.guaraba.modelo.PostoAtendimento;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author marcelo
 */
@ManagedBean
@SessionScoped
public class PostoAtendimentoController implements Serializable{
    static Logger logger = Logger.getLogger(PostoAtendimentoController.class.getName());

    private PostoAtendimento postoAtendimento;
    @EJB
    private PostoAtendimentoFacade postoAtendimentoFacade;

    /** Creates a new instance of PostoController */
    public PostoAtendimentoController() {
        this.postoAtendimento = new PostoAtendimento();
    }

    public PostoAtendimento getPostoAtendimento() {
        return postoAtendimento;
    }

    public void setPostoAtendimento(PostoAtendimento postoAtendimento) {
        this.postoAtendimento = postoAtendimento;
    }

    public String Login(){
        logger.debug("Tentativa de login do posto e:" + postoAtendimento.getEmail() + " p:" + postoAtendimento.getSenha());
        postoAtendimento = postoAtendimentoFacade.FindByEmailSenha(postoAtendimento);
        if(postoAtendimento.getId() != null){
            logger.debug("login do posto realizado com sucesso id" + postoAtendimento.getId());
            return "/posto/Index";
        }else{
            logger.debug("Erro ao realizar login do posto");
            JsfUtil.addErrorMessage(new Exception("Login invalido"), "Usuário ou senha inválido");
            return "Index";
        }
    }

}
