/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import lavor.entidade.TipoUsuario;
import lavor.entidade.Usuario;
import lavor.managedBean.PostoDeAtendimentoMB;
import lavor.managedBean.UsuarioMB;
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

@Controller("indexBB")
@Scope("request")
public class indexBB implements Serializable{
    @Resource
    private UsuarioMB usuarioMB;
    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;
    @Resource
    private UsuarioService usuarioService;
    @Resource
    private PostoDeAtendimentoService postoDeAtendimentoService;

    public indexBB() {        
    }

    public String Entrar() {
        try {
            Usuario usuario = usuarioMB.getUsuario();
            usuario = usuarioService.PesquisarLoginESenha(usuarioMB.getUsuario());
            if (usuario.getId() != null) {
                if (usuario.getTipoUsuario().equals(TipoUsuario.postoDeAtendimento)) {
                    usuarioMB.setUsuario(usuario);
                    postoDeAtendimentoMB.setPostoDeAtendimento(postoDeAtendimentoService.PesquisarPorUsuario(usuario));
                    return "posto/index";
                } else {
                    if (usuario.getTipoUsuario().equals(TipoUsuario.administrador)) {
                        usuarioMB.setUsuario(usuario);
                        return "admin/index";
                    }
                }
            }else{
                FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Email e/ou senha invalido");
            }
        } catch (Exception ex) {
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar entrar");
            Logger.getLogger(indexBB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }

    public String Cancelar(){
        this.usuarioMB.setUsuario(new Usuario());
        return "index";
    }
    

}
