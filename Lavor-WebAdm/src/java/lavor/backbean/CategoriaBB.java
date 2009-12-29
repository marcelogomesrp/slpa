/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
import lavor.dao.CategoriaDao;
import lavor.entidade.Categoria;
import lavor.managedbean.CategoriaMB;
import lavor.service.CategoriaService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("categoriaBB")
@Scope("request")
public class CategoriaBB {
    @Resource
    private CategoriaService categoriaService;
    @Resource
    private CategoriaMB categoriaMB;

    public CategoriaBB() {
    }

    public CategoriaService getCategoriaService() {
        return categoriaService;
    }

    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public CategoriaMB getCategoriaMB() {
        return categoriaMB;
    }

    public void setCategoriaMB(CategoriaMB categoriaMB) {
        this.categoriaMB = categoriaMB;
    }



    public String SalvarCategoria(){
        try{
            this.categoriaService.Salvar(categoriaMB.getCategoria());
            categoriaMB.setCategoria(new Categoria());
            lavor.util.FacesUtils.mensInfo("Categoria adicionada com sucesso");
            return "sucesso";
        }catch(Exception ex){
            lavor.util.FacesUtils.mensErro("Não foi possível adicionar a categoria: " + ex.getMessage());
            return "falha";
        }
    }

}
