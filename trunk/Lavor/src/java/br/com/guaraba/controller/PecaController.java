/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.controller;


import br.com.guaraba.controller.util.JsfUtil;
import br.com.guaraba.facade.PecaExistenteFacade;
import br.com.guaraba.facade.PecaFacade;
import br.com.guaraba.facade.PecaImportacaoFacade;
import br.com.guaraba.facade.PecaNovoFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author marcelo
 */
@ManagedBean
@SessionScoped
public class PecaController implements Serializable{
    @EJB
    private PecaFacade pecaFacade;
    @EJB
    private PecaImportacaoFacade pecaImportacaoFacade;
    @EJB
    private PecaExistenteFacade pecaExistenteFacade;
    @EJB
    private PecaNovoFacade pecaNovoFacade;



    public int getTotalPecasImportadas(){
        return pecaImportacaoFacade.count();
    }

    public int getTotalPecaExistente(){
        return pecaExistenteFacade.count();
    }

    public int getTotalPecaNova(){
        return pecaNovoFacade.count();
    }

    public PecaController() {
    }

    public PecaFacade getPecaFacade() {
        return pecaFacade;
    }

    public void setPecaFacade(PecaFacade pecaFacade) {
        this.pecaFacade = pecaFacade;
    }

    public PecaImportacaoFacade getPecaImportacaoFacade() {
        return pecaImportacaoFacade;
    }

    public void setPecaImportacaoFacade(PecaImportacaoFacade pecaImportacaoFacade) {
        this.pecaImportacaoFacade = pecaImportacaoFacade;
    }

    public PecaExistenteFacade getPecaExistenteFacade() {
        return pecaExistenteFacade;
    }

    public void setPecaExistenteFacade(PecaExistenteFacade pecaExistenteFacade) {
        this.pecaExistenteFacade = pecaExistenteFacade;
    }

    public PecaNovoFacade getPecaNovoFacade() {
        return pecaNovoFacade;
    }

    public void setPecaNovoFacade(PecaNovoFacade pecaNovoFacade) {
        this.pecaNovoFacade = pecaNovoFacade;
    }

    public String ConfirmaValidacao(){
        pecaFacade.ConfirmaArquivo();
        JsfUtil.addSuccessMessage("Importacao concluida com sucesso");
        return "Validation";
    }
    
}
