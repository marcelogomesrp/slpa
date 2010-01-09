/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedbean;

import java.io.Serializable;
import lavor.entidade.ListaDePreco;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 *
 * @author marcelo
 */

@Controller("listaDePrecoMB")
@Scope("session")
public class ListaDePrecoMB implements Serializable{
    ListaDePreco listaDePreco;

    public ListaDePreco getListaDePreco() {
        return listaDePreco;
    }

    public void setListaDePreco(ListaDePreco listaDePreco) {
        this.listaDePreco = listaDePreco;
    }

    public ListaDePrecoMB() {
        this.listaDePreco = new ListaDePreco();
    }
    



}
