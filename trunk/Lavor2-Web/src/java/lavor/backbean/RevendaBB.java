/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.model.SelectItem;
import lavor.entidade.PostoDeAtendimento;
import lavor.entidade.Revenda;
import lavor.managedBean.PostoDeAtendimentoMB;
import lavor.managedBean.RevendaMB;
import lavor.service.RevendaService;
import lavor.utils.FacesUtils;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("revendaBB")
@Scope("request")
public class RevendaBB {

    @Resource
    private RevendaMB revendaMB;

    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;

    @Resource
    private RevendaService revendaService;

    public RevendaBB() {
    }


    public String Salvar(){
        try{
            this.revendaService.Salvar(revendaMB.getRevenda());
            this.DoNovaRevendaPage();
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Revenda gravada com sucesso" );
        }catch(Exception ex){
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar..");
        }
        return "sucesso";
    }

    public String DoNovaRevendaPage(){
        Revenda revenda = new Revenda();
        revenda.setPostoDeAtendimento(postoDeAtendimentoMB.getPostoDeAtendimento());
        revendaMB.setRevenda(revenda);
        return "/revenda/novo";
    }

    public List<Revenda> PesquisarRevendaPorPosto(PostoDeAtendimento postoDeAtendimento){
        List<Revenda> revendas = this.revendaService.PesquisarPorPosto(postoDeAtendimento);
        return revendas;
        
    }

    public void CriarRevendaSelectItem(PostoDeAtendimento postoDeAtendimento){
        List<Revenda> revendas = this.PesquisarRevendaPorPosto(postoDeAtendimento);
        this.revendaMB.setRevendas(new ArrayList<SelectItem>());
        for(Revenda revenda:revendas){
            revendaMB.getRevendas().add(new SelectItem(revenda.getId(), revenda.getRazaoSocial()));
        }
    }


}
