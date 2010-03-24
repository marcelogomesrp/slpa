/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao.imp;

import lavor.dao.PedidoDao;
import lavor.entidade.ItemPedido;
import lavor.entidade.Pedido;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcelo
 */
public class PedidoDaoImp extends DaoGenericoImp<Pedido, Long> implements PedidoDao {

    	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Pedido salvar(Pedido pedido) {
		getEntityManager().clear();
                float total = 0F;
                //TODO: modificar este valor
                for(ItemPedido itemPedido:pedido.getItemPedido()){
                    itemPedido.setValor(itemPedido.getPeca().getValor());
                    //getEntityManager().persist(itemPedido);
                    total+=itemPedido.getValor() * itemPedido.getPeca().getQuantidadeMaxima();
                }
                pedido.setValorTotal(total);
		getEntityManager().persist(pedido);

                for(ItemPedido itemPedido:pedido.getItemPedido()){
                    itemPedido.setValor(itemPedido.getPeca().getValor() * itemPedido.getPeca().getQuantidadeMaxima());
                    itemPedido.setPedido(pedido);
                    getEntityManager().persist(itemPedido);
                }

		return pedido;
	}


}
