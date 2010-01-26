/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedbean;


import java.util.ArrayList;
import java.util.List;
import javax.faces.model.ListDataModel;
import lavor.entidade.Cliente;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



/**
 *
 * @author Marcelo
 */

@Controller("clienteMB")
@Scope("session")
public class ClienteMB {

    private Cliente cliente;
    private List<Cliente> clientes;
    private ListDataModel clientesModelo;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ListDataModel getClientesModelo() {
        return clientesModelo;
    }

    public void setClientesModelo(ListDataModel clientesModelo) {
        this.clientesModelo = clientesModelo;
    }
    

    public ClienteMB() {
        cliente = new Cliente();
        this.clientes = new ArrayList<Cliente>();
        this.SincronixarClienteComModelo();
    }

    public String SincronixarClienteComModelo(){
        this.clientesModelo = new ListDataModel(clientes);
        return "sucesso";
    }



}
