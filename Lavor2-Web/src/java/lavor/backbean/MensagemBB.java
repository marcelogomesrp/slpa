/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;


import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.entidade.Mensagem;
import lavor.managedBean.MensagemMB;
import lavor.service.MensagemService;
import lavor.utils.FacesUtils;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("mensagemBB")
@Scope("request")
public class MensagemBB {
   @Resource
   private MensagemMB mensagemMB;
   @Resource
   private MensagemService mensagemService;

    public MensagemBB() {

    }

    public String Salvar(){

        this.mensagemService.Salvar(this.mensagemMB.getMensagem());
        this.mensagemMB.setMensagem(new Mensagem());
        FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Mensagem salva com sucesso" );
        return "sucesso";
    }

    public String ListarTodos(){
        this.mensagemMB.setMensagens(new ListDataModel(this.mensagemService.Todas()));
        return "sucesso";
    }

    public String Excluir(){
        this.mensagemService.Excluir(this.mensagemMB.getMensagem());
        this.mensagemMB.setMensagem(new Mensagem());
        FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Mensagem excluidada com sucesso" );
        return "sucesso";
    }

    public String Atualizar(){
        this.mensagemService.Atualizar(this.mensagemMB.getMensagem());
        FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Mensagem atualizada com sucesso" );
        return "sucesso";
    }

    public String DoNovoMensagemPage(){
        this.mensagemMB.setMensagem(new Mensagem());
        return "/mensagem/novo";
    }

    public String DoListarMensagemPage(){
        this.mensagemMB.setMensagens(new ListDataModel(this.mensagemService.Todas()));
        return "/mensagem/listar";
    }

    public String DoEditarPage(){
        this.mensagemMB.setMensagem((Mensagem) mensagemMB.getMensagens().getRowData());
        return "/mensagem/editar";
    }

    public String DoExcluirPage(){
        this.mensagemMB.setMensagem((Mensagem) mensagemMB.getMensagens().getRowData());
        return "/mensagem/excluir";
    }

    public String DoListarMensagemPostoPage(){
        this.mensagemMB.setMensagens(new ListDataModel(this.mensagemService.Todas()));
        return "/mensagem/listar";
    }

    public String DoDetalhesPage(){
        this.mensagemMB.setMensagem((Mensagem) mensagemMB.getMensagens().getRowData());
        return "/mensagem/detalhes";
    }








}
