/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedbean;

import javax.faces.model.ListDataModel;
import lavor.entidade.Categoria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */
@Controller("categoriaMB")
@Scope("session")
public class CategoriaMB {

    private Categoria categoria;
    private ListDataModel listaDeCategoria;


    public ListDataModel getListaDeCategoria() {
        return listaDeCategoria;
    }

    public void setListaDeCategoria(ListDataModel listaDeCategoria) {
        this.listaDeCategoria = listaDeCategoria;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CategoriaMB() {
        this.categoria = new Categoria();
        this.listaDeCategoria = new ListDataModel();
    }



}
