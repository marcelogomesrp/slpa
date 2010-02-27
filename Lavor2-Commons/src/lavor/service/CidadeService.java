/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.io.Serializable;
import java.util.List;
import lavor.dao.CidadeDao;
import lavor.entidade.Cidade;
import lavor.entidade.Estado;

/**
 *
 * @author marcelo
 */
public class CidadeService implements Serializable{
    private CidadeDao cidadeDao;

    public CidadeService() {
    }

    public CidadeDao getCidadeDao() {
        return cidadeDao;
    }

    public void setCidadeDao(CidadeDao cidadeDao) {
        this.cidadeDao = cidadeDao;
    }

    public List<Cidade> PesquisarPorEstado(Estado estado){
        return this.cidadeDao.PesquisarPorEstado(estado);
    }

    public Cidade PesquisarPorCidadeEstado(String Cidade, Estado estado){
        Cidade cidade = this.cidadeDao.PesquisarPorCidadeEstado(Cidade, estado);
        return cidade;
    }

}
