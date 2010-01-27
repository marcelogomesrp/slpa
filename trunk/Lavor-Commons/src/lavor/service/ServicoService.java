/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import lavor.dao.ServicoDao;
import lavor.entidade.Servico;

/**
 *
 * @author Marcelo
 */
public class ServicoService {
    private ServicoDao servicoDao;

    public ServicoDao getServicoDao() {
        return servicoDao;
    }

    public void setServicoDao(ServicoDao servicoDao) {
        this.servicoDao = servicoDao;
    }

    public ServicoService() {
    }

    public Servico SalvarServico(Servico servico){
        servico = servicoDao.salvar(servico);
        return servico;
    }

}
