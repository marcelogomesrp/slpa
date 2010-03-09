/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
import lavor.entidade.Peca;
import lavor.managedBean.PecaMB;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("pecaBB")
@Scope("request")
public class PecaBB {

    @Resource
    private PecaMB pecaMB;

    public PecaBB() {
    }

    public String DoNovoPage(){
        return "/peca/novo";
    }

}
