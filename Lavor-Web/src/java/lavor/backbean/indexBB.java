/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lavor.backbean;

import javax.annotation.Resource;
import lavor.entidade.PostoDeAtendimento;
import lavor.service.PostoDeAtendimentoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */
@Controller("indexBB")
@Scope("session")
public class indexBB {

    private String nome;
    @Resource
    private PostoDeAtendimentoService postoDeAtendimentoService;
    private PostoDeAtendimento postoDeAtendimento;

    public indexBB() {
        this.postoDeAtendimento = new PostoDeAtendimento();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PostoDeAtendimentoService getPostoDeAtendimentoService() {
        return postoDeAtendimentoService;
    }

    public void setPostoDeAtendimentoService(PostoDeAtendimentoService postoDeAtendimentoService) {
        this.postoDeAtendimentoService = postoDeAtendimentoService;
    }

    public PostoDeAtendimento getPostoDeAtendimento() {
        return postoDeAtendimento;
    }

    public void setPostoDeAtendimento(PostoDeAtendimento postoDeAtendimento) {
        this.postoDeAtendimento = postoDeAtendimento;
    }
    

    public String SalvarPostoDeAtendimento(){
        this.postoDeAtendimentoService.Salvar(postoDeAtendimento);
        this.postoDeAtendimento = new PostoDeAtendimento();
        return "sucesso";
    }


}
