/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.List;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.entidade.Equipamento;
import lavor.managedBean.EquipamentoMB;
import lavor.managedBean.LinhaMB;
import lavor.service.EquipamentoService;
import lavor.utils.FacesUtils;
import lavor.utils.GenericExceptionMessageType;
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
    private EquipamentoMB equipamentoMB;
    @Resource
    private LinhaMB linhaMB;
    @Resource
    private LinhaBB linhaBB;


    @Resource
    private EquipamentoService equipamentoService;

    public EquipamentoBB() {
    }

    public String DoNovoEquipamentoPage(){
        this.equipamentoMB.setEquipamento(new Equipamento());
        this.linhaBB.TodasAsLinhas();
        return "/equipamento/novo" ;
    }

    public String DoPesquisarPage(){
        this.equipamentoMB.setEquipamento(new Equipamento());
        this.linhaBB.TodasAsLinhas();
        this.equipamentoMB.setEquipamentos(new ListDataModel());
        return "/equipamento/listar";
    }

    public String DoEditarPage(){
        this.equipamentoMB.setEquipamento((Equipamento) equipamentoMB.getEquipamentos().getRowData());
        return "/equipamento/editar";
    }

    public String Salvar(){
        try{
            equipamentoService.Salvar(equipamentoMB.getEquipamento());
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Equipamento gravado com sucesso" );
            equipamentoMB.setEquipamento(new Equipamento());
        }catch(Exception ex){
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar..");
        }
        return "sucesso";
    }

    public String Atualizar(){
        Equipamento equipamentoAtualizado =  new Equipamento();
        try{
            equipamentoAtualizado = equipamentoService.Atualizar(equipamentoMB.getEquipamento());
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Equipamento gravado com sucesso" );
        }catch(Exception ex){
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar atualizar..");
        }
        return "sucesso";
    }

    public String PesquisarLinha(){
        List<Equipamento> equipamentos = this.equipamentoService.PesquisarPorLinha(this.equipamentoMB.getEquipamento().getLinha().getId());
        this.equipamentoMB.setEquipamentos(new ListDataModel(equipamentos));
        return "/equipamento/listar";
    }



}
