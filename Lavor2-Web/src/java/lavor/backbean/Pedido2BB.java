/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import lavor.entidade.Cliente;
import lavor.entidade.Equipamento;
import lavor.entidade.Estado;
import lavor.entidade.ItemPedido;
import lavor.entidade.Peca;
import lavor.entidade.Pedido;
import lavor.entidade.Revenda;
import lavor.entidade.Situacao;
import lavor.managedBean.CidadeMB;
import lavor.managedBean.EquipamentoMB;
import lavor.managedBean.Pedido2MB;
import lavor.managedBean.PostoDeAtendimentoMB;
import lavor.service.ClienteService;
import lavor.service.EquipamentoClienteService;
import lavor.service.ItemPedidoService;
import lavor.service.PecaService;
import lavor.service.PedidoService;
import lavor.service.RevendaService;
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

    @Resource
    private EquipamentoClienteService equipamentoClienteService;

    @Resource
    private LinhaBB linhaBB;

    @Resource
    private EquipamentoMB equipamentoMB;

    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;

    @Resource
    private PecaService pecaService;

    @Resource
    private ClienteService clienteService;

    @Resource
    private RevendaService revendaService;

    @Resource
    private CidadeMB cidadeMB;

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

    public String DoNovoPedidoPage(){
        this.pedido2MB.setPedido(new Pedido());
        pedido2MB.setItensPedido(new ListDataModel());
        equipamentoMB.setEquipamentos(new ListDataModel());
        linhaBB.TodasAsLinhas();
        return "/pedido/novo";
    }

    public String SelecionarEquipamento(){
        //this.equipamentoMB.setEquipamento((Equipamento) equipamentoMB.getEquipamentos().getRowData());
        Equipamento equipamento = (Equipamento) equipamentoMB.getEquipamentos().getRowData();
        pedido2MB.getPedido().getEquipamentoCliente().setEquipamento(equipamento);
        List<ItemPedido> itensAdd = new ArrayList<ItemPedido>();

        for(Peca peca:pecaService.PesquisarPorEquipamento(equipamento)){
            //item.put(peca.getId(), new ItemPedido(pedido, peca, 0, 0F));
            itensAdd.add(new ItemPedido(this.pedido2MB.getPedido(), peca, 0, 0F));
        }

        pedido2MB.setItensPedido(new ListDataModel(itensAdd));
        return "sucesso";
    }


    public String Salvar(){
        List<ItemPedido> itens = new ArrayList<ItemPedido>();
        for(int x = 0; x<pedido2MB.getItensPedido().getRowCount();x++){
            pedido2MB.getItensPedido().setRowIndex(x);
            ItemPedido item = (ItemPedido) pedido2MB.getItensPedido().getRowData();
            if(item.getQuantidade() > 0 ){
                itens.add(item);
            }
        }
        try {
            pedido2MB.getPedido().getCliente().setPostoDeAtendimento(postoDeAtendimentoMB.getPostoDeAtendimento());
            pedido2MB.getPedido().getRevenda().setPostoDeAtendimento(postoDeAtendimentoMB.getPostoDeAtendimento());
            pedido2MB.getPedido().setPostoDeAtendimento(postoDeAtendimentoMB.getPostoDeAtendimento());
            pedido2MB.getPedido().setSituacao(Situacao.Cadastrado);
            pedido2MB.getPedido().setPrioridade(pedido2MB.getPedido().getEquipamentoCliente().getEquipamento().getPrioridade());
            pedido2MB.getPedido().setItemPedido(itens);
            pedidoService.Salvar(pedido2MB.getPedido());
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Pedido salvo com sucesso" );
            pedido2MB.setPedido(new Pedido());
            pedido2MB.setItensPedido(new ListDataModel());
            equipamentoMB.setEquipamentos(new ListDataModel());
            linhaBB.TodasAsLinhas();

        } catch (ServiceException ex) {
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar" + ex.getCause() + "<br />" + ex.getMessage());
            Logger.getLogger(Pedido2BB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucesso";
    }


    public String PesquisarClientePorTelefone(){        
        List<Cliente> clientes = clienteService.PesquisarPorTelefoneEPostoDeAtendimento(pedido2MB.getPedido().getCliente().getTelefone(), postoDeAtendimentoMB.getPostoDeAtendimento().getId());
        if(clientes.size() == 1){
            pedido2MB.getPedido().setCliente(clientes.get(0));
            cidadeMB.AtualizarListaDeCidades(pedido2MB.getPedido().getCliente().getCidade().getEstado());
            //cidadeMB.setCidades(new ArrayList<SelectItem>());
            //select * from equipamento_cliente where id in (select id_equipamento_cliente from pedido) ;
        }
        return "sucesso";
    }

    public String PesquisarRevendaPorCnpj(){
        List<Revenda> revendas = revendaService.PesquisarPorCnpjEPosto(pedido2MB.getPedido().getRevenda().getCnpj(), postoDeAtendimentoMB.getPostoDeAtendimento().getId());
        if(revendas.size() == 1){
            pedido2MB.getPedido().setRevenda(revendas.get(0));
        }
        return "sucesso";
        //pedido2MB.getItensPedido().getRowCount();
    }


    public String AtualizarListaDeCidades(){
        cidadeMB.AtualizarListaDeCidades(pedido2MB.getPedido().getCliente().getCidade().getEstado());
        return "sucesso";
    }

    public String DoListarPedidoCadastradoPage(){
        List<Pedido> pedidos = pedidoService.PesquisarPedidoPorPostoESituacao(postoDeAtendimentoMB.getPostoDeAtendimento(), Situacao.Cadastrado);
        pedido2MB.setPedidos(new ListDataModel(pedidos));
        return "/pedido2/listar";
    }

    public String DoEditarPage(){
        pedido2MB.setPedido((Pedido) pedido2MB.getPedidos().getRowData());
        return "/pedido2/editar";
    }

    public String NovoCliente(){
        pedido2MB.getPedido().setCliente(new Cliente());
        cidadeMB.setCidades(new ArrayList<SelectItem>());
        return "sucesso";
    }

}
