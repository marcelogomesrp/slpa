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
        this.pedidoMB.setClienteSelecionado(Boolean.FALSE);
        this.pedidoMB.setEquipamentoSelecionado(Boolean.FALSE);
        return "/pedido/novo";
    }
    
    public String DoNovoEquipamentoPage(){
        if(this.pedidoMB.getClienteSelecionado()){
            // limpar equipamento cliste
            return "/pedido/equipamentonovo";
        }else{
            return "/pedido/novo";
        }
    }





}
