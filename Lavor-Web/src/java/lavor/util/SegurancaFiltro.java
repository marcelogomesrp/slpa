/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.util;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import lavor.backbean.PostoDeAtendimentoMB;


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

//                System.out.print("22Filtradooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
//                //PostoDeAtendimentoMB PA = (PostoDeAtendimentoMB)sessao.getAttribute("postoDeAtendimentoMB");
//                //System.out.print("Aquiiiiiiii " + PA.getPostoDeAtendimento().getNome());
//
//		String logado = (String)sessao.getAttribute("usuario");
//                System.out.println("Achar aqui !!!@ " + logado);
//
//                System.out.println("Achar usuario2 " + sessao.getAttribute("usuario2"));
//
//
//
//
//
//
//               // String usuario = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
//
//
//                System.out.print("Filtradooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
//                //chain.doFilter(request, response);


                
		//tenta capturar a sessão chamada user
		PostoDeAtendimentoMB posto = (PostoDeAtendimentoMB)sessao.getAttribute("postoDeAtendimentoMB");
//
//
//		//verifica se a sessão existe
		//if(posto != null){
                if(posto.getPostoDeAtendimento().getNome().equalsIgnoreCase("marcelo")){
                        JOptionPane.showMessageDialog(null, "Posto " + posto.getPostoDeAtendimento().getNome());
			chain.doFilter(request, response);
		}
		else{
//			//envia uma mensagem caso o usuário
//			//não tenha se logado
//			sessao.setAttribute("mensage", "Entre com o usuário e a senha");
//                        lavor.util.FacesUtils.mensErro("Para acessar este recurso você deve estar logado");
//
//			//redireciona para a página de login
			((HttpServletResponse)response).sendRedirect("/Lavor-Web/template-client.jspx");
		}
    }

    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
