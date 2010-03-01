/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;
 
import java.lang.String;
import lavor.dao.UsuarioDao;
import lavor.entidade.TipoUsuario;
import lavor.entidade.Usuario;
import lavor.utils.GenericExceptionMessageType;

/**
 *
 * @author marcelo
 */
public class UsuarioService {
    private UsuarioDao usuarioDao;

    public UsuarioService() {
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    private void EmailValido(String email) throws ServiceException{
        ServiceException serviceException = new ServiceException();
        
        if(lavor.utils.StringUtils.isNullOrEmpty(email)){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "O e-mail deve ser informado");
        }
        if(email.length() > 255){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "O e-mail deve ser menor que 256");
        }
        if(!lavor.utils.EmailUtils.isEmailValido(email)){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "O e-mail não e valido");
        }        
        if (serviceException.hasMessages()) {
            throw serviceException;
        }
    }
    private void EmailExitente(String email) throws ServiceException{
        ServiceException serviceException = new ServiceException();
        Usuario usuario = new Usuario();

        usuario = this.PesqPorEmail(email);
        if(usuario.getId() != null){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "O e-mail já cadastrado");
        }
        if (serviceException.hasMessages()) {
            throw serviceException;
        }

    }

    private void SenhaValida(String senha) throws ServiceException {
        ServiceException serviceException = new ServiceException();
        String senhaEmbranco = null;
        try {
            senhaEmbranco = lavor.utils.StringUtils.GerarMD5(senha);
        } catch (Exception ex) {
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "A senha deve ser informada");
        }
        if(senha.equals(senhaEmbranco)){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "A senha deve ser informada");
        }
        if (serviceException.hasMessages()) {
            throw serviceException;
        }

    }

    private void TipoUsuarioValido(TipoUsuario tipoUsuario) throws ServiceException {
        ServiceException serviceException = new ServiceException();
        if(lavor.utils.StringUtils.isNullOrEmpty(tipoUsuario.toString())){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "O tipo do usuario deve ser informado");
            //throw new Exception("O tipo do usuario deve ser informado");
        }
        if (serviceException.hasMessages()) {
            throw serviceException;
        }
    }

    private Boolean UsuarioValido(Usuario usuario) throws Exception{
        this.EmailValido(usuario.getEmail());
        this.SenhaValida(usuario.getSenha());
        this.TipoUsuarioValido(usuario.getTipoUsuario());
        return true;
    }

    public Usuario Salvar(Usuario usuario) throws Exception{
        if(this.UsuarioValido(usuario)){
            usuario = usuarioDao.salvar(usuario);
        }
        return usuario;
    }

    public Usuario PesquisarLoginESenha(Usuario usuario)throws Exception{
        this.EmailValido(usuario.getEmail());
        this.SenhaValida(usuario.getSenha());
        usuario = this.usuarioDao.pesquisarPorLoginESenha(usuario.getEmail(), usuario.getSenha());
        return usuario;
    }

    public Usuario atualizar(Usuario usuario) throws Exception{
        if(this.UsuarioValido(usuario)){
            usuario = this.usuarioDao.salvar(usuario);
        }
        return usuario;
    }

    public Boolean PodeSerSalvo(Usuario usuario) throws ServiceException {
        this.EmailValido(usuario.getEmail());
        this.EmailExitente(usuario.getEmail());
        this.SenhaValida(usuario.getSenha());
        this.TipoUsuarioValido(usuario.getTipoUsuario());
        return true;
    }

    void PodeSerAtualizador(Usuario usuarioNovo, Usuario usuario0riginal) throws ServiceException {
        if(!usuarioNovo.getEmail().equals(usuario0riginal.getEmail())){
            this.EmailValido(usuarioNovo.getEmail());
            this.EmailExitente(usuarioNovo.getEmail());
        }        
    }

    
    public Usuario PesqPorId(Long id){
        Usuario usuario = this.usuarioDao.pesquisarPorId(id);
        return usuario;
        
    }
    
    public Usuario PesqPorEmail(String email){
        return this.usuarioDao.pesquisarPorEmail(email);
    }










}
