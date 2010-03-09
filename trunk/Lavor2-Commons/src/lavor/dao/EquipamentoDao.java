/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao;

import java.util.List;
import lavor.entidade.Equipamento;

/**
 *
 * @author marcelo
 */
public interface EquipamentoDao extends DaoGenerico<Equipamento, Long> {

    public List<Equipamento> PesquisarPorNome(String nome);

    public List<Equipamento> PesquisarPorLinha(Long id);

}
