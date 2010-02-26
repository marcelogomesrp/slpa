/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import java.io.Serializable;
import lavor.entidade.Cidade;
import lavor.entidade.PostoDeAtendimento;
import lavor.entidade.Usuario;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 *
 * @author marcelo
 */
@Controller("postoDeAtendimentoMB")
@Scope("session")
public class PostoDeAtendimentoMB implements Serializable{

    private PostoDeAtendimento postoDeAtendimento;

    public PostoDeAtendimentoMB() {
        this.postoDeAtendimento = new PostoDeAtendimento();
        postoDeAtendimento.setCidade(new Cidade());
        postoDeAtendimento.setUsuario(new Usuario());
    }

    public PostoDeAtendimento getPostoDeAtendimento() {
        return postoDeAtendimento;
    }

    public void setPostoDeAtendimento(PostoDeAtendimento postoDeAtendimento) {
        this.postoDeAtendimento = postoDeAtendimento;
    }

}
