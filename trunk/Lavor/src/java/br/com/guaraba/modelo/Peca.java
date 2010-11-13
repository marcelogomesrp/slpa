/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "peca", catalog = "lavor", schema = "")
//@NamedQueries({
//    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
//    @NamedQuery(name = "Item.findById", query = "SELECT i FROM Item i WHERE i.id = :id"),
//    @NamedQuery(name = "Item.findByCodigoProduto", query = "SELECT i FROM Item i WHERE i.codigoProduto = :codigoProduto"),
//    @NamedQuery(name = "Item.findByCodigoUse", query = "SELECT i FROM Item i WHERE i.codigoUse = :codigoUse"),
//    @NamedQuery(name = "Item.findByDescricao", query = "SELECT i FROM Item i WHERE i.descricao = :descricao"),
//    @NamedQuery(name = "Item.findByIpi", query = "SELECT i FROM Item i WHERE i.ipi = :ipi"),
//    @NamedQuery(name = "Item.findBySituacao", query = "SELECT i FROM Item i WHERE i.situacao = :situacao"),
//    @NamedQuery(name = "Item.findByUnidadeMedida", query = "SELECT i FROM Item i WHERE i.unidadeMedida = :unidadeMedida"),
//    @NamedQuery(name = "Item.findByValor", query = "SELECT i FROM Item i WHERE i.valor = :valor"),
//    @NamedQuery(name = "Item.findByCodigoLimpo", query = "SELECT i FROM Item i WHERE i.codigoLimpo = :codigoLimpo")})
public class Peca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "codigo_produto", length = 255)
    private String codigoProduto;
    @Column(name = "codigo_use", length = 255)
    private String codigoUse;
    @Column(name = "descricao", length = 255)
    private String descricao;
    @Column(name = "ipi", precision = 12)
    private Long ipi;
    @Column(name = "situacao", length = 255)
    private String situacao;
    @Column(name = "unidade_medida", length = 255)
    private String unidadeMedida;
    //@Column(name = "valor", precision = 12)
    @Column(name = "valor", precision=19, scale=4)
    private BigDecimal valor;
    @Column(name = "codigo_limpo", length = 255)
    private String codigoLimpo;

    public Peca() {
    }

    public Peca(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getCodigoUse() {
        return codigoUse;
    }

    public void setCodigoUse(String codigoUse) {
        this.codigoUse = codigoUse;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIpi() {
        return ipi;
    }

    public void setIpi(Long ipi) {
        this.ipi = ipi;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }



    public String getCodigoLimpo() {
        return codigoLimpo;
    }

    public void setCodigoLimpo(String codigoLimpo) {
        this.codigoLimpo = codigoLimpo;
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
        if (!(object instanceof Peca)) {
            return false;
        }
        Peca other = (Peca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.guaraba.modelo.Peca[id=" + id + "]";
    }

}
