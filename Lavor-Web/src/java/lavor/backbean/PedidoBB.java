/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
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

    public PedidoBB() {
    }

    public String DoListarPedidoPage(){
        //this.pedidoMB.setPedidos(new ListDataModel(pedidoService.LocalizarTodosOsPedidos()));
        Long id = postoDeAtendimentoMB.getPostoDeAtendimento().getId();
        this.pedidoMB.setPedidos(new ListDataModel(pedidoService.LocalizarPorPostoDeAtendimento(id)));
        return "sucesso";
    }

}
