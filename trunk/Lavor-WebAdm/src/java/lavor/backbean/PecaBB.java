/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import lavor.entidade.Peca;
import lavor.managedbean.PecaMB;
import lavor.service.PecaService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */
@Controller("pecaBB")
@Scope("request")
public class PecaBB implements Serializable {

    @Resource
    private PecaService pecaService;
    @Resource
    private PecaMB pecaMB;


    public PecaService getPecaService() {
        return pecaService;
    }

    public void setPecaService(PecaService pecaService) {
        this.pecaService = pecaService;
    }

    public PecaMB getPecaMB() {
        return pecaMB;
    }

    public void setPecaMB(PecaMB pecaMB) {
        this.pecaMB = pecaMB;
    }

    public PecaBB() {
    }


    public String SalvarPeca(){
        try {
            pecaService.SalvarPeca(pecaMB.getPeca());
            lavor.util.FacesUtils.mensInfo("Peca salva com sucesso");
            pecaMB.setPeca(new Peca());
        } catch (Exception ex) {
            lavor.util.FacesUtils.mensErro("Erro ao salvar peca " + ex.getMessage());
            Logger.getLogger(PecaBB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucesso";
    }

}
