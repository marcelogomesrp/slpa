/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lavor.dao.UsuarioDao;
import lavor.entidade.Usuario;

/**
 *
 * @author marcelo
 */
public class UsuarioDaoImp extends DaoGenericoImp<Usuario, Long> implements UsuarioDao {

    public Usuario pesquisarPorLoginESenha(String email, String senha) {
        Usuario usuario = new Usuario();
        try {
            String SQL = "SELECT u from Usuario u  WHERE email = :email and senha = :senha and ativo = :ativo";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("email", email);
            params.put("senha", senha);
            params.put("ativo", Boolean.TRUE);

            Usuario usuario2 = new Usuario(this.pesqParam(SQL, params));
            System.out.println(usuario2.getId());
            return usuario2;
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public Usuario pesquisarPorEmail(String email) {

        String SQL = "SELECT u from Usuario u  WHERE email = :email";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("email", email);
        List<Usuario> usuarios = this.listPesqParam(SQL, params);
        if (usuarios.size() == 1) {
            return usuarios.get(0);
        }
        return new Usuario();
    }
    
}
