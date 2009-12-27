/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author marcelo
 */
public class ConnectionFactory {

    private static ConnectionFactory instancia = null;
    private EntityManager entityManager;

    private ConnectionFactory(){
            instancia = new ConnectionFactory();
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lavor-CommonsPU");
            instancia.entityManager = emf.createEntityManager();
    }

    public static ConnectionFactory getInstancia() {
        if(instancia == null){
            instancia = new ConnectionFactory();
        }
        return instancia;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
