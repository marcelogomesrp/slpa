/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import lavor.entidade.Estado;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Controller;

/**
 *
 * @author Marcelo
 */

@Controller("estadosBB")
@Scope("request")
public class EstadosBB {
    List<SelectItem> estados;

    public EstadosBB() {
        this.estados = new ArrayList<SelectItem>();

        estados.add(new SelectItem(Estado.AC));
        estados.add(new SelectItem(Estado.AM));
        estados.add(new SelectItem(Estado.AP));
        estados.add(new SelectItem(Estado.BA));
        estados.add(new SelectItem(Estado.CE));
        estados.add(new SelectItem(Estado.DF));
        estados.add(new SelectItem(Estado.ES));
        estados.add(new SelectItem(Estado.GO));
        estados.add(new SelectItem(Estado.MA));
        estados.add(new SelectItem(Estado.MG));
        estados.add(new SelectItem(Estado.MS));
        estados.add(new SelectItem(Estado.MT));
        estados.add(new SelectItem(Estado.PA));
        estados.add(new SelectItem(Estado.PB));
        estados.add(new SelectItem(Estado.PE));
        estados.add(new SelectItem(Estado.PI));
        estados.add(new SelectItem(Estado.PR));
        estados.add(new SelectItem(Estado.RJ));
        estados.add(new SelectItem(Estado.RN));
        estados.add(new SelectItem(Estado.RO));
        estados.add(new SelectItem(Estado.RR));
        estados.add(new SelectItem(Estado.RS));
        estados.add(new SelectItem(Estado.SC));
        estados.add(new SelectItem(Estado.SE));
        estados.add(new SelectItem(Estado.SP));
        estados.add(new SelectItem(Estado.TO));

    }

    public List<SelectItem> getEstados() {
        return estados;
    }

    public void setEstados(List<SelectItem> estados) {
        this.estados = estados;
    }



}
