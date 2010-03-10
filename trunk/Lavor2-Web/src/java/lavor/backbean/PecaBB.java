/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.io.Serializable;
import javax.annotation.Resource;
import lavor.entidade.Peca;
import lavor.managedBean.EquipamentoMB;
import lavor.managedBean.PecaMB;
import lavor.service.PecaService;
import lavor.utils.FacesUtils;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("pecaBB")
@Scope("request")
public class PecaBB implements Serializable{

    @Resource
    private PecaMB pecaMB;
    @Resource
    private EquipamentoMB equipamentoMB;

    @Resource
    private PecaService pecaService;

    public PecaBB() {
    }

    public String DoNovoPage(){
        Peca novaPeca = new Peca();
        novaPeca.setEquipamento(this.equipamentoMB.getEquipamento());
        this.pecaMB.setPeca(novaPeca);
        return "/peca/novo";
    }

    public String Salvar(){
        try{                       
            this.pecaService.Salvar(pecaMB.getPeca());
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Peca gravada com sucesso" );
            Peca novaPeca = new Peca();
            novaPeca.setEquipamento(this.equipamentoMB.getEquipamento());
            this.pecaMB.setPeca(novaPeca);
        }catch(Exception ex){
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar..");
        }
        return "sucesso";
    }

}
