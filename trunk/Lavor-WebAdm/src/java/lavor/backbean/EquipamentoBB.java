/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.model.SelectItem;
import lavor.entidade.Categoria;
import lavor.entidade.Equipamento;
import lavor.managedbean.EquipamentoMB;
import lavor.service.CategoriaService;
import lavor.service.EquipamentoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */
@Controller("equipamentoBB")
@Scope("request")
public class EquipamentoBB {
    @Resource
    private EquipamentoService equipamentoService;
    @Resource
    private EquipamentoMB equipamentoMB;
    @Resource
    CategoriaService categoriaService;

    private List<SelectItem> categorias;


    public EquipamentoBB() {
    }

    public EquipamentoMB getEquipamentoMB() {
        return equipamentoMB;
    }

    public void setEquipamentoMB(EquipamentoMB equipamentoMB) {
        this.equipamentoMB = equipamentoMB;
    }

    public EquipamentoService getEquipamentoService() {
        return equipamentoService;
    }

    public void setEquipamentoService(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    public List<SelectItem> getCategorias() {
        categorias = new ArrayList<SelectItem>();
        for(Categoria categoria:categoriaService.LocalizarTodasCategoria()){
            categorias.add(new SelectItem(categoria.getId(),categoria.getNome()));
            //System.out.println("Categoria: " + categoria.getId() + "\t" + categoria.getNome() );
        }
        return categorias;
    }

    public void setCategorias(List<SelectItem> categorias) {
        this.categorias = categorias;
    }

    public CategoriaService getCategoriaService() {
        return categoriaService;
    }

    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public String atualiza(){
        for(Categoria categoria:categoriaService.LocalizarTodasCategoria()){
            //categorias.add(new SelectItem(categoria.getId(),categoria.getNome()));
            System.out.println("Categoria: " + categoria.getId() + "\t" + categoria.getNome() );
        }
        return "sucesso";
    }




    public String SalvarEquipamento(){
        try {
            equipamentoService.Salvar(equipamentoMB.getEquipamento());
            lavor.util.FacesUtils.mensInfo("Equipamento adicionado com sucesso");
            equipamentoMB.setEquipamento(new Equipamento());
            return "sucesso";
        } catch (Exception ex) {
            lavor.util.FacesUtils.mensErro("Erro ao adicionar equipamento: " + ex.getMessage());
            Logger.getLogger(EquipamentoBB.class.getName()).log(Level.SEVERE, null, ex);
            return "falha";
        }
    }

}
