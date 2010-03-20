/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import lavor.dao.ItemPedidoDao;

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

}
