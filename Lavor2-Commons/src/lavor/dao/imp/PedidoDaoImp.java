/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lavor.dao.PedidoDao;
import lavor.entidade.ItemPedido;
import lavor.entidade.Pedido;
import lavor.entidade.PostoDeAtendimento;
import lavor.entidade.Situacao;
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

    public List<Pedido> pesquisarPorPosto(PostoDeAtendimento postoDeAtendimento) {
        String SQL = "SELECT p from Pedido p WHERE postoDeAtendimento = :postoDeAtendimento";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("postoDeAtendimento", postoDeAtendimento);
        List<Pedido> pedidos = listPesqParam(SQL, params);
        return pedidos;
    }

    public List<Pedido> pesquisarPorPostoESituacao(PostoDeAtendimento postoDeAtendimento, Situacao situacao) {
        String SQL = "SELECT p from Pedido p WHERE postoDeAtendimento = :postoDeAtendimento and situacao  = :situacao";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("postoDeAtendimento", postoDeAtendimento);
        params.put("situacao", situacao);
        List<Pedido> pedidos = listPesqParam(SQL, params);
        return pedidos;
    }

    public List<Pedido> PesquisarPorSituacao(Situacao situacao) {
        String SQL = "SELECT p from Pedido p WHERE situacao  = :situacao";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("situacao", situacao);
        List<Pedido> pedidos = listPesqParam(SQL, params);
        return pedidos;
    }


}
