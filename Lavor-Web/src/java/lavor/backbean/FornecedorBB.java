/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
import lavor.entidade.Fornecedor;
import lavor.managedbean.FornecedorMB;
import lavor.service.FornecedorService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Marcelo
 */

@Controller("fornecedorBB")
@Scope("request")
public class FornecedorBB {
    @Resource
    private FornecedorService fornecedorService;
    @Resource
    private FornecedorMB fornecedorMB;    
    

    public FornecedorBB() {
    }


    public String NovoFornecedor(){
        this.fornecedorMB.setFornecedor(new Fornecedor());
        return "sucesso";
    }

    public String DoLocalizarFornecedorPage(){
        return "LocalizarFornecedor";
    }

    public String LocalizarFornecedorPorNome(){
        fornecedorMB.setFornecedores(fornecedorService.BuscarTodosFornecedores());
        System.out.println("tem muito fornecedor aqui" + fornecedorMB.getFornecedoresModel().getRowCount());
        return "Sucesso";
    }

    public String SelecionarFornecedor(){
        fornecedorMB.setFornecedor((Fornecedor) fornecedorMB.getFornecedoresModel().getRowData());
        return "ListaDeCategorias";
    }
    

}
