/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.util.List;
import lavor.dao.PostoDeAtendimentoDao;
import lavor.entidade.PostoDeAtendimento;
import lavor.entidade.Usuario;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author marcelo
 */
public class PostoDeAtendimentoService {
    private PostoDeAtendimentoDao postoDeAtendimentoDao;
    private UsuarioService usuarioService;

    private ServiceException serviceException;

    public PostoDeAtendimentoService() {
        this.serviceException = new ServiceException();
    }

    public PostoDeAtendimentoDao getPostoDeAtendimentoDao() {
        return postoDeAtendimentoDao;
    }

    public void setPostoDeAtendimentoDao(PostoDeAtendimentoDao postoDeAtendimentoDao) {
        this.postoDeAtendimentoDao = postoDeAtendimentoDao;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }



    private void RazaoSocialValida(String razaoSocial) {
         if(lavor.utils.StringUtils.isNullOrEmpty(razaoSocial)){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "A razao social deve ser informada");
         }
        PostoDeAtendimento postoDeAtendimento = this.pesquisarPorRazaoSocial(razaoSocial);
        if(postoDeAtendimento != null){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "A razao social já foi cadastrada");
        }
    }

    private void CepValido(String cep){
        if(lavor.utils.StringUtils.isNullOrEmpty(cep)){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "O CEP informado não e valido");
        }else{
            cep = cep.replaceAll("[^\\d{L}]", "");

            if(cep.length() < 8){
                serviceException.addMessage(GenericExceptionMessageType.WARNING, "O CEP informado não e valido");
            }
        }
        
    }


    private boolean PodeSerSalvo(PostoDeAtendimento postoDeAtendimento) throws ServiceException{
        this.serviceException = new ServiceException();

        this.RazaoSocialValida(postoDeAtendimento.getRazaoSocial());
        this.usuarioService.PodeSerSalvo(postoDeAtendimento.getUsuario());
        this.CepValido(postoDeAtendimento.getCep());

        if (serviceException.hasMessages()) {
            throw serviceException;
        }
        return true;
    }

    public boolean PodeSerAtualizardo(PostoDeAtendimento novoPostoDeAtendimento, PostoDeAtendimento originalPostoDeAtendimento) throws ServiceException{
        this.serviceException = new ServiceException();

        if(!novoPostoDeAtendimento.getRazaoSocial().equals(originalPostoDeAtendimento.getRazaoSocial())){
            this.RazaoSocialValida(novoPostoDeAtendimento.getRazaoSocial());
        }
        this.usuarioService.PodeSerAtualizador(novoPostoDeAtendimento.getUsuario(), originalPostoDeAtendimento.getUsuario());

        if (serviceException.hasMessages()) {
            throw serviceException;
        }
        return true;
    }


    public PostoDeAtendimento Salvar(PostoDeAtendimento postoDeAtendimento) throws ServiceException{
        try{
            if(this.PodeSerSalvo(postoDeAtendimento)){
                //postoDeAtendimento = postoDeAtendimentoDao.atualizar(postoDeAtendimento);
                postoDeAtendimento = postoDeAtendimentoDao.salvar(postoDeAtendimento);
            }
        }catch(DataAccessException ex){
            throw new ServiceException("Ocorreu um erro ao tentar salvar", ex);
        }
        
        return postoDeAtendimento;
    }
    
    public void Atualizar(PostoDeAtendimento postoDeAtendimento) throws ServiceException {
        PostoDeAtendimento postoOriginal = this.postoDeAtendimentoDao.pesquisarPorId(postoDeAtendimento.getId());
        try{
            if(this.PodeSerAtualizardo(postoDeAtendimento, postoOriginal)){
                postoDeAtendimento = postoDeAtendimentoDao.atualizar(postoDeAtendimento);
            }
        }catch(DataAccessException ex){
            throw new ServiceException("Ocorreu um erro ao tentar salvar", ex);
        }

    }

    public PostoDeAtendimento pesquisarPorRazaoSocial(String razaoSocial){
        return this.postoDeAtendimentoDao.pesquisarPorRazaoSocial(razaoSocial);
    }
    public PostoDeAtendimento pesquisarPorRazaoSocial(PostoDeAtendimento postoDeAtendimento){
        return this.pesquisarPorRazaoSocial(postoDeAtendimento.getRazaoSocial());
    }

    public List pesquisarPorCidade(Long id) {
        return this.postoDeAtendimentoDao.pesquisarPorCidade(id);
    }

    public PostoDeAtendimento PesquisarPorUsuario(Usuario usuario){
        return this.postoDeAtendimentoDao.pesquisarPorUsuario(usuario);
    }






}
