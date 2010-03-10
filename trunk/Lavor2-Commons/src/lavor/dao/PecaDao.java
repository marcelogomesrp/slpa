/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao;

import java.util.List;
import lavor.entidade.Equipamento;
import lavor.entidade.Peca;

/**
 *
 * @author marcelo
 */
public interface PecaDao extends DaoGenerico<Peca, Long>{

    public List<Peca> PesquisarPorPosicaoEquipamento(int posicao, Equipamento equipamento);

    public List<Peca> PesquisarPorEquipamento(Equipamento equipamento);

}
