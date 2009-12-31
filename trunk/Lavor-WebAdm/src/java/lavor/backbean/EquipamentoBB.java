/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import lavor.entidade.Categoria;
import lavor.entidade.Equipamento;
import lavor.entidade.Peca;
import lavor.managedbean.EquipamentoMB;
import lavor.service.CategoriaService;
import lavor.service.EquipamentoService;
import lavor.service.PecaService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 * @Scope("request")
 */
@Controller("equipamentoBB")
@Scope("session")
public class EquipamentoBB implements Serializable {
    @Resource
    private EquipamentoService equipamentoService;
    @Resource
    private EquipamentoMB equipamentoMB;
    @Resource
    CategoriaService categoriaService;
    @Resource
    PecaService pecaService;
    private Long numero;
    private ListDataModel pecas;
    private ListDataModel pecasSelecionadas;
    private String nome;

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

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public ListDataModel getPecas() {        
        return pecas;
    }

    public void setPecas(ListDataModel pecas) {
        this.pecas = pecas;
    }

    public PecaService getPecaService() {
        return pecaService;
    }

    public void setPecaService(PecaService pecaService) {
        this.pecaService = pecaService;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ListDataModel getPecasSelecionadas() {
        pecasSelecionadas = new ListDataModel(equipamentoMB.getEquipamento().getPecas());
        return pecasSelecionadas;
    }

    public void setPecasSelecionadas(ListDataModel pecasSelecionadas) {
        this.pecasSelecionadas = pecasSelecionadas;
    }

    public String AdicionarPeca(){
        //acha este aqui
//        if(equipamentoMB.getEquipamento().getPecas() == null){
//            equipamentoMB.getEquipamento().setPecas(new ArrayList<Peca>());
//        }
        //equipamentoMB.getEquipamento().getPecas().add((Peca) pecas.getRowData());
        equipamentoMB.getEquipamento().getPecas().add((Peca) pecas.getRowData());
        //Peca pecaSelecionada = (Peca) pecas.getRowData();
        //Equipamento equipamentoSelecionado = equipamentoService.LocalizarPorId(PecaSelecionada.getId());
        //pecaSelecioanda = pecaService.
//        equipamentoMB.getEquipamento().getPecas().add(pecaService.BuscarPorId(pecaSelecionada.getId()));
                //equipamentoService.LocalizarPorId(PecaSelecionada.getId()));

        return "sucesso";
    }




    public String atualiza(){
        for(Categoria categoria:categoriaService.LocalizarTodasCategoria()){
            //categorias.add(new SelectItem(categoria.getId(),categoria.getNome()));
            System.out.println("Categoria: " + categoria.getId() + "\t" + categoria.getNome() );
        }
        return "sucesso";
    }


    public String BuscarPecasPorNome(){
        System.out.println("Buscar por nome");
        pecas = new ListDataModel(pecaService.BuscarPecaPorNome(nome));
        return "sucesso";
    }

    public String SalvarEquipamento(){
        try {
            equipamentoMB.getEquipamento().setCategoria(categoriaService.LocalizarPorId(numero));
            //equipamentoService.Salvar(equipamentoMB.getEquipamento());
            equipamentoService.Atualizar(equipamentoMB.getEquipamento());
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
