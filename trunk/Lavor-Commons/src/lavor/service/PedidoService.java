/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import lavor.dao.PedidoDao;
import lavor.entidade.Pedido;
import lavor.entidade.PedidoItem;

/**
 *
 * @author marcelo
 */
public class PedidoService {
    private PedidoDao pedidoDao;

    public PedidoService() {
    }

    public Pedido SalvarPedido(Pedido pedido){
        //pedido = pedidoDao.salvar(pedido);
        for(PedidoItem pedidoItem:pedido.getItensPedido()){
            pedidoItem.setIdPedido(pedido);
        }
        Pedido ped = pedidoDao.atualizar(pedido);
        return ped;
    }

    public PedidoDao getPedidoDao() {
        return pedidoDao;
    }

    public void setPedidoDao(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    


}
