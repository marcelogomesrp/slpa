/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import lavor.dao.FornecedorDao;
import lavor.entidade.Fornecedor;

/**
 *
 * @author Marcelo
 */
public class FornecedorService {
    private FornecedorDao fornecedorDao;

    public FornecedorDao getFornecedorDao() {
        return fornecedorDao;
    }

    public void setFornecedorDao(FornecedorDao fornecedorDao) {
        this.fornecedorDao = fornecedorDao;
    }

    public FornecedorService() {
    }

    public Fornecedor SalvarFornecedor(Fornecedor fornecedor){
        fornecedor = fornecedorDao.atualizar(fornecedor);
        return fornecedor;
    }

}
