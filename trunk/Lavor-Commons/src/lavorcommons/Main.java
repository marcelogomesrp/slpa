/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavorcommons;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import lavor.entidade.Equipamento;
import lavor.entidade.Peca;
import lavor.service.EquipamentoService;
import lavor.service.PecaService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author marcelo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//        PostoDeAtendimento postoDeAtendimento = new PostoDeAtendimento();
//        postoDeAtendimento.setNome("Marcelo");
//        System.out.println("Posto de atendimento " + postoDeAtendimento.getNome() ) ;
//        PostoDeAtendimentoDao postoDeAtendimentoDao = new PostoDeAtendimentoDaoImp();
//        postoDeAtendimentoDao.salvar(postoDeAtendimento);
//        System.out.println("Posto Salvo com sucesso");

        
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //PostoDeAtendimento pa = (PostoDeAtendimento) context.getBean("postoDeAtendimento");
        //pa.setNome("ok spring");
        //System.out.println("Nome " + pa.getNome());

        
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        for(int x = 0; x < 10000; x++){
//            PostoDeAtendimentoService pa = (PostoDeAtendimentoService) context.getBean("postoDeAtendimentoService");
//            PostoDeAtendimento postoDeAtendimento = new PostoDeAtendimento();
//            postoDeAtendimento.setNome("Salvo com o hibernate + c3p0 n" + x);
//            //pa.Salvar(postoDeAtendimento);
//        }
        

//        org.apache.log4j.Logger log = org.apache.log4j.Logger.getRootLogger();
//        JOptionPane.showMessageDialog(null, "Log esta " + log.getLevel().toString());
//        log.debug("mensagem debug ***************************************************************************************************************************");
//        log.error("mensagem erro ***************************************************************************************************************************");

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EquipamentoService equipamentoService = (EquipamentoService) context.getBean("equipamentoService");
        PecaService pecaService = (PecaService) context.getBean("pecaService");
        Equipamento e = new Equipamento();
//        Peca peca = new Peca();
//        peca.setNome("peca id ok 12");
//        e.setNome("teste2");

       // Peca peca2 = pecaService.AtualizarPeca(peca);
        Peca peca2 = pecaService.BuscarPecaPorId(12L);

        System.out.println("ID: " + peca2.getId() + "\tNome:" + peca2.getNome());


        e.setNome("nome2");
        e.getPecas().add(peca2);
        List<Peca> pecas = new ArrayList<Peca>();
        pecas.add(peca2);
        e.setPecas(pecas);
        //equipamentoService.Atualizar(e);


        //Equipamento t = Main.persist(e);
        
        //19
        
        Equipamento t = equipamentoService.LocalizarPorID(19L);



        System.out.println("Total: " + t.getId());
        System.out.println("Lista: " + t.getPecas().size());
//        for(Peca p:t.getPecas()){
//            System.out.println("Peca = " + p.getNome() + "id = " + p.getId() );
//        }
        //pecaService.SalvarPeca(peca);

        //e.getPecas().add(peca2);
        //equipamentoService.Atualizar(e);
        
    }

    public static Equipamento persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lavor-CommonsPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Equipamento eq = null;
        try {
            //em.persist(object);
            eq = (Equipamento) em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return eq;
    }

}
