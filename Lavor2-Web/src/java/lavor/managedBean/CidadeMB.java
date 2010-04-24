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
import lavor.entidade.Estado;
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
    private Cidade cidade;

    List<SelectItem> cidades;
    @Resource
    private CidadeService cidadeService;
    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;
    

    public CidadeMB() {
        this.cidade  = new Cidade();
        this.cidades = new ArrayList<SelectItem>();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
    



    //Estsava vindo todas as cidades do estado do posto.
    public String AtualizarListaDeCidades(){
        this.LimparListaDeCidades();
        List<Cidade> ListaDeCidades = cidadeService.PesquisarPorEstado(postoDeAtendimentoMB.getPostoDeAtendimento().getCidade().getEstado());
        for(Cidade cid:ListaDeCidades){
            cidades.add(new SelectItem(cid.getCidade().toString()));
        }
        return null;
    }

    public String AtualizarListaDeCidades(Estado estado){
        this.LimparListaDeCidades();
        List<Cidade> ListaDeCidades = cidadeService.PesquisarPorEstado(estado);
        for(Cidade cid:ListaDeCidades){
            cidades.add(new SelectItem(cid.getCidade().toString()));
        }
        return null;
    }




//    O cadastro de posto não estava mostrando as cidades.
//    public String AtualizarListaDeCidades(){
//        //this.LimparListaDeCidades();
//        List<Cidade> ListaDeCidades = cidadeService.PesquisarPorEstado(clienteMB.getCliente().getCidade().getEstado());
//        for(Cidade cid:ListaDeCidades){
//            cidades.add(new SelectItem(cid.getCidade().toString()));
//        }
//        return null;
//    }


}
