/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.backbean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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
import lavor.service.ItemPedidoService;
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
    @Resource
    private ItemPedidoService itemPedidoService;

    
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

    public String DoListarPedidoDOPostoPage(){
        List<Pedido> pedidos = this.pedidoService.PesquisarPedidoPorPostoESituacao(postoDeAtendimentoMB.getPostoDeAtendimento(), Situacao.Cadastrado);
        this.pedidoMB.setPedidos(new ListDataModel(pedidos));
        return "/pedido/listarPedidos";
    }

    public String DoListarResumoPage(){
        List<Pedido> pedidos = this.pedidoService.PesquisarPedidoPorSituacao(Situacao.Cadastrado);
        this.pedidoMB.setPedidos(new ListDataModel(pedidos));
        return "/pedido/resumo";
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
                itemPedido.setQuantidade(peca.getQuantidadeMaxima());
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

    private static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        is.close();
        return bytes;
    }


    public String Exportar() {
        try {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        System.out.println("Posto: " + postoDeAtendimentoMB.getPostoDeAtendimento());
        pedidos = this.pedidoService.PesquisarPedidoPorSituacao(Situacao.Cadastrado);
        String filename = "Arquivo.txt";
        //String filename = new Date().toString();
        File arquivo = new File(filename);
        FileOutputStream fos;
        
        fos = new FileOutputStream(arquivo);
        for(Pedido pedido:pedidos){
            fos.write((pedido.getPostoDeAtendimento().getId() + "," ).getBytes());
            fos.write((pedido.getPostoDeAtendimento().getRazaoSocial() + "," ).getBytes());
            fos.write((pedido.getCliente().getId()+ "," ).getBytes());
            fos.write((pedido.getCliente().getNome()+ "\n" ).getBytes());
        }
            fos.close();

        byte[] pdf = PedidoBB.getBytesFromFile(new File(filename));
        FileInputStream filee = new FileInputStream(filename);
        InputStreamReader reader = new InputStreamReader(filee);
        BufferedReader br = new BufferedReader(reader);
        FacesContext faces = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) faces.getExternalContext().getResponse();
        response.setContentType("application/force-download");
        response.setHeader("Content-disposition", "inline; filename=\"" + filename + "\"");

            ServletOutputStream out;
            out = response.getOutputStream();
            out.write(pdf);

faces.responseComplete();

        } catch (IOException ex) {
            Logger.getLogger(PedidoBB.class.getName()).log(Level.SEVERE, null, ex);
        } 

        return "sucesso";
    }

    public String DoDetalhePage(){
        this.pedidoMB.setPedido((Pedido) pedidoMB.getPedidos().getRowData());
        List<ItemPedido> itens = itemPedidoService.PesquisarPorPedido(pedidoMB.getPedido());
        this.pedidoMB.setItemPedido(new ListDataModel(itens));
        return "/pedido/detalhes";
        
    }



}
