/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guaraba.controller;

import br.com.guaraba.controller.util.JsfUtil;
import br.com.guaraba.controller.util.Tabela;
import br.com.guaraba.facade.PecaFacade;
import br.com.guaraba.modelo.ItemPedidoPeca;
import br.com.guaraba.facade.PedidoPecaFacade;
import br.com.guaraba.modelo.Peca;
import br.com.guaraba.modelo.PedidoPeca;
import br.com.guaraba.modelo.Situacao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author marcelo
 */
@ManagedBean
@SessionScoped
public class PedidoPecaController implements Serializable{

    @EJB
    PedidoPecaFacade pedidoPecaFacade;
    @EJB
    PecaFacade pecaFacade;
    @ManagedProperty(value = "#{postoAtendimentoController}")
    PostoAtendimentoController postoAtendimentoController;
    PedidoPeca pedidoPeca;
    Tabela<ItemPedidoPeca> listaItemPedido;
    private String codigo;
    private Long quantidade;
    private DataModel listPedido = null;
    private Integer paginacao;
    private int[] paginacaoRange;
    private Boolean anterior;
    private Boolean proximo;
    private int totalPedidoCadastrado;

    public String RemoverPeca() {
        ItemPedidoPeca itemPedidoPeca = listaItemPedido.getModelo().getRowData();
        listaItemPedido.Remove(itemPedidoPeca);
        BigDecimal valor = pedidoPeca.getValor();
        //valor = itemPedidoPeca.getValorTotal().subtract(valor);
        valor = valor.subtract(itemPedidoPeca.getValorTotal());
        pedidoPeca.setValor(valor);
        return "/posto/pedido_peca/New";
    }

    public String AdicionarPeca() {
        //Peca peca = pecaFacade.findByCodigo(codigo);
        Peca peca = pecaFacade.findByCodigoLimpo(codigo);
        if (peca.getId() != null) {
            if (peca.getCodigoUse().isEmpty()) {
                if (peca.getSituacao().equalsIgnoreCase("I")) {
                    JsfUtil.addErrorMessage("Peca inativa");
                } else {
                    if (quantidade > 0) {
                        ItemPedidoPeca item = new ItemPedidoPeca();
                        item.setPeca(peca);
                        item.setQuantidade(quantidade);
                        item.setIpi(peca.getIpi());
                        item.setValorUnitario(peca.getValor());                        
                        item.setValorTotal(item.getValorUnitario().multiply(BigDecimal.valueOf(quantidade)));
                        listaItemPedido.Adicionar(item);
                        if (this.pedidoPeca.getValor() == null) {
                            this.pedidoPeca.setValor(item.getValorTotal());
                            
                        }else{
                         //   BigDecimal valor = pedidoPeca.getValor();
                         //   valor.add(item.getValorTotal());
                         //   pedidoPeca.setValor(valor);
                            
                            this.pedidoPeca.setValor(this.pedidoPeca.getValor().add(item.getValorTotal()));
                        }
                        quantidade = 1L;
                        codigo = "";
                        item.setPedidoPeca(pedidoPeca);
                    } else {
                        JsfUtil.addErrorMessage("Quantidade informada inválida");
                    }
                }
            } else {
                JsfUtil.addErrorMessage("Produto modificado, use " + peca.getCodigoUse());
            }

        } else {
            JsfUtil.addErrorMessage("Peca não econtrada");
        }
        return "/posto/pedido_peca/New";
    }

    public String AdicionarPecaEditar(){
        this.AdicionarPeca();
        return "Editar";
    }

    public String PreparaNovoPedido() {
        this.pedidoPeca = new PedidoPeca();
        listaItemPedido = new Tabela(ItemPedidoPeca.class);
        quantidade = 1L;
        codigo = "";

        return "/posto/pedido_peca/New";
    }

    public String finalizarPedido() {
        pedidoPeca.setSituacao(Situacao.Aberto);
        pedidoPeca.setDataPedido(new Date());
        pedidoPeca.setPostoAtendimento(postoAtendimentoController.getPostoAtendimento());
        pedidoPeca.setListaItemPedidoPeca(listaItemPedido.getLista());
        pedidoPecaFacade.create(pedidoPeca);
        JsfUtil.addSuccessMessage("Pedido incluido com sucesso");
        return "Pedido_peca_demonstrativo";
    }

