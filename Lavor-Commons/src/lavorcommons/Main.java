/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavorcommons;

import javax.swing.JOptionPane;

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

        
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        for(int x = 0; x < 10000; x++){
//            PostoDeAtendimentoService pa = (PostoDeAtendimentoService) context.getBean("postoDeAtendimentoService");
//            PostoDeAtendimento postoDeAtendimento = new PostoDeAtendimento();
//            postoDeAtendimento.setNome("Salvo com o hibernate + c3p0 n" + x);
//            //pa.Salvar(postoDeAtendimento);
//        }
        

        org.apache.log4j.Logger log = org.apache.log4j.Logger.getRootLogger();
        JOptionPane.showMessageDialog(null, "Log esta " + log.getLevel().toString());
        log.debug("mensagem debug ***************************************************************************************************************************");
        log.error("mensagem erro ***************************************************************************************************************************");
        
    }

}
