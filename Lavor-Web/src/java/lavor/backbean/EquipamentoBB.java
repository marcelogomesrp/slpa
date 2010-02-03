/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.managedbean.EquipamentoMB;
import lavor.service.EquipamentoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Marcelo
 */

@Controller("equipamentoBB")
@Scope("request")
public class EquipamentoBB {
    @Resource
    private EquipamentoService equipamentoService;
    @Resource
    private EquipamentoMB equipamentoMB;

    public String DoListarManuaisPage(){
        equipamentoMB.setListaDeEquipamento( new ListDataModel());
        return "ListarManuais";
    }

    public String LocalizarEquipamentoPorNome(){
        equipamentoMB.setListaDeEquipamento( new ListDataModel(equipamentoService.LocalizarPorNome(equipamentoMB.getEquipamento().getNome())));
        return "sucesso";
    }

}
