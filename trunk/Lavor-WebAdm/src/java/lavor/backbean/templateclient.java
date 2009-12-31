/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;




import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import lavor.entidade.Categoria;
import lavor.entidade.Equipamento;
import lavor.entidade.Peca;
import lavor.service.EquipamentoService;
import lavor.service.PecaService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("tt")
@Scope("request")
public class templateclient {
    @Resource
    EquipamentoService equipamentoService;
    @Resource
    PecaService pecaService;

    private String nome;

    public EquipamentoService getEquipamentoService() {
        return equipamentoService;
    }

    public void setEquipamentoService(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }




    public templateclient() {
        this.nome = "funcionou";
    }

    public String Valor(){
        Equipamento equipamento = new Equipamento();
        equipamento.setNome("equipamento teste");
        equipamento.setCategoria(new Categoria("cat add auto", "descricao", Boolean.TRUE));
        //Collection<Peca> pecas = new ArrayList<Peca>();

//        for(int x = 0; x < 20; x++){
//            Peca p = new Peca();
//            p.setNome("peca " + x);
//            //pecas.add(p);
//            equipamento.getPecas().add(p);
//        }
        equipamento.setPecas(pecaService.BuscarPecaPorNome("%"));
        

        //equipamento.setPecas(pecas);
        try {
            equipamentoService.Salvar(equipamento);
            //equipamentoService.Atualizar(equipamento);
            this.nome="OK";
        } catch (Exception ex) {
            this.nome = "erro " + ex.getMessage();
            Logger.getLogger(templateclient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ok";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
