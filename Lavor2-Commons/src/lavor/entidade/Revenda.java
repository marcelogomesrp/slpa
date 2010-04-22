/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name="revenda")
public class Revenda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    @Column(name="cnpj", length=14, nullable=false)
    private String cnpj;
    @Column(name="razao_social", length=60, nullable=false)
    private String razaoSocial;
    @OneToOne()
    @JoinColumn(name="id_posto_de_atendimento")
    private PostoDeAtendimento postoDeAtendimento;

    public Revenda() {
        this.postoDeAtendimento = new PostoDeAtendimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj.replaceAll("[^\\d{L}]", "");
    }

    public PostoDeAtendimento getPostoDeAtendimento() {
        return postoDeAtendimento;
    }

    public void setPostoDeAtendimento(PostoDeAtendimento postoDeAtendimento) {
        this.postoDeAtendimento = postoDeAtendimento;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
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
        if (!(object instanceof Revenda)) {
            return false;
        }
        Revenda other = (Revenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lavor.entidade.LocalCompra[id=" + id + "]";
    }

}
