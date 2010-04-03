/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import java.io.Serializable;
import javax.faces.model.ListDataModel;
import lavor.entidade.Mensagem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("mensagemMB")
@Scope("session")
public class MensagemMB implements Serializable{

    private Mensagem mensagem;
    private ListDataModel mensagens;

    public MensagemMB() {
        this.mensagem  = new Mensagem();
        this.mensagens = new ListDataModel();
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public ListDataModel getMensagens() {
        return mensagens;
    }

    public void setMensagens(ListDataModel mensagens) {
        this.mensagens = mensagens;
    }


}
