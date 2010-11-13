/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "pedido_peca", catalog = "lavor", schema = "")
public class PedidoPeca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "data_pedido")
    @Temporal(TemporalType.DATE)
    private Date dataPedido;
    @Column(name = "valor", precision=19, scale=4)
    private BigDecimal valor;
    @OneToOne
    private PostoAtendimento postoAtendimento;
    @OneToMany(mappedBy = "pedidoPeca", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<ItemPedidoPeca> listaItemPedidoPeca;
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    public PedidoPeca() {
    }

    public PedidoPeca(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }



    public PostoAtendimento getPostoAtendimento() {
        return postoAtendimento;
    }

    public void setPostoAtendimento(PostoAtendimento postoAtendimento) {
        this.postoAtendimento = postoAtendimento;
    }

    public List<ItemPedidoPeca> getListaItemPedidoPeca() {
        return listaItemPedidoPeca;
    }

    public void setListaItemPedidoPeca(List<ItemPedidoPeca> listaItemPedidoPeca) {
        this.listaItemPedidoPeca = listaItemPedidoPeca;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
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
        if (!(object instanceof PedidoPeca)) {
            return false;
        }
        PedidoPeca other = (PedidoPeca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.guaraba.modelo.PedidoPeca[id=" + id + "]";
    }

}
