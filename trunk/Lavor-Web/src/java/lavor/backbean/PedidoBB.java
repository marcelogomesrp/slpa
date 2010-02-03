/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.entidade.Pedido;
import lavor.entidade.Status;
import lavor.managedbean.PedidoMB;
import lavor.managedbean.PostoDeAtendimentoMB;
import lavor.service.PedidoService;
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
    private PedidoService pedidoService;
    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;
    private String mesBuscar;
    private String statusBuscar;

    public PedidoBB() {
    }

    public String DoListarPedidoPage(){
        //this.pedidoMB.setPedidos(new ListDataModel(pedidoService.LocalizarTodosOsPedidos()));
        Long id = postoDeAtendimentoMB.getPostoDeAtendimento().getId();
        this.pedidoMB.setPedidos(new ListDataModel(pedidoService.LocalizarPorPostoDeAtendimento(id)));
        return "sucesso";
    }

    public String DoListarMensagens(){
        Long id = postoDeAtendimentoMB.getPostoDeAtendimento().getId();
        this.pedidoMB.setPedidos(new ListDataModel(pedidoService.LocalizarPorPostoDeAtendimentoEStatus(id, Status.Cadastrado)));
        return "sucesso";
    }

    public String DoDetalhePage(){
        pedidoMB.setPedido((Pedido) pedidoMB.getPedidos().getRowData());
        pedidoMB.SincronizarPedidoItens();
        return "DoDetalhePage";
    }

    public String DoEditarPedidoPage(){
        pedidoMB.setPedido((Pedido) pedidoMB.getPedidos().getRowData());
        return "DoEditarPedidoPage";
    }

    public String SalvarPedido(){
        try{
            Pedido pedido = pedidoService.SalvarPedido(pedidoMB.getPedido());
            lavor.util.FacesUtils.mensInfo("Pedido salvo com sucesso");
        }catch(Exception ex){
            lavor.util.FacesUtils.mensErro("Erro ao salvar pedido");
        }
        return "sucesso";
    }


    public String DoListarPedidoDoMesPage(){
        this.pedidoMB.setPedidos(new ListDataModel());
        return "ListarPedidoDomes";
    }

    public String BuscarPorAnoMes(){
        //System.out.println("Valores = " + mesBuscar + " " + statusBuscar) ;
        Long id = postoDeAtendimentoMB.getPostoDeAtendimento().getId();
        pedidoMB.setPedidos(new ListDataModel(pedidoService.LocalizarPorPostoDeAtendimentoMesEStatus(id, mesBuscar, statusBuscar)));
        return "sucesso";
    }

    public String getMesBuscar() {
        return mesBuscar;
    }

    public void setMesBuscar(String mesBuscar) {
        this.mesBuscar = mesBuscar;
    }

    public String getStatusBuscar() {
        return statusBuscar;
    }

    public void setStatusBuscar(String statusBuscar) {
        this.statusBuscar = statusBuscar;
    }





    

}
