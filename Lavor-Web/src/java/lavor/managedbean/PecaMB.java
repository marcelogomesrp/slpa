/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.managedbean;

import java.util.ArrayList;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import lavor.entidade.Peca;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("pecaMB")
@Scope("session")
public class PecaMB {
    private Peca peca;
    private ListDataModel listaDePecas;
    private ArrayList<Peca> pecas;

    public DataModel getListaDePecas() {
        return listaDePecas;
    }

    public void setListaDePecas(ListDataModel listaDePecas) {
        this.listaDePecas = listaDePecas;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }


    public PecaMB() {
        this.peca = new Peca();
        this.pecas = new ArrayList<Peca>();
    }

    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(ArrayList<Peca> pecas) {
        this.pecas = pecas;
    }





}
