/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.io.Serializable;
import javax.annotation.Resource;
import lavor.entidade.EquipamentoCliente;
import lavor.managedBean.EquipamentoClienteMB;
import lavor.managedBean.EquipamentoMB;
import lavor.managedBean.PedidoMB;
import lavor.service.EquipamentoClienteService;
import lavor.utils.FacesUtils;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("equipamentoClienteBB")
@Scope("request")
public class EquipamentoClienteBB implements Serializable{

    @Resource
    private EquipamentoClienteMB equipamentoClienteMB;
    @Resource
    private EquipamentoMB equipamentoMB;
    @Resource
    private PedidoMB pedidoMB;

    @Resource
    private EquipamentoClienteService equipamentoClienteService;

    public EquipamentoClienteBB() {
    }

    public String SalvarEManter(){
        try {
            EquipamentoCliente equipamentoCliente = equipamentoClienteMB.getEquipamentoCliente();
            equipamentoCliente.setEquipamento(equipamentoMB.getEquipamento());
            equipamentoClienteMB.setEquipamentoCliente(equipamentoClienteService.Salvar(equipamentoCliente));
            this.pedidoMB.setEquipamentoSelecionado(Boolean.TRUE);
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Equipamento do cliente gravado com sucesso" );
        } catch (Exception ex) {
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar..");
        }
        return "sucesso";
    }



}
