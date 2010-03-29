/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.model.ListDataModel;
import lavor.entidade.EquipamentoCliente;
import lavor.entidade.ItemPedido;
import lavor.entidade.Peca;
import lavor.entidade.Pedido;
import lavor.entidade.Situacao;
import lavor.managedBean.ClienteMB;
import lavor.managedBean.EquipamentoClienteMB;
import lavor.managedBean.EquipamentoMB;
import lavor.managedBean.PecaMB;
import lavor.managedBean.PedidoMB;
import lavor.managedBean.PostoDeAtendimentoMB;
import lavor.managedBean.RevendaMB;
import lavor.service.EquipamentoClienteService;
import lavor.service.PecaService;
import lavor.service.PedidoService;
import lavor.service.ServiceException;
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
    private ClienteMB clienteMB;
    @Resource
    private PostoDeAtendimentoMB postoDeAtendimentoMB;
    @Resource
    private RevendaMB revendaMB;

    @Resource
    private EquipamentoMB equipamentoMB;

    @Resource
    private ClienteBB clienteBB;
    @Resource
    private EquipamentoBB equipamentoBB;
    @Resource
    private RevendaBB revendaBB;


    @Resource
    private PecaService pecaService;
    @Resource
    private PedidoService pedidoService;
    @Resource
    private EquipamentoClienteService equipamentoClienteService;

    
    public PedidoBB() {
    }

    public String DoNovoClientePage(){
        this.clienteBB.DoNovoClientePage();
        this.pedidoMB.setClienteSelecionado(Boolean.FALSE);
        this.pedidoMB.setEquipamentoSelecionado(Boolean.FALSE);
        this.equipamentoClienteMB.setEquipamentoCliente(new EquipamentoCliente());
        return "/pedido/novo";
    }
    
    public String DoNovoEquipamentoPage(){

        //clienteBB.SalvarEManter();


        if(this.pedidoMB.getClienteSelecionado()){
            // limpar equipamento cliste
            //mudei aqui em ...
            if(this.equipamentoClienteMB.getEquipamentoCliente().getId() == null){
                this.equipamentoClienteMB.setEquipamentoCliente(new EquipamentoCliente());
            }
            this.equipamentoBB.DoPesquisarPage();
            this.revendaBB.CriarRevendaSelectItem(postoDeAtendimentoMB.getPostoDeAtendimento());
            return "/pedido/equipamentonovo";
        }else{
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.ERROR, "O cliente deve ser informado antes de continuar");
            return "/pedido/novo";
        }
    }

    public String DoNovoPedidoPage(){

        try {
            EquipamentoCliente equipamentoCliente = equipamentoClienteMB.getEquipamentoCliente();
            equipamentoCliente.setEquipamento(equipamentoMB.getEquipamento());
            equipamentoClienteMB.setEquipamentoCliente(equipamentoClienteService.Salvar(equipamentoCliente));
            this.pedidoMB.setEquipamentoSelecionado(Boolean.TRUE);
            //FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Equipamento do cliente gravado com sucesso" );
        } catch (Exception ex) {
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar salvar.." + ex.getMessage());
        }


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

        Pedido pedido = new Pedido();

        for (int x = 0; x < pecas.getRowCount(); x++){
            pecas.setRowIndex(x);
            Peca peca = (Peca) pecas.getRowData();
            if(peca.getQuantidadeMaxima() > 0 ){
                pecasSolicitada.add(peca);
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setPeca(peca);
                itemPedido.setValor(peca.getValor());
                pedido.getItemPedido().add(itemPedido);
            }
        }
        this.pecaMB.setPecasSolicitada(pecasSolicitada);
        
        pedido.setCliente(clienteMB.getCliente());
        pedido.setDataDoPedido(new Date());
        pedido.setEquipamentoCliente(equipamentoClienteMB.getEquipamentoCliente());
        pedido.setPostoDeAtendimento(postoDeAtendimentoMB.getPostoDeAtendimento());
        pedido.setRevenda(revendaMB.getRevenda());
        pedido.setSituacao(Situacao.Cadastrado);

        try{
            pedidoService.Salvar(pedido);
            FacesUtils.adicionarMensagem("base_message", GenericExceptionMessageType.INFO, "Pedido salvo com sucesso" );
        }catch(ServiceException ex){
            FacesUtils.adicionarMensagem("base_message", ex, "Ocorreu uma falha ao tentar atualizar..");
        }
        return "ok";
    }

    public String NovaPesquisaDeCliente(){
        this.pedidoMB.setClienteSelecionado(Boolean.FALSE);        
        return "sucesso";
    }

}
