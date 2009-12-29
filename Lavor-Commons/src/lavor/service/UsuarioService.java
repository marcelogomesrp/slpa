/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import lavor.dao.UsuarioDao;
import lavor.entidade.Usuario;

/**
 *
 * @author marcelo
 */
public class UsuarioService {
    UsuarioDao usuarioDao;

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public UsuarioService() {
    }

   public Usuario Salvar(Usuario usuario) throws Exception{

        this.UsarioPodeSerSalvo(usuario);
        usuario = usuarioDao.salvar(usuario);
        return usuario;
    }

   private void UsarioPodeSerSalvo(Usuario usario){

   }

}
