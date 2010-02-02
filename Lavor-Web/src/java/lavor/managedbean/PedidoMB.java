/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
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
    List<SelectItem> garantia;
    private Boolean adicionadoPedido;

    private ListDataModel pedidoItens;

    private Boolean temPedido;

    public List<SelectItem> getGarantia() {
        return garantia;
    }

    public void setGarantia(List<SelectItem> garantia) {
        this.garantia = garantia;
    }

    public ListDataModel getPedidoItens() {
        return pedidoItens;
    }

    public void setPedidoItens(ListDataModel pedidoItens) {
        this.pedidoItens = pedidoItens;
    }


    

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public PedidoMB() {
        this.pedido = new Pedido();
        this.pedidos = new ListDataModel();
        this.garantia = new ArrayList<SelectItem>();
        this.garantia.add(new SelectItem(Boolean.TRUE, "Sim"));
        this.garantia.add(new SelectItem(Boolean.FALSE, "Não"));
        this.pedidoItens = new ListDataModel();
        this.temPedido = Boolean.FALSE;
        this.adicionadoPedido = Boolean.FALSE;
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
        this.SincronizaPecaSolicitadas();
    }

    public void SincronizaPecaSolicitadas(){
        this.pecasSolicitada = new ListDataModel(this.pedido.getItensPedido());
    }

    public void SincronizarPedidoItens(){
        this.pedidoItens = new ListDataModel(pedido.getItensPedido());
    }

    public Boolean getTemPedido() {
        return temPedido;
    }

    public void setTemPedido(Boolean temPedido) {
        this.temPedido = temPedido;
    }

    public Boolean getAdicionadoPedido() {
        return adicionadoPedido;
    }

    public void setAdicionadoPedido(Boolean adicionadoPedido) {
        this.adicionadoPedido = adicionadoPedido;
    }



}
