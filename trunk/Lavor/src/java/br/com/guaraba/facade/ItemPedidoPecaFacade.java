/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.facade;

import br.com.guaraba.modelo.ItemPedidoPeca;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author marcelo
 */

@Stateless
public class ItemPedidoPecaFacade extends AbstractFacade<ItemPedidoPeca>{

    @PersistenceContext(unitName = "LavorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemPedidoPecaFacade() {
        super(ItemPedidoPeca.class);
    }
    

}

