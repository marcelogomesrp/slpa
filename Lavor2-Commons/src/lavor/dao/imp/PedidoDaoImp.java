/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lavor.dao.PedidoDao;
import lavor.entidade.Cliente;
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
    public Pedido salvarPedidoPeca(Pedido pedido) {
        getEntityManager().clear();
        float total = 0F;
        for (ItemPedido itemPedido : pedido.getItemPedido()) {
            itemPedido.setValor(itemPedido.getPeca().getValor());
            total += itemPedido.getValor() * itemPedido.getQuantidade();
        }
        pedido.setValorTotal(total);
        getEntityManager().persist(pedido);

        for (ItemPedido itemPedido : pedido.getItemPedido()) {
            itemPedido.setValor(itemPedido.getPeca().getValor() * itemPedido.getQuantidade());
            itemPedido.setPedido(pedido);
            getEntityManager().persist(itemPedido);
        }

        return pedido;
    }

    	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Pedido salvar(Pedido pedido) {
		getEntityManager().clear();
                float total = 0F;
                //TODO: modificar este valor
                for(ItemPedido itemPedido:pedido.getItemPedido()){
                    itemPedido.setValor(itemPedido.getPeca().getValor());
                    //getEntityManager().persist(itemPedido);
                    //total+=itemPedido.getValor() * itemPedido.getPeca().getQuantidadeMaxima();
                    total+=itemPedido.getValor() * itemPedido.getQuantidade();
                }
                pedido.setValorTotal(total);
		getEntityManager().persist(pedido);

                for(ItemPedido itemPedido:pedido.getItemPedido()){
                    itemPedido.setValor(itemPedido.getPeca().getValor() * itemPedido.getQuantidade());
                    itemPedido.setPedido(pedido);
                    getEntityManager().persist(itemPedido);
                }

		return pedido;
	}

    	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Pedido atualizar(Pedido pedido) {

		getEntityManager().clear();
                float total = 0F;


  //              List<ItemPedido> itens = new ArrayList<ItemPedido>();
    //            List<ItemPedido> remover = new ArrayList<ItemPedido>();

                for(ItemPedido itemPedido:pedido.getItemPedido()){
                    itemPedido.setValor(itemPedido.getPeca().getValor() * itemPedido.getQuantidade());
                    if(itemPedido.getId() == null){
                        if(itemPedido.getValor() > 0){
                            itemPedido.setPedido(pedido);
                            getEntityManager().persist(itemPedido);
      //                      itens.add(itemPedido);
                        }
                    }else{
                            getEntityManager().merge(itemPedido);
                    }
                }

//                pedido.setItemPedido(itens);


                for(ItemPedido itemPedido:pedido.getItemPedido()){
                    itemPedido.setValor(itemPedido.getPeca().getValor());
                    total+=itemPedido.getValor() * itemPedido.getQuantidade();
                }
                pedido.setValorTotal(total);
		getEntityManager().merge(pedido);

//                for(ItemPedido item:remover){
//                    getEntityManager().remove(item);
//                }




		return pedido;


//		getEntityManager().clear();
//                float total = 0F;
//                //TODO: modificar este valor
//                for(ItemPedido itemPedido:pedido.getItemPedido()){
//                    itemPedido.setValor(itemPedido.getPeca().getValor());
//                    //getEntityManager().persist(itemPedido);
//                    total+=itemPedido.getValor() * itemPedido.getPeca().getQuantidadeMaxima();
//                }
//                pedido.setValorTotal(total);
//		//getEntityManager().persist(pedido);
//                getEntityManager().merge(pedido);
//
//                for(ItemPedido itemPedido:pedido.getItemPedido()){
//                    itemPedido.setValor(itemPedido.getPeca().getValor() * itemPedido.getPeca().getQuantidadeMaxima());
//                    itemPedido.setPedido(pedido);
//                    if(itemPedido.getId() == null){
//                        getEntityManager().persist(itemPedido);
//                    }else{
//                        getEntityManager().merge(itemPedido);
//                    }
//                }
//
//		return pedido;
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

    public List<Pedido> PesquisarPorSituacao(Situacao situacao, Boolean prioridade) {
        String SQL = "SELECT p from Pedido p WHERE situacao  = :situacao and prioridade = :prioridade";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("situacao", situacao);
        params.put("prioridade", prioridade);
        List<Pedido> pedidos = listPesqParam(SQL, params);
        return pedidos;
    }

    public List<Pedido> PesquisarPedidoPorPostoEPeriodo(PostoDeAtendimento postoDeAtendimento, Date inicio, Date fim) {
        String SQL = "SELECT p from Pedido p WHERE postoDeAtendimento = :postoDeAtendimento and dataDoPedido >= :inicio and dataDoPedido <= :fim";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("postoDeAtendimento", postoDeAtendimento);
        params.put("inicio", inicio);
        params.put("fim", fim);
        List<Pedido> pedidos = listPesqParam(SQL, params);
        return pedidos;
    }

    public List<Pedido> PesquisarPedidoPorCliente(Cliente cliente) {
        String SQL = "SELECT p from Pedido p WHERE cliente = :cliente";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cliente", cliente);
        List<Pedido> pedidos = listPesqParam(SQL, params);
        return pedidos;
    }

    public List<Pedido> PesquisarPedidoPorPeriodo(Date inicio, Date fim) {
        String SQL = "SELECT p from Pedido p WHERE dataDoPedido >= :inicio and dataDoPedido <= :fim";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("inicio", inicio);
        params.put("fim", fim);
        List<Pedido> pedidos = listPesqParam(SQL, params);
        return pedidos;
    }


}
