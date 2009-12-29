/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import javax.annotation.Resource;
import lavor.dao.UsuarioDao;
import lavor.entidade.Usuario;
import lavor.managedbean.UsuarioMB;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("usuarioBB")
@Scope("request")
public class usuarioBB {
    @Resource
    private UsuarioMB usuarioMB;
    @Resource
    private UsuarioDao usuarioDao;

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public UsuarioMB getUsuarioMB() {
        return usuarioMB;
    }

    public void setUsuarioMB(UsuarioMB usuarioMB) {
        this.usuarioMB = usuarioMB;
    }

    public usuarioBB() {
    }

    public String AdiconarUsuario(){
        this.usuarioDao.salvar(this.usuarioMB.getUsuario());
        this.usuarioMB.setUsuario(new Usuario());
        return "sucesso";
    }


}
