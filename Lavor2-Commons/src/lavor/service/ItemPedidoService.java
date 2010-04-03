/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.util.List;
import lavor.dao.ItemPedidoDao;
import lavor.entidade.ItemPedido;
import lavor.entidade.Pedido;

/**
 *
 * @author marcelo
 */
public class ItemPedidoService {
    private ItemPedidoDao itemPedidoDao;

    public ItemPedidoService() {
    }

    public ItemPedidoDao getItemPedidoDao() {
        return itemPedidoDao;
    }

    public void setItemPedidoDao(ItemPedidoDao itemPedidoDao) {
        this.itemPedidoDao = itemPedidoDao;
    }

    public List<ItemPedido> PesquisarPorPedido(Pedido pedido){
        return this.itemPedidoDao.pesquisarPorPedido(pedido);
    }

}
