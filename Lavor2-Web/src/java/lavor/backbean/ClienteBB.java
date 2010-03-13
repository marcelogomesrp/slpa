/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.List;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.entidade.Cliente;
import lavor.managedBean.CidadeMB;
import lavor.managedBean.ClienteMB;
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
        String nome = this.clienteMB.getCliente().getNome();
        Long   id   = this.postoDeAtendimentoMB.getPostoDeAtendimento().getId();
        List<Cliente> clientes = this.clienteService.PesquisarPorNomePostoDeAtendimento(nome, id);
        this.clienteMB.setClientes( new ListDataModel(clientes));
        return "cliente/listar";
    }

        public String SalvarEManter(){
        try {
            Cliente cliente = clienteMB.getCliente();
            cliente.setPostoDeAtendimento(this.postoDeAtendimentoMB.getPostoDeAtendimento());
            cliente.setCidade(cidadeService.PesquisarPorCidadeEstado(cliente.getCidade().getCidade(), cliente.getCidade().getEstado()));
            cliente = this.clienteService.Salvar(cliente);
            this.pedidoMB.setClienteSelecionado(Boolean.TRUE);
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Cliente gravado com sucesso" );            
        } catch (ServiceException ex) {
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar..");
        }
        return "sucesso";
    }

}
