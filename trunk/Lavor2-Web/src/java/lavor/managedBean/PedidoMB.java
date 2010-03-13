/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import lavor.entidade.Pedido;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("pedidoMB")
@Scope("session")
public class PedidoMB {
    private Pedido pedido;
    private Boolean clienteSelecionado;
    private Boolean equipamentoSelecionado;


    public PedidoMB() {
        this.pedido = new Pedido();
        this.clienteSelecionado = Boolean.FALSE;
        this.equipamentoSelecionado = Boolean.FALSE;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Boolean getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Boolean clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public Boolean getEquipamentoSelecionado() {
        return equipamentoSelecionado;
    }

    public void setEquipamentoSelecionado(Boolean equipamentoSelecionado) {
        this.equipamentoSelecionado = equipamentoSelecionado;
    }

}
