/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import java.util.List;
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
         ApplicationContext context  = new ClassPathXmlApplicationContext("applicationContext.xml");
         //PostoDeAtendimentoDao postoDeAtendimentoDao = (PostoDeAtendimentoDao) context.getBean("postoDeAtendimentoDao");
         PostoDeAtendimentoService postoDeAtendimentoService = (PostoDeAtendimentoService) context.getBean("postoDeAtendimentoService");

         List<PostoDeAtendimento> postos = postoDeAtendimentoService.pesquisarPorCidade(1L);
         for(PostoDeAtendimento posto:postos){
             System.out.println("Posto " + posto.getId() + posto.getRazaoSocial());

         }
//
//        String regex = "[0-9]+";
//        Pattern pattern = Pattern.compile(regex);
//        String source = "14061-330";
//        Matcher matcher = pattern.matcher(source);
//
//        while(matcher.find()){
//            System.out.println("Encontrado: " + matcher.group());
//        }
//
//        String str = "14061330";
//        //String var = str.replaceAll("[^\\p{L}]", " ");
//        String var = str.replaceAll("[^\\d{L}]", "");
//        System.out.println("Var: " + var);

        
       
//        UsuarioDao usuarioDao       = (UsuarioDao) context.getBean("usuarioDao");
//        Usuario usuario             = usuarioDao.pesquisarPorId(100L);
//        if (usuario.getId() == null){
//            System.out.println("Usuario nao econtrado");
//        }else{
//            System.out.println("Usuario ok");
//        }
//
//        //System.out.println(usuario.toString());
//        System.out.println("valor");
    }

}
