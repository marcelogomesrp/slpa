/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import lavor.entidade.Cidade;
import lavor.entidade.Cliente;
import lavor.entidade.Defeito;
import lavor.entidade.Equipamento;
import lavor.entidade.ItemPedido;
import lavor.entidade.Peca;
import lavor.entidade.Pedido;
import lavor.entidade.PostoDeAtendimento;
import lavor.entidade.Revenda;
import lavor.entidade.Situacao;
import lavor.managedBean.CidadeMB;
import lavor.managedBean.ClienteMB;
import lavor.managedBean.DefeitoMB;
import lavor.managedBean.EquipamentoMB;
import lavor.managedBean.Pedido2MB;
import lavor.managedBean.PostoDeAtendimentoMB;
import lavor.service.CidadeService;
import lavor.service.ClienteService;
import lavor.service.DefeitoService;
import lavor.service.EquipamentoClienteService;
import lavor.service.ItemPedidoService;
import lavor.service.PecaService;
import lavor.service.PedidoService;
import lavor.service.PostoDeAtendimentoService;
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

    @Resource
    private ClienteMB clienteMB;

    @Resource
    private PostoDeAtendimentoService postoDeAtendimentoService;

    @Resource
    private CidadeService cidadeService;

    @Resource
    private DefeitoMB defeitoMB;

    @Resource
    private DefeitoService defeitoService;

    private Date inicio;
    private Date fim;

    

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

    public String DoListarProcessadoPage(){
        this.pedido2MB.setPedidos(this.ListarPedido(Situacao.Processado));
        return "sucesso";
    }

    public String DoListarRejeitadoPage(){
        this.pedido2MB.setPedidos(this.ListarPedido(Situacao.Recusado));
        return "sucesso";
    }



    public String DoListarPagePrioridade(){
        pedido2MB.setPedidos(pedidoService.PesquisarPedidoPorSituacaoEPrioridade(Situacao.Cadastrado, Boolean.TRUE));
        return "sucesso";
    }

    public String DoListarProcessadoPagePrioridade(){
        pedido2MB.setPedidos(pedidoService.PesquisarPedidoPorSituacaoEPrioridade(Situacao.Processado, Boolean.TRUE));
        return "sucesso";
    }

    public String DoListarRejeitadoPagePrioridade(){
        pedido2MB.setPedidos(pedidoService.PesquisarPedidoPorSituacaoEPrioridade(Situacao.Recusado, Boolean.TRUE));
        return "sucesso";
    }



    public String DoDetalhes(){
        this.pedido2MB.setPedido((Pedido) pedido2MB.getPedidos().getRowData());
        this.cidadeMB.AtualizarListaDeCidades(pedido2MB.getPedido().getCliente().getCidade().getEstado());
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

//    public String Atualizar(){
//        //List<ItemPedido> itens = pedido2MB.getPedido().getItemPedido();
//        List<ItemPedido> itens = new ArrayList<ItemPedido>();
//
//        for(int x = 0; x < pedido2MB.getItensPedido().getRowCount(); x++){
//            pedido2MB.getItensPedido().setRowIndex(x);
//            ItemPedido itemPedido = (ItemPedido) pedido2MB.getItensPedido().getRowData();
//            if(itemPedido.getQuantidade() > 0){
//                itens.add(itemPedido);
//            }
//        }
//        pedido2MB.getPedido().setItemPedido(itens);
//        try{
//            pedidoService.Atualizar(pedido2MB.getPedido());
//            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Pedido salvo com sucesso" );
//        }catch(Exception ex){
//            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar atualizar.." + ex.getMessage() + "<p /> " + ex.getCause());
//        }
//        return "sucesso";
//    }

    public String DoNovoPedidoPecaPage(){
        linhaBB.TodasAsLinhas();
        this.pedido2MB.setPedido(new Pedido());
        this.pedido2MB.setItensPedidoSelecionado( new ListDataModel());
        return "/pedidopeca/novo";
    }

    public String DoNovoPedidoPage(){
        this.pedido2MB.setPedido(new Pedido());
        pedido2MB.setItensPedido(new ListDataModel());
        equipamentoMB.setEquipamentos(new ListDataModel());
        linhaBB.TodasAsLinhas();
        defeitoMB.AtualizarSelectDefeito();
        return "/pedido/novo";
    }

    public String DoListarPostoEPeridoPage(){
        this.postoDeAtendimentoMB.setPostoDeAtendimento(new PostoDeAtendimento());
        this.inicio = new Date();
        this.fim = new Date();
        this.postoDeAtendimentoMB.setPostosDeAtendimento(new ListDataModel());
        this.cidadeMB.setCidades(new ArrayList<SelectItem>());
        return "/pedido2/postoperiodo";
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
            for(String id:pedido2MB.getDefeitos()){
                Defeito defeito = defeitoService.PesquisarPorId(Long.valueOf(id));
                pedido2MB.getPedido().getDefeitos().add(defeito);
            }
//            for(Defeito df:pedido2MB.getPedido().getDefeitos()){
//                System.out.println(df.getId() + df.getNome() + df.getDescricao());
//            }
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

    public String DoListarPedidoRejeitadoPage(){
        List<Pedido> pedidos = pedidoService.PesquisarPedidoPorPostoESituacao(postoDeAtendimentoMB.getPostoDeAtendimento(), Situacao.Recusado);
        pedido2MB.setPedidos(new ListDataModel(pedidos));
        return "/pedido2/listar";
    }

    public String Cancelar(){
        pedido2MB.getPedido().setSituacao(Situacao.Cancelado);
            this.Atualizar();            
            pedido2MB.setPedido(new Pedido());
            pedido2MB.setItensPedido(new ListDataModel());
            equipamentoMB.setEquipamentos(new ListDataModel());
            linhaBB.TodasAsLinhas();
        return "sucesso";
    }

    public String CancelarPedido(){
        pedido2MB.setPedido((Pedido) pedido2MB.getPedidos().getRowData());
        pedido2MB.getPedido().setSituacao(Situacao.Cancelado);
            this.Atualizar();
            pedido2MB.setPedido(new Pedido());
            pedido2MB.setItensPedido(new ListDataModel());
            equipamentoMB.setEquipamentos(new ListDataModel());
            linhaBB.TodasAsLinhas();
        return "sucesso";
    }


    public String RejeitarPedido(){
        pedido2MB.setPedido((Pedido) pedido2MB.getPedidos().getRowData());
        pedido2MB.getPedido().setSituacao(Situacao.Recusado);
            this.Atualizar();
            pedido2MB.setPedido(new Pedido());
            pedido2MB.setItensPedido(new ListDataModel());
            equipamentoMB.setEquipamentos(new ListDataModel());
            linhaBB.TodasAsLinhas();
        return "sucesso";
    }

    public String ProcessarPedido(){
        pedido2MB.setPedido((Pedido) pedido2MB.getPedidos().getRowData());
        pedido2MB.getPedido().setSituacao(Situacao.Processado);
            this.Atualizar();
            pedido2MB.setPedido(new Pedido());
            pedido2MB.setItensPedido(new ListDataModel());
            equipamentoMB.setEquipamentos(new ListDataModel());
            linhaBB.TodasAsLinhas();
        return "sucesso";
    }

    public String FinalizarPedido(){
        pedido2MB.setPedido((Pedido) pedido2MB.getPedidos().getRowData());
        pedido2MB.getPedido().setSituacao(Situacao.Finalizado);
            this.Atualizar();
            pedido2MB.setPedido(new Pedido());
            pedido2MB.setItensPedido(new ListDataModel());
            equipamentoMB.setEquipamentos(new ListDataModel());
            linhaBB.TodasAsLinhas();
        return "sucesso";
    }

    public String DoEditarPage() {
        Pedido pedi = (Pedido) pedido2MB.getPedidos().getRowData();
        if (pedi.getCliente() != null) {
            pedido2MB.setPedido((Pedido) pedido2MB.getPedidos().getRowData());
            cidadeMB.AtualizarListaDeCidades(pedido2MB.getPedido().getCliente().getCidade().getEstado());
            defeitoMB.AtualizarSelectDefeito();
            pedido2MB.setDefeitos(new ArrayList<String>());
            for (Defeito defeito : pedido2MB.getPedido().getDefeitos()) {
                pedido2MB.getDefeitos().add(defeito.getId().toString());
            }
            return "/pedido2/editar";
        } else {
            pedido2MB.setPedidoPeca(pedi);
            //pedido2MB.itensPedido
            pedido2MB.setItensPedido(itemPedidoService.PesquisarPorPedido(pedi));
            //ItemPedido);
            //pedido2MB.setPedido(pedi);
            //pedido2MB.setPedido(pedi);
            return "/pedidopeca/editar";
        }
    }

    public String DoAdminEditarPage(){
        pedido2MB.setPedido((Pedido) pedido2MB.getPedidos().getRowData());
        cidadeMB.AtualizarListaDeCidades(pedido2MB.getPedido().getCliente().getCidade().getEstado());
        return "/pedido2/editar";
    }

    public String NovoCliente(){
        pedido2MB.getPedido().setCliente(new Cliente());
        cidadeMB.setCidades(new ArrayList<SelectItem>());
        return "sucesso";
    }

    public String NovaRevenda(){
        pedido2MB.getPedido().setRevenda(new Revenda());
        return "sucesso";
    }


    public String Atualizar(){
        //marcelo
        List<ItemPedido> itens = new ArrayList<ItemPedido>();
        for(int x = 0; x<pedido2MB.getItensPedido().getRowCount();x++){
            pedido2MB.getItensPedido().setRowIndex(x);
            ItemPedido item = (ItemPedido) pedido2MB.getItensPedido().getRowData();
            //if(item.getQuantidade() > 0 ){
                itens.add(item);
            //}
        }
        pedido2MB.getPedido().setDefeitos(new ArrayList<Defeito>());
        for(String id:pedido2MB.getDefeitos()){
            Defeito defeito = defeitoService.PesquisarPorId(Long.valueOf(id));
            pedido2MB.getPedido().getDefeitos().add(defeito);
        }

        try {
            pedido2MB.getPedido().setItemPedido(itens);
            pedidoService.Atualizar(pedido2MB.getPedido());
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Pedido atualizado com sucesso" );
        } catch (ServiceException ex) {
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar" + ex.getCause() + "<br />" + ex.getMessage());
            Logger.getLogger(Pedido2BB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucesso";
    }


    public String DoPesquisarPorPeriodoPage(){
        this.inicio = new Date();
        this.fim    = new Date();
        return "/pedido2/listarPorPeriodo";
    }

    public String PesquisarPorPeriodo(){
        List<Pedido> pedidos = pedidoService.PesquisarPedidoPorPostoEPeriodo(postoDeAtendimentoMB.getPostoDeAtendimento(), inicio, fim);
        pedido2MB.setPedidos(new ListDataModel(pedidos));
        return "/pedido2/listarPorPeriodo";
    }

    public String PesquisarAdmPorPeriodo(){
        List<Pedido> pedidos = pedidoService.PesquisarPedidoPorPeriodo(inicio, fim);
        pedido2MB.setPedidos(new ListDataModel(pedidos));
        return "/pedido2/listarPorPeriodo";
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public String DoDetalhesPage(){
        pedido2MB.setPedido((Pedido) pedido2MB.getPedidos().getRowData());
        return "/pedido2/detalhes";
    }

    public String DoPesquisarPorClientePage(){        
        clienteMB.setCliente(new Cliente());
        clienteMB.setClientes(new ListDataModel());
        return "/pedido2/listarPorCliente";
    }

    public String SelecionarCliente(){
        Cliente cliente = (Cliente) clienteMB.getClientes().getRowData();
        List<Pedido> pedidos = pedidoService.PesquisarPedidoPorCliente(cliente);
        pedido2MB.setPedidos(new ListDataModel(pedidos));
        clienteMB.setClientes(new ListDataModel());
        return "sucesso";
    }

    public String PesquisarCliente(){
        List<Cliente> clientes = clienteService.PesquisarPorNome(clienteMB.getCliente().getNome());
        clienteMB.setClientes(new ListDataModel(clientes));
        pedido2MB.setPedidos(new ListDataModel());
        return "sucesso";
    }

    public String PesquisarPostoPorCidade(){
        Cidade cidade = cidadeService.PesquisarPorCidadeEstado(postoDeAtendimentoMB.getPostoDeAtendimento().getCidade().getCidade(), postoDeAtendimentoMB.getPostoDeAtendimento().getCidade().getEstado());
        List<PostoDeAtendimento> postos = postoDeAtendimentoService.pesquisarPorCidade(cidade.getId());
        this.postoDeAtendimentoMB.setPostosDeAtendimento(new ListDataModel(postos));
        return "sucesso";
    }

    public String SelecionarPosto(){
        this.postoDeAtendimentoMB.setPostoDeAtendimento((PostoDeAtendimento) postoDeAtendimentoMB.getPostosDeAtendimento().getRowData());
        return "sucesso";
    }

    public String AdiconarItem() {
        for (int x = 0; x < pedido2MB.getItensPedido().getRowCount(); x++) {
            pedido2MB.getItensPedido().setRowIndex(x);
            ItemPedido itemPedido = (ItemPedido) pedido2MB.getItensPedido().getRowData();
            if (itemPedido.getQuantidade() > 0) {
                pedido2MB.getPedido().getItemPedido().add(itemPedido);
            }

        }
        pedido2MB.setItensPedidoSelecionado(new ListDataModel(pedido2MB.getPedido().getItemPedido()));
        return "sucesso";
    }

    public String Finalizar(){
        try {
            pedido2MB.getPedido().setDataDoPedido(new Date());
            pedido2MB.getPedido().setCliente(null);
            pedido2MB.getPedido().setEquipamentoCliente(null);
            pedido2MB.getPedido().setRevenda(null);
            pedido2MB.getPedido().setSituacao(Situacao.Cadastrado);
            pedido2MB.getPedido().setPostoDeAtendimento(postoDeAtendimentoMB.getPostoDeAtendimento());
            pedidoService.SalvarPedidoPeca(pedido2MB.getPedido());
        } catch (ServiceException ex) {
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao gerar o pedido" + ex.getMessage() + "<p /> " + ex.getCause());
            Logger.getLogger(Pedido2BB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/pedidopeca/finalizar";
    }

}
