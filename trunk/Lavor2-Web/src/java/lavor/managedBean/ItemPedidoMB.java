/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import java.io.Serializable;
import javax.faces.model.ListDataModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("itemPedidoMB")
@Scope("session")
public class ItemPedidoMB implements Serializable{

    private ItemPedidoMB itemPedido;
    private ListDataModel itensPedido;

    public ItemPedidoMB() {
        this.itemPedido  = new ItemPedidoMB();
        this.itensPedido = new ListDataModel();
    }

    public ItemPedidoMB getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedidoMB itemPedido) {
        this.itemPedido = itemPedido;
    }

    public ListDataModel getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(ListDataModel itensPedido) {
        this.itensPedido = itensPedido;
    }
    



}
