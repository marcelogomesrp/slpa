/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.ListDataModel;
import lavor.entidade.Fornecedor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Marcelo
 */


@Controller("fornecedorMB")
@Scope("session")
public class FornecedorMB {

    private Fornecedor fornecedor;
    private List<Fornecedor> fornecedores;
    private ListDataModel fornecedoresModel;

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public FornecedorMB() {
        this.fornecedor         = new Fornecedor();
        this.fornecedores       = new ArrayList<Fornecedor>();
        this.fornecedoresModel  = new ListDataModel();
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
        this.atualizarModel();
    }

    public ListDataModel getFornecedoresModel() {
        return fornecedoresModel;
    }

    public void setFornecedoresModel(ListDataModel fornecedoresModel) {
        this.fornecedoresModel = fornecedoresModel;
    }

    private void atualizarModel(){
        fornecedoresModel = new ListDataModel(fornecedores);
    }



}
