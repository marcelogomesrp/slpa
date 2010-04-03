/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao;

import java.util.List;
import lavor.entidade.ItemPedido;
import lavor.entidade.Pedido;

/**
 *
 * @author marcelo
 */
public interface ItemPedidoDao extends DaoGenerico<ItemPedido, Long> {

    public List<ItemPedido> pesquisarPorPedido(Pedido pedido);

}
