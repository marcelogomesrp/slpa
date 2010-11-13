/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.controller;

import br.com.guaraba.modelo.Arquivo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.primefaces.event.FileUploadEvent;

import br.com.guaraba.facade.PecaFacade;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.swing.JOptionPane;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author marcelo
 */
@ManagedBean
@SessionScoped
public class FileUploadController implements Serializable{
    @EJB
    private PecaFacade pecaFacade;



    /** Creates a new instance of FileUploadController */
    public FileUploadController() {
    }

    public void fileUploadAction(FileUploadEvent event) {
         //JOptionPane.showMessageDialog(null, "Vou fazer o ulpload");
    }

     public void handleFileUpload(FileUploadEvent event) {
//         System.err.print("aqui tem um errooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
//         //JOptionPane.showMessageDialog(null, "Vou fazer o ulpload");
        try {

//            Logger.getLogger("Este eh um log testeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            UploadedFile uuf = event.getFile();
            Date date = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd_MM_yyyy");
            File path = new File("./lista/");
            if(!path.exists()){
                path.mkdir();
            }
            File arquivo = new File(path.getPath() + "/" +formato.format(date) +".txt");
            FileOutputStream fos = new FileOutputStream(arquivo);
            fos.write(event.getFile().getContents());
            fos.flush();
            fos.close();
            this.Parser(arquivo);
        } catch (IOException ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage() + ex.getCause());
            Logger.getLogger(FileUploadController.class.getName()).log(Level.INFO, "Esta vendo este logooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
            Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

     private void Parser(File file){
         //JOptionPane.showMessageDialog(null, "Vou fazer parser");
         String SQL_limpa_importacao       = "DELETE FROM peca_importacao;";
         String SQL_limpa_item_novo        = "DELETE FROM peca_novo;";
         String SQL_limpa_item_existente   = "DELETE FROM peca_existente;";
         String SQL_novo        = "INSERT INTO peca_novo (select * from peca_importacao where codigo_produto not in (select codigo_produto from peca));";
         String SQL_existente   = "INSERT INTO peca_existente  (select * from peca_importacao where codigo_produto in (select codigo_produto from peca));";
        try {
            Arquivo arquivo = new Arquivo(file.getAbsolutePath(), 161);
            StringBuilder valor = new StringBuilder();
            StringBuilder custo = new StringBuilder();
            StringBuilder linha = new StringBuilder();
            valor.append("INSERT INTO peca_importacao (codigo_produto, codigo_limpo, codigo_use, descricao, unidade_medida, situacao, valor, ipi) values ");
            for(int x = 0; x<=arquivo.getTotalLinhasArquivo(); x++){
                linha.setLength(0);
                custo.setLength(0);
                linha.append(arquivo.getLinha(x));
                if(linha.substring(0,2).equals("01")){
                    valor.append("('");
                    valor.append(linha.substring(3, 23).replace(" ", ""));
                    valor.append("','");
                    //String codigo = linha.substring(3, 23).replace(" ", "");
                    //codigo.replace(".", "");
                    valor.append(linha.substring(3, 23).replace(" ", "").toUpperCase().replace(".", ""));
                    valor.append("','");
                    valor.append(linha.substring(24, 44).replace(" ", ""));
                    valor.append("','");
                    valor.append(linha.substring(45, 95).replace("'", " "));
                    valor.append("','");
                    valor.append(linha.substring(96, 99).replace("'", " "));
                    valor.append("','");
                    if(linha.length()==160){
                        valor.append(linha.substring(157,159).replace(" ", ""));
                    }else{
                        valor.append(linha.length());
                    }
                    valor.append("',");
                    custo.append(linha.substring(121, 132));
                    custo.insert(6, ".");
                    valor.append(custo);
                    valor.append(",");
                    valor.append(linha.substring(154,156));
                    valor.append("), \n");
                }
            }
            valor.deleteCharAt(valor.length() -3);
            valor.append(";");
            pecaFacade.RunNativeSQL(SQL_limpa_importacao);
            pecaFacade.RunNativeSQL(SQL_limpa_item_existente);
            pecaFacade.RunNativeSQL(SQL_limpa_item_novo);
            pecaFacade.RunNativeSQL(valor.toString());
            pecaFacade.RunNativeSQL(SQL_novo);
            pecaFacade.RunNativeSQL(SQL_existente);


            arquivo.Finalizar();
        } catch (IOException ex) {
           // JOptionPane.showMessageDialog(null, "error " + ex.getMessage());
        }
     }
}
