/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.model.SelectItem;
import lavor.entidade.Cidade;
import lavor.service.CidadeService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("cidadeMB")
@Scope("session")
public class CidadeMB implements Serializable{

    List<SelectItem> cidades;
    @Resource
    private CidadeService cidadeService;
    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;

    public CidadeMB() {
        this.cidades = new ArrayList<SelectItem>();
    }

    public List<SelectItem> getCidades() {
        return cidades;
    }

    public void setCidades(List<SelectItem> cidades) {
        this.cidades = cidades;
    }

    public CidadeService getCidadeService() {
        return cidadeService;
    }

    public void setCidadeService(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    public PostoDeAtendimentoMB getPostoDeAtendimentoMB() {
        return postoDeAtendimentoMB;
    }

    public void setPostoDeAtendimentoMB(PostoDeAtendimentoMB postoDeAtendimentoMB) {
        this.postoDeAtendimentoMB = postoDeAtendimentoMB;
    }

    public String LimparListaDeCidades(){
        this.cidades = new ArrayList<SelectItem>();
        return "sucesso";
    }
    

    public String AtualizarListaDeCidades(){
        this.LimparListaDeCidades();
        List<Cidade> ListaDeCidades = cidadeService.PesquisarPorEstado(postoDeAtendimentoMB.getPostoDeAtendimento().getCidade().getEstado());
        for(Cidade cidade:ListaDeCidades){
            cidades.add(new SelectItem(cidade.getCidade().toString()));
        }
        return null;
    }



}
