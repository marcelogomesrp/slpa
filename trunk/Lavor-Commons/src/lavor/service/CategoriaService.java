/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import lavor.dao.CategoriaDao;
import lavor.entidade.Categoria;

/**
 *
 * @author marcelo
 */
public class CategoriaService {
    public CategoriaDao categoriaDao;

    public CategoriaDao getCategoriaDao() {
        return categoriaDao;
    }

    public void setCategoriaDao(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    public CategoriaService() {
    }

    public Categoria Salvar(Categoria categoria) throws Exception{
        this.PodeSerSalvo(categoria);
        categoria = this.categoriaDao.salvar(categoria);
        return categoria;
    }

    private void PodeSerSalvo(Categoria categoria) throws Exception{
        StringBuilder msg = new StringBuilder();
        if(categoria.getNome().isEmpty()){
            msg.append("O nome da categoria não ser informado");
        }

        if(msg.length() > 0){
        //if(!msg.equals(new StringBuilder())){
            throw new Exception(msg.toString());
        }
    }

}
