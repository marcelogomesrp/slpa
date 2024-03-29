/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.entidade.Cliente;
import lavor.entidade.Equipamento;
import lavor.entidade.ItemPedido;
import lavor.entidade.Peca;
import lavor.entidade.Pedido;
import lavor.service.ItemPedidoService;
import lavor.service.PecaService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */
@Controller("pedido2MB")
@Scope("session")
public class Pedido2MB {
    private Pedido pedido;
    private ListDataModel pedidos;
    private ListDataModel itensPedido;
    private ListDataModel itensPedidoSelecionado;
    @Resource
    private ItemPedidoService itemPedidoService;
    @Resource
    private PecaService pecaService;

    private List<String> defeitos;

    public List<String> getDefeitos() {
        return defeitos;
    }

    public void setDefeitos(List<String> defeitos) {
        this.defeitos = defeitos;
    }



    

    public Pedido2MB() {
        this.pedido         = new Pedido();
        this.pedidos        = new ListDataModel();
        this.itensPedido    = new ListDataModel();
        this.itensPedidoSelecionado = new ListDataModel();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedidoPeca(Pedido pedido){
        this.pedido = pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;

        if(this.pedido.getCliente().getId() != null){

        if(pedido.getId() != null){
        HashMap<Long, ItemPedido> item = new HashMap<Long, ItemPedido>();
        Equipamento equipamento = pedido.getEquipamentoCliente().getEquipamento();

        for(Peca peca:pecaService.PesquisarPorEquipamento(equipamento)){
            item.put(peca.getId(), new ItemPedido(pedido, peca, 0, 0F));
        }

        for(ItemPedido itemDoPedido:itemPedidoService.PesquisarPorPedido(pedido)){
            item.put(itemDoPedido.getPeca().getId(), itemDoPedido);
        }

        //this.setItensPedido(item.values().toArray());
        List<ItemPedido> itensAdd = new ArrayList<ItemPedido>();
        for(ItemPedido itemDoPedido:item.values()){
            itensAdd.add(itemDoPedido);
        }

        this.setItensPedido(itensAdd);
        }else{
                this.setItensPedido(new ArrayList<ItemPedido>());
        }
        }


    }

    public ListDataModel getPedidos() {
        return pedidos;
    }

    public void setPedidos(ListDataModel pedidos) {
        this.pedidos = pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = new ListDataModel(pedidos);
    }

    public ListDataModel getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(ListDataModel itensPedido) {
        this.itensPedido = itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = new ListDataModel(itensPedido);
    }

    public ListDataModel getItensPedidoSelecionado() {
        return itensPedidoSelecionado;
    }

    public void setItensPedidoSelecionado(ListDataModel itensPedidoSelecionado) {
        this.itensPedidoSelecionado = itensPedidoSelecionado;
    }


}
