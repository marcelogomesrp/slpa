/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import lavor.entidade.Usuario;
import lavor.managedbean.UsuarioMB;
import lavor.service.UsuarioService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("usuarioBB")
@Scope("request")
public class usuarioBB implements Serializable {
    @Resource
    private UsuarioService usuarioService;
    @Resource
    private UsuarioMB usuarioMB;

    public UsuarioMB getUsuarioMB() {
        return usuarioMB;
    }

    public void setUsuarioMB(UsuarioMB usuarioMB) {
        this.usuarioMB = usuarioMB;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public usuarioBB() {
    }

    public String SalvarUsuario(){
        try {
            this.usuarioService.Salvar(this.usuarioMB.getUsuario());
            this.usuarioMB.setUsuario(new Usuario());
            lavor.util.FacesUtils.mensInfo("Usuário adicionado com sucesso");
            return "sucesso";
        } catch (Exception ex) {
            Logger.getLogger(usuarioBB.class.getName()).log(Level.SEVERE, null, ex);
            return "falha";
        }
    }


}