    public String finalizarModificacao() {        
        pedidoPeca.setSituacao(Situacao.Aberto);
        pedidoPeca.setDataPedido(new Date());
        pedidoPeca.setPostoAtendimento(postoAtendimentoController.getPostoAtendimento());
        pedidoPeca.setListaItemPedidoPeca(listaItemPedido.getLista());
        //pedidoPecaFacade.create(pedidoPeca);
        pedidoPecaFacade.edit(pedidoPeca);
        JsfUtil.addSuccessMessage("Pedido modificado com sucesso");
        return "Pedido_peca_demonstrativo";
    }

    /** Creates a new instance of PedidoPecaController */
    public PedidoPecaController() {
        paginacao = 20;
        pedidoPeca = new PedidoPeca();
        listaItemPedido = new Tabela(ItemPedidoPeca.class);
        //this.getPaginacaoRange();
    }

    public PedidoPeca getPedidoPeca() {
        return pedidoPeca;
    }

    public void setPedidoPeca(PedidoPeca pedidoPeca) {
        this.pedidoPeca = pedidoPeca;
    }

    public PedidoPecaFacade getPedidoPecaFacade() {
        return pedidoPecaFacade;
    }

    public void setPedidoPecaFacade(PedidoPecaFacade pedidoPecaFacade) {
        this.pedidoPecaFacade = pedidoPecaFacade;
    }

    public Tabela getListaItemPedido() {
        return listaItemPedido;
    }

