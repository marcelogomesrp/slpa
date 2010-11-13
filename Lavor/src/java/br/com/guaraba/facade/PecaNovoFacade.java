/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.facade;

import br.com.guaraba.modelo.PecaNovo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcelo
 */
@Stateless
public class PecaNovoFacade extends AbstractFacade<PecaNovo>{
    @PersistenceContext(unitName = "LavorPU")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public PecaNovoFacade() {
        super(PecaNovo.class);
    }


}