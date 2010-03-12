/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
import lavor.entidade.Pedido;
import lavor.managedBean.PedidoMB;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("pedidoBB")
@Scope("request")
public class PedidoBB {
    
    @Resource
    private PedidoMB pedidoMB;

    @Resource
    private ClienteBB clienteBB;

    public PedidoBB() {
    }

    public String DoNovoPedidoPage(){
        this.clienteBB.DoNovoClientePage();
        return "/pedido/novo";
    }



}
