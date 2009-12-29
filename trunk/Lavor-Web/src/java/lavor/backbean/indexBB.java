/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lavor.backbean;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import lavor.service.PostoDeAtendimentoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 * @Scope("session")
 */
@Controller("indexBB")
@Scope("request")
public class indexBB {

    private String nome;
    @Resource
    private PostoDeAtendimentoService postoDeAtendimentoService;
    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;


    public indexBB() {
        //this.postoDeAtendimento = new PostoDeAtendimento();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PostoDeAtendimentoService getPostoDeAtendimentoService() {
        return postoDeAtendimentoService;
    }

    public void setPostoDeAtendimentoService(PostoDeAtendimentoService postoDeAtendimentoService) {
        this.postoDeAtendimentoService = postoDeAtendimentoService;
    }

    public PostoDeAtendimentoMB getPostoDeAtendimentoMB() {
        return postoDeAtendimentoMB;
    }

    public void setPostoDeAtendimentoMB(PostoDeAtendimentoMB postoDeAtendimentoMB) {
        this.postoDeAtendimentoMB = postoDeAtendimentoMB;
    }

    public String SalvarPostoDeAtendimento() {

        //Log4JUtils.LogFatal("Mensagem do log Fatal feito com a classe legal heheeheh");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("usuario", "marcelo");

        try {
            this.postoDeAtendimentoService.Salvar(postoDeAtendimentoMB.getPostoDeAtendimento());
            //this.postoDeAtendimentoMB.setPostoDeAtendimento(new PostoDeAtendimento());
            lavor.util.FacesUtils.mensInfo("Posto salvo com sucesso");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario2", "marcelogomes");
            

        } catch (Exception ex) {
            lavor.util.FacesUtils.mensErro("Erro ao salvar \n" + ex.getMessage());
        }
        return "sucesso";
    }
    public String Ok(){
        return "logar";
    }
}
