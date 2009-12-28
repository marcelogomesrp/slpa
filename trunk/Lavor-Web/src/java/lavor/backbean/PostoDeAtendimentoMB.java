/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lavor.backbean;

import lavor.entidade.PostoDeAtendimento;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */
@Controller("postoDeAtendimentoMB")
@Scope("session")
public class PostoDeAtendimentoMB {

    private PostoDeAtendimento postoDeAtendimento;

    public PostoDeAtendimentoMB() {
        this.postoDeAtendimento = new PostoDeAtendimento();
    }

    public PostoDeAtendimento getPostoDeAtendimento() {
        return postoDeAtendimento;
    }

    public void setPostoDeAtendimento(PostoDeAtendimento postoDeAtendimento) {
        this.postoDeAtendimento = postoDeAtendimento;
    }
}
