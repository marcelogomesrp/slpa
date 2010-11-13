/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.facade;

import br.com.guaraba.modelo.ItemPedidoPeca;
import br.com.guaraba.modelo.PedidoPeca;
import br.com.guaraba.modelo.PostoAtendimento;
import br.com.guaraba.modelo.Situacao;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcelo
 */
@Stateless
public class PedidoPecaFacade extends AbstractFacade<PedidoPeca>{
@PersistenceContext(unitName = "LavorPU")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public PedidoPecaFacade() {
        super(PedidoPeca.class);
    }

    public int countPedidoCadastrado(){
        String SQL = "SELECT p from PedidoPeca p  WHERE situacao = :situacao";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("situacao", Situacao.Aberto);
        List<PedidoPeca> listaPedidoPeca = findByParam(SQL, params);
        return listaPedidoPeca.size();
    }

    public List<PedidoPeca> FindBySituacao(Situacao situacao){
        String SQL = "SELECT p from PedidoPeca p  WHERE situacao = :situacao";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("situacao", situacao);
        List<PedidoPeca> listaPedidoPeca = findByParam(SQL, params);
        if(listaPedidoPeca.size() > 0 ){
            return listaPedidoPeca;
        }else{
            return new ArrayList<PedidoPeca>();
        }
    }

    public List<PedidoPeca> FindBySituacao(Situacao situacao, int[] range){
        String SQL = "SELECT p from PedidoPeca p  WHERE situacao = :situacao";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("situacao", situacao);
        List<PedidoPeca> listaPedidoPeca = findByParamRange(SQL, params,range);
        if(listaPedidoPeca.size() > 0 ){
            return listaPedidoPeca;
        }else{
            return new ArrayList<PedidoPeca>();
        }
    }


    public List<PedidoPeca> FindByPosto(PostoAtendimento postoAtendimento, int[] range){
        String SQL = "SELECT p from PedidoPeca p  WHERE postoAtendimento = :postoAtendimento";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("postoAtendimento", postoAtendimento);
        List<PedidoPeca> listaPedidoPeca = findByParamRange(SQL, params,range);
        if(listaPedidoPeca.size() > 0 ){
            return listaPedidoPeca;
        }else{
            return new ArrayList<PedidoPeca>();
        }

    }

    @Override
    public void create(PedidoPeca pedidoPeca) {
        pedidoPeca.setDataPedido(new Date());
        pedidoPeca.setModificado(Boolean.FALSE);
        super.create(pedidoPeca);
    }



    


}


//    public List<T> findRange(int[] range) {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        q.setMaxResults(range[1] - range[0]);
//        q.setFirstResult(range[0]);
//        return q.getResultList();
//    }