    public void setListaItemPedido(Tabela listaItemPedido) {
        this.listaItemPedido = listaItemPedido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public int getTotalPedidoCadastrado() {
        return totalPedidoCadastrado;
    }

    public void setTotalPedidoCadastrado(int totalPedidoCadastrado) {
        this.totalPedidoCadastrado = totalPedidoCadastrado;
    }
    

    public PostoAtendimentoController getPostoAtendimentoController() {
        return postoAtendimentoController;
    }

    public void setPostoAtendimentoController(PostoAtendimentoController postoAtendimentoController) {
        this.postoAtendimentoController = postoAtendimentoController;
    }

    public DataModel getListPedido() {
        if (listPedido == null) {
            listPedido = new ListDataModel(pedidoPecaFacade.findRange(getPaginacaoRange()));
        }
        return listPedido;
    }

    public void AtualizarListPedido() {
        //listPedido = new ListDataModel(pedidoPecaFacade.findRange(getPaginacaoRange()));
        listPedido = new ListDataModel(pedidoPecaFacade.FindByPosto(postoAtendimentoController.getPostoAtendimento(), paginacaoRange));
    }

    public void setListPedido(DataModel listPedido) {
        this.listPedido = listPedido;
    }

    public Integer getPaginacao() {
        return paginacao;
    }

    public void setPaginacao(Integer paginacao) {
        this.paginacao = paginacao;
    }

    public int[] getPaginacaoRange() {
        if (paginacaoRange == null) {
            paginacaoRange = new int[2];
            paginacaoRange[0] = 0;
            paginacaoRange[1] = paginacao;
        }
        return paginacaoRange;
    }

    public void setPaginacaoRange(int[] paginacaoRange) {
        this.paginacaoRange = paginacaoRange;
    }

    public int getTotalRegistro() {
        return pedidoPecaFacade.count();
    }

    public Boolean getTemAnterior() {
        if (paginacaoRange[0] > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Boolean getTemProximo() {
        if (paginacaoRange[1] >= getTotalRegistro()) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }

    }

    public String Proximo() {
        if (this.getTemProximo()) {
            paginacaoRange[0] = paginacaoRange[1];
            paginacaoRange[1] = paginacaoRange[0] + paginacao;
            this.AtualizarListPedido();
        }
        return "List";
    }

    public String Anterior() {
        if (this.getTemAnterior()) {
            paginacaoRange[1] = paginacaoRange[0];
            paginacaoRange[0] = paginacaoRange[1] - paginacao;
            this.AtualizarListPedido();
        }
        return "List";
    }

    public String Detalhe() {
        pedidoPeca = (PedidoPeca) listPedido.getRowData();
        listaItemPedido.setLista(pedidoPeca.getListaItemPedidoPeca());
        listaItemPedido.Sincroniza();
        return "Pedido_peca_demonstrativo";
    }

    public String Editar() {
        pedidoPeca = (PedidoPeca) listPedido.getRowData();
        listaItemPedido.setLista(pedidoPeca.getListaItemPedidoPeca());
        listaItemPedido.Sincroniza();

        return "Editar";
    }

    public String Cancelar(){
        pedidoPeca.setSituacao(Situacao.Cancelado);
        pedidoPecaFacade.edit(pedidoPeca);
        JsfUtil.addSuccessMessage("Pedido cancelado com sucesso");
        return "Cancel";
    }


    public String DoListPedidoPage(){
        this.getPaginacaoRange();
        this.AtualizarListPedido();
        return "/posto/pedido_peca/List";
    }

    public String CancelarPedido(){
        pedidoPeca = (PedidoPeca) listPedido.getRowData();
        listaItemPedido.setLista(pedidoPeca.getListaItemPedidoPeca());
        listaItemPedido.Sincroniza();
        return "Cancel";
    }

    //////////////////////////////////admin //////////////////////////


    public String DoListPedidoAberto(){
        //listPedido = new ListDataModel(pedidoPecaFacade.FindByPosto(postoAtendimentoController.getPostoAtendimento(), paginacaoRange));
        
        listPedido = new ListDataModel(pedidoPecaFacade.FindBySituacao(Situacao.Aberto, getPaginacaoRange()));
        //listPedido = new ListDataModel();
        return "/adm/pedido/ListPedido";
        //return "ListPedido";
    }

    public String DoExportPage(){
        totalPedidoCadastrado = pedidoPecaFacade.countPedidoCadastrado();
        return "/adm/pedido/Export";
    }


    public String Exportar() {
        try {
            List<PedidoPeca> listaPedidoPeca = new ArrayList<PedidoPeca>();
            listaPedidoPeca = pedidoPecaFacade.FindBySituacao(Situacao.Aberto);
            String filename = "Export.txt";
            File arquivo = new File(filename);
            FileOutputStream fos;
            fos = new FileOutputStream(arquivo);
            for (PedidoPeca pedido : listaPedidoPeca) {
                fos.write(("ID pedido:" + pedido.getId().toString()).getBytes());
                fos.write(";IdPosto:".getBytes());
                fos.write(pedido.getPostoAtendimento().getId().toString().getBytes());
                //fos.write(";".getBytes());
                //fos.write(pedido.getPostoAtendimento().getRazaoSocial().getBytes());
                fos.write(";Valor:".getBytes());
                fos.write(pedido.getValor().toString().getBytes());
                for(ItemPedidoPeca itemPedidoPeca : pedido.getListaItemPedidoPeca()){
                    fos.write(";Codigopeca:".getBytes());
                    fos.write(itemPedidoPeca.getPeca().getCodigoProduto().toString().getBytes());
                    fos.write(";Qtd:".getBytes());
                    fos.write(itemPedidoPeca.getQuantidade().toString().getBytes());
                    fos.write(";Valor:".getBytes());
                    fos.write(itemPedidoPeca.getPeca().getValor().toString().getBytes());
                }

                fos.write("\n".getBytes());
                pedido.setSituacao(Situacao.Processado);
                pedidoPecaFacade.edit(pedido);
                //fos.write(pedidoPeca.getId().byteValue());
                //fos.write((pedidoPeca.getPostoAtendimento().getId()).toString().getBytes());
                //fos.write(pedidoPeca.getPostoAtendimento().getNomeFantasia().getBytes());
                
                //fos.write((pedido.getPostoAtendimento().getId())).getBytes());
                //fos.write((pedido.getPostoDeAtendimento().getId() + ",").getBytes());
                //fos.write((pedido.getPostoDeAtendimento().getRazaoSocial() + ",").getBytes());
                //fos.write((pedido.getCliente().getId() + ",").getBytes());
                //fos.write((pedido.getCliente().getNome() + "\n").getBytes());
            }
            fos.close();

            //byte[] pdf = PedidoBB.getBytesFromFile(new File(filename));
            byte[] pdf = PedidoPecaController.getBytesFromFile(new File(filename));
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

            totalPedidoCadastrado = 0;
            faces.responseComplete();

        } catch (IOException ex) {
            //Logger.getLogger(PedidoBB.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(PedidoPeca.class.getName()).log(Level.SEVERE, null, ex);
        }

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


}
