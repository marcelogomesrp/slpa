/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.util.List;
import lavor.dao.RevendaDao;
import lavor.entidade.PostoDeAtendimento;
import lavor.entidade.Revenda;
import lavor.utils.GenericExceptionMessageType;

/**
 *
 * @author marcelo
 */
public class RevendaService {

    private RevendaDao revendaDao;

    private ServiceException serviceException;

    public RevendaService() {
        this.serviceException = new ServiceException();
    }

    public RevendaDao getRevendaDao() {
        return revendaDao;
    }

    public void setRevendaDao(RevendaDao revendaDao) {
        this.revendaDao = revendaDao;
    }

    public Revenda Salvar(Revenda revenda) throws ServiceException{
        this.serviceException = new ServiceException();
        try{
            if(this.PodeSerSalva(revenda)){
                //revenda = this.revendaDao.atualizar(revenda);
                revenda = this.revendaDao.salvar(revenda);
            }
        }catch(Exception ex){
            throw new ServiceException("Ocorreu um erro ao tentar salvar", ex);
        }
        return revenda;
    }

    private boolean PodeSerSalva(Revenda revenda) throws ServiceException {
        this.RazaoSocialValida(revenda.getRazaoSocial());
        if (serviceException.hasMessages()) {
            throw serviceException;
        }
        return Boolean.TRUE;
    }

    private void RazaoSocialValida(String razaoSocial) {
        if(lavor.utils.StringUtils.isNullOrEmpty(razaoSocial)){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "A razao social deve ser informada");
        }
    }

    public List<Revenda> PesquisarPorPosto(PostoDeAtendimento postoDeAtendimento) {
        return this.revendaDao.PesquisarPorPosto(postoDeAtendimento);
    }

    public List<Revenda> PesquisarPorCnpjEPosto(String cnpj, Long id) {
        return this.revendaDao.PesquisarPorCnpjEPosto(cnpj, id);
    }

    public Revenda Atualizar(Revenda revenda){
        return revendaDao.atualizar(revenda);
    }

}
