/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedbean;

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
    private ListDataModel pecasSolicitada;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public PedidoMB() {
        this.pedido = new Pedido();
    }

    public ListDataModel getPecasSolicitada() {
        return pecasSolicitada;
    }

    public void setPecasSolicitada(ListDataModel pecasSolicitada) {
        this.pecasSolicitada = pecasSolicitada;
    }

}