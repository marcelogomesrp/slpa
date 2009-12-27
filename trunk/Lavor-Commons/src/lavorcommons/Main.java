/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavorcommons;

import lavor.dao.PostoDeAtendimentoDao;
import lavor.dao.imp.PostoDeAtendimentoDaoImp;
import lavor.entidade.PostoDeAtendimento;
import lavor.service.PostoDeAtendimentoService;
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
    public static void main(String[] args) {
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

        
        
        for(int x = 0; x < 100; x++){
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            PostoDeAtendimentoService pa = (PostoDeAtendimentoService) context.getBean("postoDeAtendimentoService");
            PostoDeAtendimento postoDeAtendimento = new PostoDeAtendimento();
            postoDeAtendimento.setNome("Salvo com o hibernate" + x);
            pa.Salvar(postoDeAtendimento);
        }
        
    }

}
