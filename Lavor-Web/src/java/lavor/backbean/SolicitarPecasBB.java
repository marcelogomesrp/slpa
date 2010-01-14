/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.entidade.ItemPedido;
import lavor.entidade.Peca;
import lavor.entidade.Pedido;
import lavor.entidade.PedidoItem;
import lavor.managedbean.PecaMB;
import lavor.managedbean.PedidoMB;
import lavor.managedbean.PostoDeAtendimentoMB;
import lavor.service.PecaService;
import lavor.service.PedidoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("solicitarPecasBB")
@Scope("request")
public class SolicitarPecasBB {
    @Resource
    private PecaService pecaService;
    @Resource
    private PecaMB pecaMB;
    @Resource
    private PedidoMB pedidoMB;
    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;
    @Resource
    private PedidoService pedidoService;





    public SolicitarPecasBB() {
    }

    public PecaMB getPecaMB() {
        return pecaMB;
    }

    public ListDataModel getListaDePecas() {
        List<Peca> pecas = pecaService.BuscarTodasAsPecas();
        pecaMB.setListaDePecas(new ListDataModel(pecaService.BuscarTodasAsPecas()));
        return (ListDataModel) pecaMB.getListaDePecas();
    }

    public String AddItem(){
        PedidoItem pedidoItem = new PedidoItem();
        pedidoItem.setPeca((Peca) pecaMB.getListaDePecas().getRowData());
        this.pedidoMB.getPedido().getItensPedido().add(pedidoItem);
        return "sucesso";
    }

    public String DoConfirmaSolicitarPeca(){
//        pedidoMB.getPedido().setPostoDeAtendimento(postoDeAtendimentoMB.getPostoDeAtendimento());
        pedidoMB.getPedido().setDataDaSolicitacao(new Date());
        this.pedidoMB.setPecasSolicitada(new ListDataModel(pedidoMB.getPedido().getItensPedido()));

        return "sucesso";
    }

    

    public String ConfirmaPedido(){
        pedidoService.SalvarPedido(this.pedidoMB.getPedido());
        this.pedidoMB.setPedido(new Pedido());
        return "service";
    }



}
