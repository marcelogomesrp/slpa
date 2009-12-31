/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedbean;

import java.io.Serializable;
import javax.annotation.Resource;
import lavor.entidade.Peca;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 *
 * @author marcelo
 */

@Controller("pecaMB")
@Scope("session")
public class PecaMB implements Serializable {
    
    private Peca peca;

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public PecaMB() {
        this.peca = new Peca();
    }

}
