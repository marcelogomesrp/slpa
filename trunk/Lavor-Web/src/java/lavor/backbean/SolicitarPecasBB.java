/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
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

    // private ListDataModel pecasSolicitas;






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
        Peca pecaEscolhida = (Peca) pecaMB.getListaDePecas().getRowData();
        pedidoItem.setPeca(pecaEscolhida);
        pedidoItem.setValorUnitario(pecaEscolhida.getValor());
        this.pedidoMB.getPedido().getItensPedido().add(pedidoItem);
        this.pedidoMB.SincronizaPecaSolicitadas();
        this.pedidoMB.setTemPedido(Boolean.TRUE);
        return "sucesso";
    }

    public String DoConfirmaSolicitarPeca(){
        pedidoMB.getPedido().setDataDaSolicitacao(new Date());
        this.pedidoMB.setPecasSolicitada(new ListDataModel(pedidoMB.getPedido().getItensPedido()));
        this.pedidoMB.getPedido().setPostoDeAtendimento(postoDeAtendimentoMB.getPostoDeAtendimento());
        return "sucesso";
    }

    public String ConfirmaPedido(){
        //this.pedidoMB.getPedido().setPostoDeAtendimento(postoDeAtendimentoMB.getPostoDeAtendimento());
        Pedido pedido = pedidoService.SalvarPedido(this.pedidoMB.getPedido());        
        lavor.util.FacesUtils.mensInfo("Pedido adicionado com sucesso nº" + pedido.getId() + " valor " + pedido.getValorTotal());
        this.pedidoMB.setPecasSolicitada(new ListDataModel());
        this.pedidoMB.setPedido(new Pedido());
        return "service";
    }

//    public ListDataModel getPecasSolicitas() {
//        this.pecasSolicitas = new ListDataModel(pedidoMB.getPedido().getItensPedido());
//        return pecasSolicitas;
//    }
//
//    public void setPecasSolicitas(ListDataModel pecasSolicitas) {
//        this.pecasSolicitas = pecasSolicitas;
//    }



    public String RemoverPecaSolicitada(){
        PedidoItem pedidoItemRemover = (PedidoItem) pedidoMB.getPecasSolicitada().getRowData();
        int index = pedidoMB.getPecasSolicitada().getRowIndex();
        lavor.util.FacesUtils.mensInfo("Remover " + pedidoItemRemover.getPeca().getNome() + "index " + index);
        this.pedidoMB.getPedido().getItensPedido().remove(index);
        if(this.pedidoMB.getPedido().getItensPedido().isEmpty()){
            this.pedidoMB.setTemPedido(Boolean.FALSE);
        }
        return "sucesso";
    }

    public String DoSolicitarPecasPage(){
        this.pedidoMB.setPecasSolicitada(new ListDataModel());
        this.pedidoMB.setPedido(new Pedido());
        this.pedidoMB.setPedidos(new ListDataModel());
        return "solicitarPecas";
    }

    public String LimparFiltro(){
        this.pecaMB.setPecaFiltro(new Peca());
        return "sucesso";
    }

}
