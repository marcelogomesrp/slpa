/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.controller;

import br.com.guaraba.controller.util.JsfUtil;
import br.com.guaraba.facade.AdministradorFacade;
import br.com.guaraba.modelo.Administrador;
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
public class AdministradorController implements Serializable{
    static Logger logger = Logger.getLogger(AdministradorController.class.getName());
   private Administrador administrador;
    @EJB
    private AdministradorFacade administradorFacade;


    /** Creates a new instance of AdministradorController */
    public AdministradorController() {
        this.administrador = new Administrador();
    }


    public String Login(){
        logger.debug("Tentativa de login do posto e:" + administrador.getEmail() + " s:" + administrador.getSenha());
        administrador = administradorFacade.FindByEmailSenha(administrador);
        if(administrador.getId() != null){
            logger.debug("Login do administrador realizado com sucesso id" + administrador.toString());
            return "/adm/Index";
        }else{
            logger.debug("Erro ao realizar login do administrador");
            JsfUtil.addErrorMessage(new Exception("Login invalido"), "Usuário ou senha inválido");
            return "IndexAdm";
        }
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    
}
