/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.entidade.EquipamentoCliente;
import lavor.entidade.Peca;
import lavor.managedBean.EquipamentoClienteMB;
import lavor.managedBean.PecaMB;
import lavor.managedBean.PedidoMB;
import lavor.service.PecaService;
import lavor.utils.FacesUtils;
import lavor.utils.GenericExceptionMessageType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author marcelo
 */

@Controller("pedidoBB")
@Scope("request")
public class PedidoBB {
    
    @Resource
    private PedidoMB pedidoMB;
    @Resource
    private EquipamentoClienteMB equipamentoClienteMB;
    @Resource
    private PecaMB pecaMB;

    @Resource
    private ClienteBB clienteBB;
    @Resource
    private EquipamentoBB equipamentoBB;

    @Resource
    private PecaService pecaService;

    public PedidoBB() {
    }

    public String DoNovoClientePage(){
        this.clienteBB.DoNovoClientePage();
        this.pedidoMB.setClienteSelecionado(Boolean.FALSE);
        this.pedidoMB.setEquipamentoSelecionado(Boolean.FALSE);
        return "/pedido/novo";
    }
    
    public String DoNovoEquipamentoPage(){
        if(this.pedidoMB.getClienteSelecionado()){
            // limpar equipamento cliste
            this.equipamentoClienteMB.setEquipamentoCliente(new EquipamentoCliente());
            this.equipamentoBB.DoPesquisarPage();
            return "/pedido/equipamentonovo";
        }else{
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.ERROR, "O cliente deve ser informado antes de continuar");
            return "/pedido/novo";
        }
    }

    public String DoNovoPedidoPage(){
        if(this.pedidoMB.getEquipamentoSelecionado()){
            List<Peca> pecas = pecaService.PesquisarPorEquipamento(equipamentoClienteMB.getEquipamentoCliente().getEquipamento());
            pecaMB.setPecas(new ListDataModel(pecas));
            return "/pedido/pedidonovo";
        }else{
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.ERROR, "O equipamento deve ser informado antes de continuar");
            return "/pedido/equipamentonovo";
        }

    }

    public String Salvar(){
        ListDataModel pecas =  pecaMB.getPecas();
        List<Peca> pecasSolicitada = new ArrayList<Peca>();
        for (int x = 0; x < pecas.getRowCount(); x++){
            pecas.setRowIndex(x);
            Peca peca = (Peca) pecas.getRowData();
            if(peca.getQuantidadeMaxima() > 0 ){
                pecasSolicitada.add(peca);
            }
        }
        this.pecaMB.setPecasSolicitada(pecasSolicitada);
        return "ok";
    }





}
