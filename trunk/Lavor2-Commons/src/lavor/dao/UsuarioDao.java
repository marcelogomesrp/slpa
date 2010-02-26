/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.dao;

import lavor.entidade.Usuario;

/**
 *
 * @author marcelo
 */
public interface UsuarioDao extends DaoGenerico<Usuario, Long>{
    public Usuario pesquisarPorLoginESenha(String login, String senha);
    public Usuario pesquisarPorEmail(String email);

}
