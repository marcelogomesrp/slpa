/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
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

}
