/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.controle;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lavor.entidade.TipoUsuario;
import lavor.managedBean.UsuarioMB;



/**
 *
 * @author marcelo
 */
public class ValidarAdmin implements Filter{

    public void init(FilterConfig filterConfig) throws ServletException {     
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
         HttpServletRequest req = (HttpServletRequest)request;
         UsuarioMB usuarioMB = (UsuarioMB)req.getSession().getAttribute("usuarioMB");
         if(usuarioMB.getUsuario().getTipoUsuario() != null){
            if (usuarioMB.getUsuario().getTipoUsuario().equals(TipoUsuario.administrador)){
               chain.doFilter(request, response);
            }else{
              HttpServletResponse res = (HttpServletResponse)response;
              res.sendRedirect("http://localhost:8080/Lavor2-Web/");
            }
         }else{
             HttpServletResponse res = (HttpServletResponse)response;
             res.sendRedirect("http://localhost:8080/Lavor2-Web/");
         }
        }catch(Exception ex){
             HttpServletResponse res = (HttpServletResponse)response;
             res.sendRedirect("http://localhost:8080/Lavor2-Web/");
        }

    }

    public void destroy() {
     
    }



}
