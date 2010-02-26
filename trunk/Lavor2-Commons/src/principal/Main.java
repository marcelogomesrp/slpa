/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import lavor.dao.UsuarioDao;
import lavor.entidade.Usuario;
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
        UsuarioDao usuarioDao       = (UsuarioDao) context.getBean("usuarioDao");
        Usuario usuario             = usuarioDao.pesquisarPorId(100L);
        if (usuario.getId() == null){
            System.out.println("Usuario nao econtrado");
        }else{
            System.out.println("Usuario ok");
        }
        
        //System.out.println(usuario.toString());
        System.out.println("valor");
    }

}
