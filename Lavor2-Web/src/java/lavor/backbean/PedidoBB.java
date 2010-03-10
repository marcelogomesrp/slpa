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

    public PedidoBB() {
    }

    public String DoNovoPedidoPage(){
        this.pedidoMB.setPedido(new Pedido());
        return "/pedido/novo";
    }



}
