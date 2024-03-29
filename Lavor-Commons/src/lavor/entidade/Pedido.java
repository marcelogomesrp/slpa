/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.entidade;

import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name="pedido")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="postodeatendimento_id", referencedColumnName="id")
    private PostoDeAtendimento postoDeAtendimento;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDaSolicitacao;
    @OneToMany(mappedBy = "idPedido", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<PedidoItem> itensPedido;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Transient
    private Float valorTotal;
    private String mensagem;
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Cliente cliente;
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Fornecedor fornecedor;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDaCompra;
    private Boolean garantia;
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Servico garantiaSevico;
    @Transient
    private Integer quantidadeDeItens;

    public Integer getQuantidadeDeItens() {
        this.quantidadeDeItens = itensPedido.size();
        return quantidadeDeItens;
    }

    public void setQuantidadeDeItens(Integer quantidadeDeItens) {
        this.quantidadeDeItens = quantidadeDeItens;
    }

   

    

    public Float getValorTotal() {
        valorTotal = 0F;
        for(PedidoItem item:itensPedido){
            valorTotal += item.getValorTotal();
        }
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getGarantiaSevico() {
        return garantiaSevico;
    }

    public void setGarantiaSevico(Servico garantiaSevico) {
        this.garantiaSevico = garantiaSevico;
    }





    public Pedido() {        
        this.itensPedido        = new ArrayList<PedidoItem>();
        this.status             = status.Cadastrado;
        this.fornecedor         = new Fornecedor();
        this.garantia           = Boolean.FALSE;
        this.garantiaSevico     = new Servico();
        this.dataDaSolicitacao  = new Date();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataDaSolicitacao() {
        return dataDaSolicitacao;
    }

    public void setDataDaSolicitacao(Date dataDaSolicitacao) {
        this.dataDaSolicitacao = dataDaSolicitacao;
    }

    public List<PedidoItem> getItensPedido() {
        if(itensPedido == null){
            itensPedido = new ArrayList<PedidoItem>();
        }
        return itensPedido;
    }

    public void setItensPedido(List<PedidoItem> itensPedido) {
        for(PedidoItem pedido:itensPedido){
            pedido.setIdPedido(this);
        }
        this.itensPedido = itensPedido;
    }

    public PostoDeAtendimento getPostoDeAtendimento() {
        return postoDeAtendimento;
    }

    public void setPostoDeAtendimento(PostoDeAtendimento postoDeAtendimento) {
        this.postoDeAtendimento = postoDeAtendimento;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataDaCompra() {
        return dataDaCompra;
    }

    public void setDataDaCompra(Date dataDaCompra) {
        this.dataDaCompra = dataDaCompra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Boolean getGarantia() {
        return garantia;
    }

    public void setGarantia(Boolean garantia) {
        this.garantia = garantia;
    }

    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lavor.entidade.Pedido[id=" + id + "]";
    }

}
