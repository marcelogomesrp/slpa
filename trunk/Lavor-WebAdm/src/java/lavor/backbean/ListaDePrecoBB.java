/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.entidade.ListaDePreco;
import lavor.entidade.PecaValor;
import lavor.managedbean.ListaDePrecoMB;
import lavor.service.ListaDePrecoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */
@Controller("listaDePrecoBB")
@Scope("session")
public class ListaDePrecoBB {
    @Resource
    private ListaDePrecoMB listaDePrecoMB;
    @Resource
    private ListaDePrecoService listaDePrecoService;
    private ListDataModel listasDePreco;

    public ListaDePrecoMB getListaDePrecoMB() {
        return listaDePrecoMB;
    }

    public void setListaDePrecoMB(ListaDePrecoMB listaDePrecoMB) {
        this.listaDePrecoMB = listaDePrecoMB;
    }

    public ListaDePrecoService getListaDePrecoService() {
        return listaDePrecoService;
    }

    public void setListaDePrecoService(ListaDePrecoService listaDePrecoService) {
        this.listaDePrecoService = listaDePrecoService;
    }

    public ListDataModel getListasDePreco() {
        //listasDePreco = new ListDataModel(listaDePrecoService.BuscarTodasListaDePreco());
        listasDePreco = new ListDataModel();
        for(ListaDePreco listaDePreco:listaDePrecoService.BuscarTodasListaDePreco()){
            System.out.println(listaDePreco.getInicio());
        }
        return listasDePreco;
    }

    public void setListasDePreco(ListDataModel listasDePreco) {
        this.listasDePreco = listasDePreco;
    }

    public ListaDePrecoBB() {
        
    }

    public String SalvarListaDePreco(){
        try {
            if(this.listaDePrecoMB.getListaDePreco().getPecas() == null){
                this.listaDePrecoMB.getListaDePreco().setPecas(new ArrayList<PecaValor>());
            }
            listaDePrecoService.SalvarListaDePrecoService(this.listaDePrecoMB.getListaDePreco());
            lavor.util.FacesUtils.mensErro("Lista salva com sucesso");
            this.listaDePrecoMB.setListaDePreco(new ListaDePreco());
        } catch (Exception ex) {
            lavor.util.FacesUtils.mensErro("Ocorreu um erro ao salvar a lista de preco \n" + ex.getMessage() + ex.toString());
            Logger.getLogger(ListaDePrecoBB.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return "sucesso";
    }

}
