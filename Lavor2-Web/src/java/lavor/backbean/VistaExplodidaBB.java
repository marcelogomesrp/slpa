/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lavor.backbean;

import java.io.File;
import java.io.Serializable;
import java.util.StringTokenizer;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;
import javax.swing.JOptionPane;
import lavor.entidade.Equipamento;
import lavor.managedBean.EquipamentoMB;
import org.apache.catalina.connector.Request;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */
@Controller("vistaExplodidaBB")
@Scope("request")
public class VistaExplodidaBB implements Serializable {

    private String nome;
    @Resource
    private EquipamentoMB equipamentoMB;
    @Resource
    private LinhaBB linhaBB;

    public VistaExplodidaBB() {
        this.nome = "Selecione o equipamento e informe o local do arquivo";
    }

    public String DoNovoPage() {
        this.equipamentoMB.setEquipamento(new Equipamento());
        this.linhaBB.TodasAsLinhas();
        this.equipamentoMB.setEquipamentos(new ListDataModel());
        return "/vista/novo";
    }

    public void UploadComAjax(UploadEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
        String realPath = sc.getRealPath("/");
        UploadItem item = event.getUploadItem();
        File file = item.getFile();
        String filePathName = item.getFileName();
        String fileName = equipamentoMB.getEquipamento().getId().toString().concat(".pdf");
//        StringTokenizer st = new StringTokenizer(filePathName, "\\");
//        while(st.hasMoreElements()){
//            fileName = st.nextToken();
//        }
        //realPath = realPath + "upload//" + fileName;
        realPath = realPath + "vista/" + fileName;
        //JOptionPane.showMessageDialog(null, realPath);
        file.renameTo(new File(realPath));

    }


    public String DoListarVistaPage(){
        this.linhaBB.TodasAsLinhas();
        return "/vista/equipamentolistar";
    }


    public String Troca() {
        this.nome = "Arquivo enviado com sucesso";
        return "ok";
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
