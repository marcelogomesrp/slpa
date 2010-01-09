/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lavor.backbean;

import javax.annotation.Resource;
import lavor.entidade.PostoDeAtendimento;
import lavor.managedbean.PostoDeAtendimentoMB;
import lavor.service.PostoDeAtendimentoService;
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
    private PostoDeAtendimentoService postoDeAtendimentoService;

    public PostoDeAtendimentoMB getPostoDeAtendimentoMB() {
        return postoDeAtendimentoMB;
    }

    public void setPostoDeAtendimentoMB(PostoDeAtendimentoMB postoDeAtendimentoMB) {
        this.postoDeAtendimentoMB = postoDeAtendimentoMB;
    }

    public PostoDeAtendimentoBB() {
    }

    public String Logar(){
        System.out.println("Login");
        PostoDeAtendimento po = postoDeAtendimentoService.BuscarPorEmailESenha(postoDeAtendimentoMB.getPostoDeAtendimento().getEmail(),postoDeAtendimentoMB.getPostoDeAtendimento().getSenha());
        if(po == null){
            System.out.println("falha");
        }else{
            System.out.println("sucesso");
        }
        return "sucesso";
    }
}
