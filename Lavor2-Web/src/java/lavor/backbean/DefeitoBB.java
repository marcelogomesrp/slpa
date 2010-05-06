/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.List;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.entidade.Defeito;
import lavor.managedBean.DefeitoMB;
import lavor.service.DefeitoService;
import lavor.utils.FacesUtils;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */
@Controller("defeitoBB")
@Scope("request")
public class DefeitoBB {
    @Resource
    private DefeitoMB defeitoMB;

    @Resource
    private DefeitoService defeitoService;

    public DefeitoBB() {
    }

    public String DoNovoDefeitoPage(){
        this.defeitoMB.setDefeito(new Defeito());
        return "/defeito/novo";
    }

    public String DoListarPage(){
        List<Defeito> defeitos = defeitoService.Todos();
        return "/defeito/listar";
    }

    public String Salvar(){
        try{
            this.defeitoService.Salvar(defeitoMB.getDefeito());
            this.defeitoMB.setDefeito(new Defeito());
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Defeito gravado com sucesso" );
        }catch(Exception ex){
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar..");
        }
        return "sucesso";
    }

    public String Atualizar(){
        try{
            this.defeitoService.Atualizar(defeitoMB.getDefeito());
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Defeito atualizado com sucesso" );
        }catch(Exception ex){
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar..");
        }
        return "sucesso";
    }

    public String DoListarDefeitosPage(){
        this.defeitoMB.setDefeitos(new ListDataModel(defeitoService.Todos()));
        return "/defeito/listar";
    }

    public String DoEditarPage(){
        this.defeitoMB.setDefeito((Defeito) defeitoMB.getDefeitos().getRowData());
        return "/defeito/editar";
    }
}
