/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.util.List;
import lavor.dao.ListaDePrecoDao;
import lavor.entidade.ListaDePreco;

/**
 *
 * @author marcelo
 */
public class ListaDePrecoService {
    private ListaDePrecoDao listaDePrecoDao;

    public ListaDePrecoDao getListaDePrecoDao() {
        return listaDePrecoDao;
    }

    public void setListaDePrecoDao(ListaDePrecoDao listaDePrecoDao) {
        this.listaDePrecoDao = listaDePrecoDao;
    }

    public ListaDePrecoService() {
    }

    public ListaDePreco SalvarListaDePrecoService(ListaDePreco listaDePreco){
        listaDePreco = listaDePrecoDao.salvar(listaDePreco);
        return listaDePreco;
    }

    public ListaDePreco BuscarListaDePrecoPorId(Long id) {
        return listaDePrecoDao.pesquisarPorId(id);
    }

    public List<ListaDePreco> BuscarTodasListaDePreco(){
        List listaDePreco = null;
        listaDePreco = listaDePrecoDao.todos();
        return listaDePreco;
    }

}
