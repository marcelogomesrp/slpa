/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.entidade.Cliente;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



/**
 *
 * @author marcelo
 */

@Controller("clienteMB")
@Scope("session")
public class ClienteMB implements Serializable{
    private Cliente cliente;
    private ListDataModel clientes;
    @Resource
    private CidadeMB cidadeMB;

    public ClienteMB() {
        this.cliente    = new Cliente();
        this.clientes   = new ListDataModel();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ListDataModel getClientes() {
        return clientes;
    }

    public void setClientes(ListDataModel clientes) {
        this.clientes = clientes;
    }



}
