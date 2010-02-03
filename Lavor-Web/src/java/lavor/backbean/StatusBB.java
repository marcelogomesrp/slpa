/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import lavor.entidade.Status;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Marcelo
 */
@Controller("statusBB")
@Scope("request")
public class StatusBB {
    List<SelectItem> status;

    public StatusBB() {
        this.status = new ArrayList<SelectItem>();

        status.add(new SelectItem(Status.Cadastrado));
        status.add(new SelectItem(Status.Cancelado));
        status.add(new SelectItem(Status.Finalizado));
        status.add(new SelectItem(Status.Processado));

    }

    public List<SelectItem> getStatus() {
        return status;
    }

    public void setStatus(List<SelectItem> status) {
        this.status = status;
    }





}
