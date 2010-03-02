/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
import lavor.entidade.Linha;
import lavor.managedBean.LinhaMB;
import lavor.service.LinhaService;
import lavor.utils.FacesUtils;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("linhaBB")
@Scope("request")
public class LinhaBB {

    @Resource
    private LinhaMB linhaMB;
    
    @Resource
    private LinhaService linhaService;

    public LinhaBB() {
    }

    public String DoNovoLinhaPage(){
        this.linhaMB.setLinha(new Linha());
        return "/linha/novo";
    }

    public String Salvar(){
        try{
            linhaService.Salvar(linhaMB.getLinha());
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Linha gravada com sucesso" );
            linhaMB.setLinha(new Linha());
        }catch(Exception ex){
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar..");
        }
        return "sucesso";
    }


}
