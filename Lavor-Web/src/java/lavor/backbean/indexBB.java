/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lavor.backbean;

import javax.annotation.Resource;
import javax.swing.JOptionPane;
import lavor.entidade.PostoDeAtendimento;
import lavor.service.PostoDeAtendimentoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 * @Scope("session")
 */
@Controller("indexBB")
@Scope("request")
public class indexBB {

    private String nome;
    @Resource
    private PostoDeAtendimentoService postoDeAtendimentoService;
    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;

    public indexBB() {
        //this.postoDeAtendimento = new PostoDeAtendimento();
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

    public PostoDeAtendimentoMB getPostoDeAtendimentoMB() {
        return postoDeAtendimentoMB;
    }

    public void setPostoDeAtendimentoMB(PostoDeAtendimentoMB postoDeAtendimentoMB) {
        this.postoDeAtendimentoMB = postoDeAtendimentoMB;
    }

    

    public String SalvarPostoDeAtendimento(){
        org.apache.log4j.Logger log = org.apache.log4j.Logger.getRootLogger();
        //JOptionPane.showMessageDialog(null, "Log esta " + log.getLevel().toString());
        log.debug("mensagem debug ***************************************************************************************************************************");
        log.info("mensagem info ***************************************************************************************************************************");
        log.warn("mensagem warn ***************************************************************************************************************************");
        log.error("mensagem erro ***************************************************************************************************************************");
        log.fatal("mensagem fatal ***************************************************************************************************************************");




        try{
            this.postoDeAtendimentoService.Salvar(postoDeAtendimentoMB.getPostoDeAtendimento());
            this.postoDeAtendimentoMB.setPostoDeAtendimento(new PostoDeAtendimento());
            lavor.util.FacesUtils.mensInfo("Posto salvo com sucesso");

        }catch(Exception ex){
            lavor.util.FacesUtils.mensErro("Erro ao salvar \n" + ex.getMessage());
        }
        return "sucesso";
    }

}
