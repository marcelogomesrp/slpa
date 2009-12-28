/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcelo
 */
public class SegurancaFiltro implements Filter{

    public void init(FilterConfig filterConfig) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        	HttpSession sessao = ((HttpServletRequest) request).getSession();

                System.out.print("Filtradooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");

                chain.doFilter(request, response);


                
		//tenta capturar a sessão chamada user
//		String logado = (String)sessao.getAttribute("user");
//
//
//		//verifica se a sessão existe
//		if(logado != null){
//			chain.doFilter(request, response);
//		}
//		else{
//			//envia uma mensagem caso o usuário
//			//não tenha se logado
//			//sessao.setAttribute("mensage", "Entre com o usuário e a senha");
//                        lavor.util.FacesUtils.mensErro("Para acessar este recurso você deve estar logado");
//
//			//redireciona para a página de login
//			((HttpServletResponse)response).sendRedirect("template-client.jspx");
//		}
    }

    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
