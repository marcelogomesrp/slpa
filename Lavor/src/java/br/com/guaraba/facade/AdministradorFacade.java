/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.facade;

import br.com.guaraba.modelo.Administrador;
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
public class AdministradorFacade extends AbstractFacade<Administrador>{
    static Logger logger = Logger.getLogger(AdministradorFacade.class.getName());
    @PersistenceContext(unitName = "LavorPU")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }
    
    public Administrador FindByEmailSenha(Administrador administrador){
        String SQL = "SELECT a from Administrador a  WHERE email = :email and senha = :senha";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("email", administrador.getEmail());
        params.put("senha", administrador.getSenha());
        List<Administrador> listaAdministrador =  findByParam(SQL, params);
        logger.debug("localizado " + listaAdministrador.size() + " administradores " );
        if(listaAdministrador.size() == 1  ){
            return listaAdministrador.get(0);
        }
        return new Administrador();       
    }

}





