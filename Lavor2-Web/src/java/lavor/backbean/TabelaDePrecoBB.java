/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.io.File;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */
@Controller("tabelaDePrecoBB")
@Scope("request")
public class TabelaDePrecoBB {
    private String nome;

    public TabelaDePrecoBB() {
        this.nome = "";
    }


    public void UploadComAjax(UploadEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
        String realPath = sc.getRealPath("/");
        UploadItem item = event.getUploadItem();
        File file = item.getFile();
        String filePathName = item.getFileName();
        String fileName = "tabela.pdf";
        realPath = realPath + "tabela/" + fileName;
        file.renameTo(new File(realPath));

    }


    public String DoNovoPage(){
        this.nome = "";
        return "/tabela/novo";

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String Troca() {
        this.nome = "Arquivo enviado com sucesso";
        return "ok";
    }

    public String DoDownloaPage(){
        return "/tabela/donwload";
    }

}
