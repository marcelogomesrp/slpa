/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import lavor.entidade.SituacaoPeca;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("situacaoPecaBB")
@Scope("request")
public class SituacaoPecaBB {
    List<SelectItem> situacoesPeca;

    public SituacaoPecaBB() {
         this.situacoesPeca = new ArrayList<SelectItem>();
         situacoesPeca.add(new SelectItem(SituacaoPeca.Ativo));
         situacoesPeca.add(new SelectItem(SituacaoPeca.Consultar));
         situacoesPeca.add(new SelectItem(SituacaoPeca.Inativo));

         //estados.add(new SelectItem(Estado.TO));
    }

    public List<SelectItem> getSituacoesPeca() {
        return situacoesPeca;
    }

    public void setSituacoesPeca(List<SelectItem> situacoesPeca) {
        this.situacoesPeca = situacoesPeca;
    }





}
