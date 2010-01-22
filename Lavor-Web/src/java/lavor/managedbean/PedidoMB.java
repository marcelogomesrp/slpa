/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedbean;

import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.entidade.Pedido;
import lavor.entidade.Status;
import lavor.service.PedidoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("pedidoMB")
@Scope("session")
public class PedidoMB {
    @Resource
    private PedidoService pedidoService;

    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;

    private Pedido pedido;
    private ListDataModel pecasSolicitada;
    private ListDataModel pedidos;


    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public PedidoMB() {
        this.pedido = new Pedido();
        this.pedidos = new ListDataModel();
    }

    public ListDataModel getPecasSolicitada() {
        return pecasSolicitada;
    }

    public void setPecasSolicitada(ListDataModel pecasSolicitada) {
        this.pecasSolicitada = pecasSolicitada;
    }

    public ListDataModel getPedidos() {
        Long id = postoDeAtendimentoMB.getPostoDeAtendimento().getId();
        if(pedidos == null){
            this.pedidos = new ListDataModel(pedidoService.LocalizarPorPostoDeAtendimentoEStatus(id, Status.Cadastrado));
        }
        return pedidos;
    }

    public void setPedidos(ListDataModel pedidos) {
        this.pedidos = pedidos;
    }

}
