/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
import lavor.entidade.Cidade;
import lavor.managedBean.CidadeMB;
import lavor.service.CidadeService;
import lavor.service.ServiceException;
import lavor.utils.FacesUtils;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("cidadeBB")
@Scope("request")
public class CidadeBB {
    
    @Resource
    private CidadeMB cidadeMB;
    @Resource
    private CidadeService cidadeService;

    public CidadeBB() {
    }

    public String DoNovoCidadePage(){
        return "/cidade/admin";
    }

    public String Salvar(){
        try {
            this.cidadeService.Salvar(cidadeMB.getCidade());
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Cidade adicionada com sucesso" );
            cidadeMB.setCidade(new Cidade());
        } catch (ServiceException ex) {
           FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar..");
        }
        return "sucesso";
    }



}
