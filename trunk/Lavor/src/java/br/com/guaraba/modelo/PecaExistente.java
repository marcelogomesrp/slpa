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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "peca_existente", catalog = "lavor", schema = "")
@NamedQueries({
    @NamedQuery(name = "PecaExistente.findAll", query = "SELECT p FROM PecaExistente p"),
    @NamedQuery(name = "PecaExistente.findById", query = "SELECT p FROM PecaExistente p WHERE p.id = :id"),
    @NamedQuery(name = "PecaExistente.findByCodigoProduto", query = "SELECT p FROM PecaExistente p WHERE p.codigoProduto = :codigoProduto"),
    @NamedQuery(name = "PecaExistente.findByCodigoUse", query = "SELECT p FROM PecaExistente p WHERE p.codigoUse = :codigoUse"),
    @NamedQuery(name = "PecaExistente.findByDescricao", query = "SELECT p FROM PecaExistente p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "PecaExistente.findByIpi", query = "SELECT p FROM PecaExistente p WHERE p.ipi = :ipi"),
    @NamedQuery(name = "PecaExistente.findBySituacao", query = "SELECT p FROM PecaExistente p WHERE p.situacao = :situacao"),
    @NamedQuery(name = "PecaExistente.findByUnidadeMedida", query = "SELECT p FROM PecaExistente p WHERE p.unidadeMedida = :unidadeMedida"),
    @NamedQuery(name = "PecaExistente.findByValor", query = "SELECT p FROM PecaExistente p WHERE p.valor = :valor"),
    @NamedQuery(name = "PecaExistente.findByCodigoLimpo", query = "SELECT p FROM PecaExistente p WHERE p.codigoLimpo = :codigoLimpo")})
public class PecaExistente implements Serializable {
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
    private Float ipi;
    @Column(name = "situacao", length = 255)
    private String situacao;
    @Column(name = "unidade_medida", length = 255)
    private String unidadeMedida;
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "codigo_limpo", length = 255)
    private String codigoLimpo;

    public PecaExistente() {
    }

    public PecaExistente(Long id) {
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

    public Float getIpi() {
        return ipi;
    }

    public void setIpi(Float ipi) {
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
        if (!(object instanceof PecaExistente)) {
            return false;
        }
        PecaExistente other = (PecaExistente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.guaraba.modelo.PecaExistente[id=" + id + "]";
    }

}
