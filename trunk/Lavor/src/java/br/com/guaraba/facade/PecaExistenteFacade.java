/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.facade;

import br.com.guaraba.modelo.PecaExistente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcelo
 */

@Stateless
public class PecaExistenteFacade extends AbstractFacade<PecaExistente>{

    @PersistenceContext(unitName = "LavorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PecaExistenteFacade() {
        super(PecaExistente.class);
    }

}

