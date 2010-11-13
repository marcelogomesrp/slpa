/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.modelo;

import br.com.guaraba.modelo.Peca;
import br.com.guaraba.modelo.PedidoPeca;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "item_pedido_peca", catalog = "lavor", schema = "")

public class ItemPedidoPeca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "ipi", precision = 12)
    private Long ipi;
    @Column(name = "valorTotal")
    private BigDecimal valorTotal;
    @Column(name = "valorUnitario")
    private BigDecimal valorUnitario;
    @Column(name = "quantidade", precision = 12)
    private Long quantidade;
    //@JoinColumn(name="listaItemPedidoPeca")
    @ManyToOne(cascade=CascadeType.ALL)
    private PedidoPeca pedidoPeca;
    @OneToOne
    private Peca peca;

    public ItemPedidoPeca() {

    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public PedidoPeca getPedidoPeca() {
        return pedidoPeca;
    }

    public void setPedidoPeca(PedidoPeca pedidoPeca) {
        this.pedidoPeca = pedidoPeca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIpi() {
        return ipi;
    }

    public void setIpi(Long ipi) {
        this.ipi = ipi;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }


}
