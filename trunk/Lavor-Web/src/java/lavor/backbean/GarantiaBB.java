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
import lavor.entidade.Servico;
import lavor.managedbean.CategoriaMB;
import lavor.managedbean.ClienteMB;
import lavor.managedbean.EquipamentoMB;
import lavor.managedbean.FornecedorMB;
import lavor.managedbean.PecaMB;
import lavor.managedbean.PedidoMB;
import lavor.managedbean.PostoDeAtendimentoMB;
import lavor.service.CategoriaService;
import lavor.service.EquipamentoService;
import lavor.service.PecaService;
import lavor.service.PedidoService;
import lavor.service.ServicoService;
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

    @Resource
    private FornecedorMB fornecedorMB;

    @Resource
    private ClienteMB clienteMB;

    @Resource
    private ServicoService servicoService;

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
        Peca pecaEscolhida = (Peca) pecaMB.getListaDePecas().getRowData();
        pedidoItem.setPeca(pecaEscolhida);
        pedidoItem.setValorUnitario(pecaEscolhida.getValor());
        pedidoMB.getPedido().getItensPedido().add(pedidoItem);
        this.pedidoMB.SincronizaPecaSolicitadas();
        return "sucesso";
    }

    public String DoConfirma(){
//        pedidoMB.getPedido().setPostoDeAtendimento(postoDeAtendimentoMB.getPostoDeAtendimento());
//        pedidoMB.getPedido().setCliente(this.clienteMB.getCliente());
//        pedidoMB.getPedido().getCliente().setPostoDeAtendimento(postoDeAtendimentoMB.getPostoDeAtendimento());
//        pedidoMB.getPedido().setFornecedor(fornecedorMB.getFornecedor());
        pedidoService.SalvarPedido(pedidoMB.getPedido());
        lavor.util.FacesUtils.mensInfo("Pedido Adicionado com sucesso"  );
        pedidoMB.setPedido(new Pedido());
        return "sucesso";
    }

    public String DoConfirmacaoPedidoPage() {
        // TODO: Warning - Todo pedido sem garantia esta criando um novo servico
        // TODO: Warning - Descobri pq o finalizar pedido não esta mostrando este cara
        Float valorTotal = 0F;

        pedidoMB.getPedido().setPostoDeAtendimento(postoDeAtendimentoMB.getPostoDeAtendimento());
        pedidoMB.getPedido().setCliente(this.clienteMB.getCliente());
        pedidoMB.getPedido().getCliente().setPostoDeAtendimento(postoDeAtendimentoMB.getPostoDeAtendimento());
        pedidoMB.getPedido().setFornecedor(fornecedorMB.getFornecedor());


        for (PedidoItem pedidoItem : pedidoMB.getPedido().getItensPedido()) {
            valorTotal += pedidoItem.getValorTotal();
            if (pedidoItem.getPeca().getServico().getValor() > pedidoMB.getPedido().getGarantiaSevico().getValor()) {
                //pedidoMB.getPedido().getGarantiaSevico().setValor(pedidoItem.getPeca().getServico().getValor());
                pedidoMB.getPedido().setGarantiaSevico(pedidoItem.getPeca().getServico());
            }
        }
        if (pedidoMB.getPedido().getGarantia()) {
            valorTotal += pedidoMB.getPedido().getGarantiaSevico().getValor();
        } else {
            pedidoMB.getPedido().setGarantiaSevico(servicoService.BuscarServicoPorID(1L));
        }

        pedidoMB.getPedido().setValorTotal(valorTotal);
        return "ConfirmacaoPedido";
    }

}
