/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedbean;

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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public FornecedorMB() {
        this.fornecedor = new Fornecedor();
    }



}
