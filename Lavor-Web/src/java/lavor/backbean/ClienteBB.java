/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import javax.swing.JOptionPane;
import lavor.entidade.Cliente;
import lavor.managedbean.ClienteMB;
import lavor.managedbean.PedidoMB;
import lavor.service.ClienteService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



/**
 *
 * @author Marcelo
 */

@Controller("clienteBB")
@Scope("request")
public class ClienteBB {
    @Resource
    private ClienteService clienteService;
    @Resource
    private ClienteMB clienteMB;
    @Resource
    private PedidoMB pedidoMB;
    private Boolean novoCliente;

    public Boolean getNovoCliente() {

        if (clienteMB.getCliente().getId() == null) {
            novoCliente = Boolean.TRUE;
        } else {
            novoCliente = Boolean.FALSE;
        }

        return novoCliente;
    }

    public void setNovoCliente(Boolean novoCliente) {
        this.novoCliente = novoCliente;
    }

    


    private String nomeAchar;

    public ClienteBB() {
    }

    public String SelecionarCliente(){
        this.clienteMB.setCliente((Cliente) clienteMB.getClientesModelo().getRowData());
        this.pedidoMB.getPedido().setCliente(clienteMB.getCliente());
        return "ListaDeCategorias";
        //return "sucesso";
    }


    public String LocalizarClientePorNome(){
        lavor.util.FacesUtils.mensInfo("nome a acahar " + this.clienteMB.getCliente().getNome());
        this.clienteMB.setClientes(clienteService.BuscarClientePorNome(this.clienteMB.getCliente().getNome()));
        //this.clientes = new ListDataModel(this.clienteMB.getClientes());
        clienteMB.setClientesModelo(new ListDataModel(this.clienteMB.getClientes()));
        return "sucesso2";
    }

    public String DoLocalizarClientePage(){
        if(!this.clienteMB.getCliente().getNome().isEmpty()) {
            this.LocalizarClientePorNome();
        }
        return "LocalizarCliente";
    }

    public String getNomeAchar() {

        return nomeAchar;
    }

    public void setNomeAchar(String nomeAchar) {
        this.nomeAchar = nomeAchar;
    }

    public String NovoCliente(){
        this.clienteMB.setCliente(new Cliente());
        return "sucesso";
        
    }

}
