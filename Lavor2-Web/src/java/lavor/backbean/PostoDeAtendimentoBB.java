/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
import lavor.entidade.Cidade;
import lavor.entidade.PostoDeAtendimento;
import lavor.entidade.TipoUsuario;
import lavor.managedBean.CidadeMB;
import lavor.managedBean.PostoDeAtendimentoMB;
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
    private PostoDeAtendimentoService postoDeAtendimentoService;
    @Resource
    private UsuarioService usuarioService;
    @Resource
    private CidadeService cidadeService;
    

    public PostoDeAtendimentoBB() {
    }

    public PostoDeAtendimentoMB getPostoDeAtendimentoMB() {
        return postoDeAtendimentoMB;
    }

    public void setPostoDeAtendimentoMB(PostoDeAtendimentoMB postoDeAtendimentoMB) {
        this.postoDeAtendimentoMB = postoDeAtendimentoMB;
    }

    public String DoNovoPage(){
        postoDeAtendimentoMB.setPostoDeAtendimento(new PostoDeAtendimento());
        return "/admin/posto/novo";
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
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar..");
        }
        
//        PostoDeAtendimento novoPostoDeAtendimento = postoDeAtendimentoMB.getPostoDeAtendimento();
//        Usuario novoUsuario = novoPostoDeAtendimento.getUsuario();
//        Cidade cidade = novoPostoDeAtendimento.getCidade();
//        cidade = cidadeService.PesquisarPorCidadeEstado(cidade.getCidade(), cidade.getEstado());
//        novoPostoDeAtendimento.setCidade(cidade);
//        novoUsuario.setTipoUsuario(TipoUsuario.postoDeAtendimento);
//        novoUsuario.setAtivo(Boolean.TRUE);
//        try {
//            if(novoUsuario.getId() == null){
//                novoUsuario = usuarioService.Salvar(novoUsuario);
//                novoPostoDeAtendimento.setUsuario(novoUsuario);
//                novoPostoDeAtendimento = postoDeAtendimentoService.Salvar(novoPostoDeAtendimento);
//                lavor.utils.FacesUtils_teste.mensInfo("Posto de atendimento salvo com sucesso ");
//                postoDeAtendimentoMB.setPostoDeAtendimento(new PostoDeAtendimento());
//                cidadeMB.LimparListaDeCidades();
//            }
//        } catch (Exception ex) {
//                 FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar..");
//            //lavor.utils.FacesUtils_teste.mensErro("Ocorreu um erro ao tentar salvar. " + ex.getMessage() + " causa " + ex.getCause());
//            Logger.getLogger(PostoDeAtendimentoBB.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return "sucesso";
    }

}
