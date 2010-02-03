/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Marcelo
 */
@Controller("anoBB")
@Scope("request")
public class AnoBB {

    List<SelectItem> meses;

    public AnoBB() {
        this.meses = new ArrayList<SelectItem>();

        meses.add(new SelectItem(1,"janeiro"));
        meses.add(new SelectItem(2,"fevereiro"));
        meses.add(new SelectItem(3,"março"));
        meses.add(new SelectItem(4,"abril"));
        meses.add(new SelectItem(5,"maio"));
        meses.add(new SelectItem(6,"junho"));
        meses.add(new SelectItem(7,"julho"));
        meses.add(new SelectItem(8,"agosto"));
        meses.add(new SelectItem(9,"setembro"));
        meses.add(new SelectItem(10,"outubro"));
        meses.add(new SelectItem(11,"novembro"));
        meses.add(new SelectItem(12,"dezembro"));

    }

    public List<SelectItem> getMeses() {
        return meses;
    }

    public void setMeses(List<SelectItem> meses) {
        this.meses = meses;
    }




}
