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
import lavor.entidade.Cliente;
import lavor.entidade.EquipamentoCliente;
import lavor.entidade.Estado;
import lavor.managedBean.CidadeMB;
import lavor.managedBean.ClienteMB;
import lavor.managedBean.EquipamentoClienteMB;
import lavor.managedBean.PedidoMB;
import lavor.managedBean.PostoDeAtendimentoMB;
import lavor.service.CidadeService;
import lavor.service.ClienteService;
import lavor.service.ServiceException;
import lavor.utils.FacesUtils;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("clienteBB")
@Scope("request")
public class ClienteBB {
    @Resource
    private ClienteMB clienteMB;
    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;
    @Resource
    private CidadeMB cidadeMB;

    @Resource
    private PedidoMB pedidoMB;

    @Resource
    private CidadeService cidadeService;
    @Resource
    private ClienteService clienteService;



    @Resource
    private EquipamentoClienteMB equipamentoClienteMB;
    @Resource
    private RevendaBB revendaBB;
    @Resource
    private EquipamentoBB equipamentoBB;


    public ClienteBB() {
    }

    public String DoNovoClientePage(){
        this.clienteMB.setCliente(new Cliente());
        this.clienteMB.setClientes(new ListDataModel());
        this.cidadeMB.LimparListaDeCidades();
        return "sucesso";
    }
    public String DoLocalizarClientePage(){
        this.clienteMB.setCliente(new Cliente());
        this.clienteMB.setClientes(new ListDataModel());
        return "/cliente/listar";
    }

    public String Salvar(){
        try {
            Cliente cliente = clienteMB.getCliente();
            cliente.setPostoDeAtendimento(this.postoDeAtendimentoMB.getPostoDeAtendimento());
            cliente.setCidade(cidadeService.PesquisarPorCidadeEstado(cliente.getCidade().getCidade(), cliente.getCidade().getEstado()));
            this.clienteService.Salvar(cliente);
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Cliente gravado com sucesso" );
            this.DoNovoClientePage();
        } catch (ServiceException ex) {
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar..");
        }
        return "sucesso";
    }

    public String Pesquisar(){
        //List<Cliente> clientes = this.clienteService.PesquisarPorNome(this.clienteMB.getCliente().getNome());
        this.pedidoMB.setClienteSelecionado(Boolean.FALSE);
        String nome = this.clienteMB.getCliente().getNome();
        Long   id   = this.postoDeAtendimentoMB.getPostoDeAtendimento().getId();
        List<Cliente> clientes = this.clienteService.PesquisarPorNomePostoDeAtendimento(nome, id);
        this.clienteMB.setClientes( new ListDataModel(clientes));
        return "cliente/listar";
    }

    public String SalvarEManter() {
        try {
            Cliente cliente = clienteMB.getCliente();
            cliente.setPostoDeAtendimento(this.postoDeAtendimentoMB.getPostoDeAtendimento());
            cliente.setCidade(cidadeService.PesquisarPorCidadeEstado(cliente.getCidade().getCidade(), cliente.getCidade().getEstado()));
            cliente = this.clienteService.Salvar(cliente);
            this.pedidoMB.setClienteSelecionado(Boolean.TRUE);
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Cliente gravado com sucesso");
        } catch (ServiceException ex) {
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar..");
        }
        return "sucesso";
    }

    public String PedidoSalvar(){

        try {
            Cliente cliente = clienteMB.getCliente();
            cliente.setPostoDeAtendimento(this.postoDeAtendimentoMB.getPostoDeAtendimento());
            cliente.setCidade(cidadeService.PesquisarPorCidadeEstado(cliente.getCidade().getCidade(), cliente.getCidade().getEstado()));
            cliente = this.clienteService.Salvar(cliente);
            this.pedidoMB.setClienteSelecionado(Boolean.TRUE);
           // FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Cliente gravado com sucesso");
        } catch (ServiceException ex) {
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar..");
            return "/pedido/novo";
        }

        if(this.pedidoMB.getClienteSelecionado()){
            this.equipamentoClienteMB.setEquipamentoCliente(new EquipamentoCliente());
            this.equipamentoBB.DoPesquisarPage();
            this.revendaBB.CriarRevendaSelectItem(postoDeAtendimentoMB.getPostoDeAtendimento());
            //return "/equipamento/novo";
            return "/pedido/equipamentonovo";

        }else{
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.ERROR, "O cliente deve ser informado antes de continuar");
            return "/pedido/novo";
        }

    }

    public String DoListarPage(){
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes = clienteService.PesquisarPorPostoDeAtendimento(clienteMB.getCliente().getNome(), postoDeAtendimentoMB.getPostoDeAtendimento());
        this.clienteMB.setClientes(new ListDataModel(clientes));
        return "/pedido/clientenovo";
    }

    public String DoSelecionarCliente(){
        this.pedidoMB.setClienteSelecionado(Boolean.TRUE);
        this.clienteMB.setCliente((Cliente) clienteMB.getClientes().getRowData());
        return "sucesso";
    }

    public List<Cliente> PesquisarClientesPorNomeEPosto(String nome, Long id){
        List<Cliente> clientes = this.clienteService.PesquisarPorNomePostoDeAtendimento(nome, id);
        this.clienteMB.setClientes( new ListDataModel(clientes));
        return clientes;
    }


    public String AtualizarListaDeCidades(){
        cidadeMB.AtualizarListaDeCidades(clienteMB.getCliente().getCidade().getEstado());
        return "sucesso";
    }


    public String DoEditarPage(){
        clienteMB.setCliente((Cliente) clienteMB.getClientes().getRowData());
        this.AtualizarListaDeCidades();
        return "/cliente/editar";
    }

    public String Atualizar(){
        try {
            //cliente.setCidade(cidadeService.PesquisarPorCidadeEstado(cliente.getCidade().getCidade(), cliente.getCidade().getEstado()));
            clienteMB.getCliente().setCidade(cidadeService.PesquisarPorCidadeEstado(clienteMB.getCliente().getCidade().getCidade(), clienteMB.getCliente().getCidade().getEstado()));
            this.clienteService.Atualizar(clienteMB.getCliente());
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Cliente atualizado com sucesso" );
        } catch (ServiceException ex) {
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.ERROR, "Ocorreu um erro ao tentar atualizar");
            Logger.getLogger(ClienteBB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucesso";
    }

}
