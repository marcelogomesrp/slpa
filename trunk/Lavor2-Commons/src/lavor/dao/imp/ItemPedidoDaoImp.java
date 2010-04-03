/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lavor.dao.ItemPedidoDao;
import lavor.entidade.ItemPedido;
import lavor.entidade.Pedido;

/**
 *
 * @author marcelo
 */
public class ItemPedidoDaoImp extends DaoGenericoImp<ItemPedido, Long> implements ItemPedidoDao {

    public List<ItemPedido> pesquisarPorPedido(Pedido pedido) {
        String SQL = "SELECT i from ItemPedido i  WHERE pedido = :pedido";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pedido", pedido);
        List<ItemPedido> itens = this.listPesqParam(SQL, params);
        return itens;
    }

}
