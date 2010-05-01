/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.List;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.entidade.Equipamento;
import lavor.entidade.Peca;
import lavor.managedBean.EquipamentoMB;
import lavor.managedBean.LinhaMB;
import lavor.managedBean.PecaMB;
import lavor.service.EquipamentoService;
import lavor.service.PecaService;
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
    private PecaMB pecaMB;
    @Resource
    private LinhaMB linhaMB;
    @Resource
    private LinhaBB linhaBB;
    
    @Resource
    private PecaService pecaService;


    @Resource
    private EquipamentoClienteBB equipamentoClienteBB;


    @Resource
    private EquipamentoService equipamentoService;

    public EquipamentoBB() {
    }

    public String DoNovoEquipamentoPage(){
        this.equipamentoMB.setEquipamento(new Equipamento());
        this.linhaBB.TodasAsLinhas();
        this.equipamentoMB.setEquipamentoAtivo(Boolean.FALSE);
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
        List<Peca> pecas = pecaService.PesquisarPorEquipamento(this.equipamentoMB.getEquipamento());
        this.pecaMB.setPecas(new ListDataModel(pecas));
        return "/equipamento/editar";
    }

    public String Salvar(){
        try{
            equipamentoService.Salvar(equipamentoMB.getEquipamento());
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Equipamento gravado com sucesso" );
            List<Equipamento> lista = equipamentoService.PesquisarPorNome(equipamentoMB.getEquipamento().getModelo());
            equipamentoMB.setEquipamento(lista.get(0));
            //equipamentoService.PesquisarPorNome(equipamentoMB.getEquipamento().getModelo()));
            this.equipamentoMB.setEquipamentoAtivo(Boolean.TRUE);
            //equipamentoMB.setEquipamento(new Equipamento());
        }catch(Exception ex){
            this.equipamentoMB.setEquipamentoAtivo(Boolean.FALSE);
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

    public String SelecionarEquipamento(){
        this.equipamentoMB.setEquipamento((Equipamento) equipamentoMB.getEquipamentos().getRowData());        
        return "sucesso";
    }

    public String LimparEquipamento(){
        this.equipamentoMB.setEquipamento(new Equipamento());
        return "sucesso";
    }

    public String Selecionar(){
        this.equipamentoMB.setEquipamento((Equipamento) equipamentoMB.getEquipamentos().getRowData());
        this.equipamentoMB.setEquipamentos(new ListDataModel());
        return "sucesso";
    }

//    public String PesquisarPorLinha(){
//        this.equipamentoMB.setEquipamentos(new ListDataModel(equipamentoService.PesquisarPorLinha(Long.MIN_VALUE)));
//        return "sucesso";
//    }

}
