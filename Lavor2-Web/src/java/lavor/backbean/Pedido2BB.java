/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lavor.entidade.ItemPedido;
import lavor.entidade.Pedido;
import lavor.entidade.Situacao;
import lavor.managedBean.Pedido2MB;
import lavor.service.ItemPedidoService;
import lavor.service.PedidoService;
import lavor.service.ServiceException;
import lavor.utils.FacesUtils;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */
@Controller("pedido2BB")
@Scope("request")
public class Pedido2BB {
    
    @Resource
    private Pedido2MB pedido2MB;
    @Resource
    private PedidoService pedidoService;
    @Resource
    private ItemPedidoService itemPedidoService;

    public Pedido2BB() {

    }

    public List<Pedido> ListarPedido(Situacao situacao){
        return pedidoService.PesquisarPedidoPorSituacao(situacao);
    }


//    public void DoListarPage(ActionEvent event){
    public String DoListarPage(){
        this.pedido2MB.setPedidos(this.ListarPedido(Situacao.Cadastrado));
        return "sucesso";
    }


    public String DoListarPagePrioridade(){
        pedidoService.PesquisarPedidoPorSituacaoEPrioridade(Situacao.Cadastrado, Boolean.TRUE);
        return "sucesso";
    }


    public String DoDetalhes(){
        this.pedido2MB.setPedido((Pedido) pedido2MB.getPedidos().getRowData());
        return "sucesso";
    }



    public String RemoverItem(){
        ItemPedido itemPedido = (ItemPedido) pedido2MB.getItensPedido().getRowData();
        try{
            this.itemPedidoService.Excluir(itemPedido);
        }catch(Exception ex){
            System.out.println("Errrooooooo " + ex.getMessage() + " causeeee " + ex.getCause());
        }
        return "sucesso";
    }

    public String Atualizar(){
        //List<ItemPedido> itens = pedido2MB.getPedido().getItemPedido();
        List<ItemPedido> itens = new ArrayList<ItemPedido>();

        for(int x = 0; x < pedido2MB.getItensPedido().getRowCount(); x++){
            pedido2MB.getItensPedido().setRowIndex(x);
            ItemPedido itemPedido = (ItemPedido) pedido2MB.getItensPedido().getRowData();
            if(itemPedido.getQuantidade() > 0){
                itens.add(itemPedido);
            }
        }
        pedido2MB.getPedido().setItemPedido(itens);
        try{            
            pedidoService.Atualizar(pedido2MB.getPedido());
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Pedido salvo com sucesso" );
        }catch(Exception ex){
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar atualizar.." + ex.getMessage() + "<p /> " + ex.getCause());
        }
        return "sucesso";
    }


}
