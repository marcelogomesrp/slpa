/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.controller.util;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author marcelo
 */

public class Tabela<T>  {
    private Class<T> oClass;
    private List<T> lista;
    private DataModel<T> modelo;

    public Tabela(Class<T> entityClass) {
        oClass = entityClass;
        lista = new ArrayList();
        modelo = new ListDataModel(lista);
    }

    public List getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }

    public DataModel<T> getModelo() {
        return modelo;
    }

    public void setModelo(DataModel modelo) {
        this.modelo = modelo;
    }

    public void Sincroniza(){
        modelo = new ListDataModel(lista);
    }

    public void Adicionar(T objeto){
        lista.add(objeto);
        this.Sincroniza();
    }

    public void Remove(T objeto){
        lista.remove(objeto);
        this.Sincroniza();
    }





}
