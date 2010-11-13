/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.facade;


import br.com.guaraba.modelo.PecaImportacao;
import java.text.MessageFormat;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author marcelo
 */
@Stateless
public class PecaImportacaoFacade extends AbstractFacade<PecaImportacaoFacade>{

    @PersistenceContext(unitName = "LavorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PecaImportacaoFacade() {
        super(PecaImportacaoFacade.class);
    }
    
    
    @Override
    public int count(){        
        Query q = getEntityManager().createQuery("SELECT COUNT(i) FROM PecaImportacao i");
        return ((Long) q.getSingleResult()).intValue();
    }
}
