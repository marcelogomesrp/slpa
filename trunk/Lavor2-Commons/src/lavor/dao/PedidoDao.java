/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao;

import java.util.List;
import lavor.entidade.Pedido;
import lavor.entidade.PostoDeAtendimento;
import lavor.entidade.Situacao;

/**
 *
 * @author marcelo
 */
public interface PedidoDao  extends DaoGenerico<Pedido, Long>{

    public List<Pedido> pesquisarPorPosto(PostoDeAtendimento postoDeAtendimento);

    public List<Pedido> pesquisarPorPostoESituacao(PostoDeAtendimento postoDeAtendimento, Situacao situacao);

    public List<Pedido> PesquisarPorSituacao(Situacao situacao);

}
