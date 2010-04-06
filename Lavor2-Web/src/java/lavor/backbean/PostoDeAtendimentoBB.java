/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import lavor.entidade.Cidade;
import lavor.entidade.PostoDeAtendimento;
import lavor.entidade.TipoUsuario;
import lavor.managedBean.CidadeMB;
import lavor.managedBean.PostoDeAtendimentoMB;
import lavor.managedBean.UsuarioMB;
import lavor.service.CidadeService;
import lavor.service.PostoDeAtendimentoService;
import lavor.service.UsuarioService;
import lavor.utils.FacesUtils;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("postoDeAtendimentoBB")
@Scope("request")
public class PostoDeAtendimentoBB {

    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;
    @Resource
    private CidadeMB cidadeMB;
    @Resource
    private UsuarioMB usuarioMB;

    @Resource
    private PostoDeAtendimentoService postoDeAtendimentoService;
    @Resource
    private UsuarioService usuarioService;
    @Resource
    private CidadeService cidadeService;

    private Boolean postosDeAtendimentoTemItem;
    

    public PostoDeAtendimentoBB() {
        this.postosDeAtendimentoTemItem = false;
    }

    public PostoDeAtendimentoMB getPostoDeAtendimentoMB() {
        return postoDeAtendimentoMB;
    }

    public void setPostoDeAtendimentoMB(PostoDeAtendimentoMB postoDeAtendimentoMB) {
        this.postoDeAtendimentoMB = postoDeAtendimentoMB;
    }

    public Boolean getPostosDeAtendimentoTemItem() {
        return (postoDeAtendimentoMB.getPostosDeAtendimento().getRowCount() > 0? Boolean.TRUE:Boolean.FALSE);
    }

    public void setPostosDeAtendimentoTemItem(Boolean postosDeAtendimentoTemItem) {
        this.postosDeAtendimentoTemItem = postosDeAtendimentoTemItem;
    }



    public String DoNovoPage(){
        postoDeAtendimentoMB.setPostoDeAtendimento(new PostoDeAtendimento());
        postoDeAtendimentoMB.setPostosDeAtendimento(new ListDataModel());
        return "/admin/posto/novo";
    }

    public String DoListarPage(){
        postoDeAtendimentoMB.setPostoDeAtendimento(new PostoDeAtendimento());
        this.cidadeMB.setCidades(new ArrayList<SelectItem>());
        this.postoDeAtendimentoMB.setPostosDeAtendimento(new ListDataModel());
        return "/admin/posto/listar";
    }

    public String DoListarPageVoltar(){
        return "/admin/posto/listar";
    }

    public String DoDetalhesPage(){
        this.postoDeAtendimentoMB.setPostoDeAtendimento((PostoDeAtendimento) this.postoDeAtendimentoMB.getPostosDeAtendimento().getRowData());
        return "posto/detalhes";
    }

    public String DoEditarPage(){
        this.postoDeAtendimentoMB.setPostoDeAtendimento((PostoDeAtendimento) this.postoDeAtendimentoMB.getPostosDeAtendimento().getRowData());
        return "posto/editar";
    }

    public String DoLogout(){

//        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
        return "index";
    }
    

    public String Salvar(){
        PostoDeAtendimento novoPostoDeAtendimento = postoDeAtendimentoMB.getPostoDeAtendimento();
        Cidade cidade = novoPostoDeAtendimento.getCidade();
        cidade = cidadeService.PesquisarPorCidadeEstado(cidade.getCidade(), cidade.getEstado());

        novoPostoDeAtendimento.setCidade(cidade);
        novoPostoDeAtendimento.getUsuario().setTipoUsuario(TipoUsuario.postoDeAtendimento);
        novoPostoDeAtendimento.getUsuario().setAtivo(Boolean.TRUE);

        try{
            postoDeAtendimentoService.Salvar(novoPostoDeAtendimento);
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Posto gravado com sucesso" );
            postoDeAtendimentoMB.setPostoDeAtendimento(new PostoDeAtendimento());
        }catch(Exception ex){
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar.." + ex.getMessage() + " causa " + ex.getCause());
        }        
        return "sucesso";
    }

    public String Atualizar(){
        PostoDeAtendimento novoPostoDeAtendimento = postoDeAtendimentoMB.getPostoDeAtendimento();
        Cidade cidade = novoPostoDeAtendimento.getCidade();
        cidade = cidadeService.PesquisarPorCidadeEstado(cidade.getCidade(), cidade.getEstado());
        novoPostoDeAtendimento.setCidade(cidade);
        try{
            postoDeAtendimentoService.Atualizar(novoPostoDeAtendimento);
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Posto atualizado com sucesso" );
            postoDeAtendimentoMB.setPostoDeAtendimento(new PostoDeAtendimento());
        }catch(Exception ex){
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar atualizar..");
        }
        return "sucesso";
    }


    public String PesquisarPorCidade(){
        Cidade cidade = this.postoDeAtendimentoMB.getPostoDeAtendimento().getCidade();
        cidade = cidadeService.PesquisarPorCidadeEstado(cidade.getCidade(), cidade.getEstado());
        List<PostoDeAtendimento> postos = this.postoDeAtendimentoService.pesquisarPorCidade(cidade.getId());
        //List<PostoDeAtendimento> postos = this.postoDeAtendimentoService.pesquisarPorCidade(1L);
        this.postoDeAtendimentoMB.setPostosDeAtendimento(new ListDataModel(postos));

        return "sucesso";
    }

    //Metodos do posto

    public String PostoDoEditar(){
        return "editar";
    }
    
    public String PostoDoTrocarSenha(){
        return "/posto/trocarSenha";
    }

    public String PostoAtualizar(){
        PostoDeAtendimento novoPostoDeAtendimento = postoDeAtendimentoMB.getPostoDeAtendimento();
        Cidade cidade = novoPostoDeAtendimento.getCidade();
        cidade = cidadeService.PesquisarPorCidadeEstado(cidade.getCidade(), cidade.getEstado());
        novoPostoDeAtendimento.setCidade(cidade);
        try{            
            postoDeAtendimentoService.Atualizar(novoPostoDeAtendimento);
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Posto atualizado com sucesso" );
            postoDeAtendimentoMB.setPostoDeAtendimento(novoPostoDeAtendimento);
        }catch(Exception ex){
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar atualizar..");
        }
        return "sucesso";
    }


    // fim posto

}
