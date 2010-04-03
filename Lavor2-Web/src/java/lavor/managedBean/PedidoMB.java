/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import javax.faces.model.ListDataModel;
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
    private ListDataModel pedidos;
    private ListDataModel itemPedido;


    public PedidoMB() {
        this.pedido                 = new Pedido();
        this.clienteSelecionado     = Boolean.FALSE;
        this.equipamentoSelecionado = Boolean.FALSE;
        this.pedidos                = new ListDataModel();
        this.itemPedido             = new ListDataModel();
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

    public ListDataModel getPedidos() {
        return pedidos;
    }

    public void setPedidos(ListDataModel pedidos) {
        this.pedidos = pedidos;
    }

    public ListDataModel getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ListDataModel itemPedido) {
        this.itemPedido = itemPedido;
    }

    

}
