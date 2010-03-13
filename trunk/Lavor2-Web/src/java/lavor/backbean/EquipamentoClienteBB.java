/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.io.Serializable;
import javax.annotation.Resource;
import lavor.managedBean.EquipamentoClienteMB;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("equipamentoClienteBB")
@Scope("request")
public class EquipamentoClienteBB implements Serializable{

    @Resource
    private EquipamentoClienteMB equipamentoClienteMB;

    public EquipamentoClienteBB() {
    }

    public String SalvarEManter(){
        return "sucesso";
    }



}
