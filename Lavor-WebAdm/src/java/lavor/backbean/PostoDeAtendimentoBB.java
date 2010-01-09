/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import lavor.entidade.PostoDeAtendimento;
import lavor.managedbean.PostoDeAtendimentoMB;
import lavor.service.PostoDeAtendimentoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("postoDeAtendimentoBB")
@Scope("request")
public class PostoDeAtendimentoBB {
    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;
    @Resource
    private PostoDeAtendimentoService postoDeAtendimentoService;

    public PostoDeAtendimentoMB getPostoDeAtendimentoMB() {
        return postoDeAtendimentoMB;
    }

    public void setPostoDeAtendimentoMB(PostoDeAtendimentoMB postoDeAtendimentoMB) {
        this.postoDeAtendimentoMB = postoDeAtendimentoMB;
    }

    public String SalvarPostoDeAtendimento(){
        try {
            postoDeAtendimentoService.Salvar(postoDeAtendimentoMB.getPostoDeAtendimento());
            lavor.util.FacesUtils.mensInfo("Posto adicionado com sucesso");
            postoDeAtendimentoMB.setPostoDeAtendimento(new PostoDeAtendimento());
        } catch (Exception ex) {
            lavor.util.FacesUtils.mensErro("Erro ao adicionar posto de atendimento " + ex.getMessage() );
            Logger.getLogger(PostoDeAtendimentoBB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucesso";
    }

}
