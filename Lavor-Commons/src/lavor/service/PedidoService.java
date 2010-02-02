/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lavor.dao.PedidoDao;
import lavor.entidade.Pedido;
import lavor.entidade.PedidoItem;
import lavor.entidade.Status;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Pedido> LocalizarTodosOsPedidos(){
        return pedidoDao.todos();
    }

    
    public List<Pedido> LocalizarPorPostoDeAtendimento(Long id){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id",id);
        String sql = "SELECT p FROM Pedido p WHERE p.postoDeAtendimento.id = :id";
        List<Pedido> pedidos = pedidoDao.listPesqParam(sql, params);
        return pedidos;
    }

    public List<Pedido> LocalizarPorPostoDeAtendimentoEStatus(Long id, Status status){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id",id);
        params.put("status", status);
        String sql = "SELECT p FROM Pedido p " +
                "WHERE p.postoDeAtendimento.id = :id " +
                "  AND p.status = :status" ;
        List<Pedido> pedidos = pedidoDao.listPesqParam(sql, params);
        return pedidos;
    }

    


}
