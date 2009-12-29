/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;



import javax.swing.JOptionPane;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("tt")
@Scope("request")
public class templateclient {

    private String nome;

    public templateclient() {
        this.nome = "funcionou";
    }

    public String Valor(){
        //JOptionPane.showMessageDialog(null, "to retornoando ok");
        return "ok";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
