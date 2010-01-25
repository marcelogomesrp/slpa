/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
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

    private ListDataModel clientes;


    private String nomeAchar;

    public ClienteBB() {
        this.clientes = new ListDataModel();
    }

    public String SelecionarCliente(){
        this.clienteMB.setCliente((Cliente) this.clientes.getRowData());
        this.pedidoMB.getPedido().setCliente(this.clienteMB.getCliente());
        //return "ListaDeCategorias";
        return "sucesso";
    }


    public String LocalizarClientePorNome(){
        lavor.util.FacesUtils.mensInfo("nome a acahar " + nomeAchar);
        this.clienteMB.setClientes(clienteService.BuscarClientePorNome(nomeAchar));
        this.clientes = new ListDataModel(this.clienteMB.getClientes());
        return "sucesso2";
    }

    public String DoLocalizarClientePage(){
        return "LocalizarCliente";
    }

    public String getNomeAchar() {
        return nomeAchar;
    }

    public void setNomeAchar(String nomeAchar) {
        this.nomeAchar = nomeAchar;
    }

    public ListDataModel getClientes() {
        return clientes;
    }

    public void setClientes(ListDataModel clientes) {
        this.clientes = clientes;
    }

}
