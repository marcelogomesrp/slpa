/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.junit.Assert.*;

/**
 *
 * @author marcelo
 */
public class CidadeServiceTest {
    private CidadeService cidadeService;

    public CidadeServiceTest() {
        ApplicationContext context  = new ClassPathXmlApplicationContext("applicationContext.xml");
       // this.cidadeService = (CidadeService) context.getBean("cidadeService");
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testGetCidadeDao() {
    }

    @Test
    public void testSetCidadeDao() {
    }

    @Test
    public void testPesquisarPorEstado() {
    }

    @Test
    public void testPesquisarPorCidadeEstado() {
    }

    @Test
    public void testSalvar() throws Exception {
    }

}