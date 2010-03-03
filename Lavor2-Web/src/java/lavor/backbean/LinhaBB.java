/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.List;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
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

    public String DoListarPage(){
        List<Linha> linhas = linhaService.Todos();
        this.linhaMB.setLinhas(new ListDataModel(linhas));
        return "/linha/listar";
    }

    public String DoEditarPage(){
        this.linhaMB.setLinha((Linha) linhaMB.getLinhas().getRowData());
        return "/linha/editar";
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

    public String Atualizar(){
        try{
            linhaService.Atualizar(linhaMB.getLinha());
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Linha atualizada com sucesso" );
        }catch(Exception ex){
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar atualizar..");
        }
        return "sucesso";
    }


}
