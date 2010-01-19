/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.entidade.Categoria;
import lavor.entidade.Equipamento;
import lavor.entidade.Peca;
import lavor.entidade.Pedido;
import lavor.entidade.PedidoItem;
import lavor.managedbean.CategoriaMB;
import lavor.managedbean.EquipamentoMB;
import lavor.managedbean.PecaMB;
import lavor.managedbean.PedidoMB;
import lavor.managedbean.PostoDeAtendimentoMB;
import lavor.service.CategoriaService;
import lavor.service.EquipamentoService;
import lavor.service.PecaService;
import lavor.service.PedidoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("garantiaBB")
@Scope("request")
public class GarantiaBB {
    @Resource
    private CategoriaMB categoriaMB;

    @Resource
    private CategoriaService categoriaService;

    @Resource
    private EquipamentoMB equipamentoMB;

    @Resource
    private EquipamentoService equipamentoService;

    @Resource
    private PecaService pecaService;

    @Resource
    private PecaMB pecaMB;

    @Resource
    private PedidoMB pedidoMB;

    @Resource
    private PedidoService pedidoService;

    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;

    public GarantiaBB() {
    }

    public String DoSolicitarGarantiaPage(){
        this.categoriaMB.setListaDeCategoria(new ListDataModel(categoriaService.LocalizarTodasCategoria()));
        return "solicitarGarantiaPage";
    }

    public String SelecionarCategoria(){
        this.categoriaMB.setCategoria((Categoria) categoriaMB.getListaDeCategoria().getRowData());
        this.equipamentoMB.setListaDeEquipamento(new ListDataModel(equipamentoService.LocalizarEquipamentoPorCategoria(categoriaMB.getCategoria().getId())));
        return "sucesso";
    }

    public String DoSolicitarPecaPage(){
        this.equipamentoMB.setEquipamento((Equipamento) equipamentoMB.getListaDeEquipamento().getRowData());
        //this.pecaMB.setListaDePecas(new ListDataModel(pecaService.BuscarPecaPorEquipamento(equipamentoMB.getEquipamento().getId())));
        this.pecaMB.setListaDePecas(new ListDataModel(pecaService.BuscarTodasAsPecas() ));
        return "sucesso";
    }

    public String SolicitarPeca(){
        PedidoItem pedidoItem = new PedidoItem();
        pedidoItem.setPeca((Peca) pecaMB.getListaDePecas().getRowData());
        pedidoMB.getPedido().getItensPedido().add(pedidoItem);
        return "sucesso";
    }

    public String DoConfirma(){
        pedidoMB.getPedido().setPostoDeAtendimento(postoDeAtendimentoMB.getPostoDeAtendimento());
        pedidoService.SalvarPedido(pedidoMB.getPedido());
        pedidoMB.setPedido(new Pedido());
        return "sucesso";
    }

}
