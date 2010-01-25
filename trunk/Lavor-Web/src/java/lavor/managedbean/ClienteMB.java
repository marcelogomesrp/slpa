/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedbean;


import java.util.ArrayList;
import java.util.List;
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

    public ClienteMB() {
        cliente = new Cliente();
        this.clientes = new ArrayList<Cliente>();
    }



}
