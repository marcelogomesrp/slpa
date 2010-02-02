/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author marcelo
 *
 * //@ManyToMany(mappedBy = "equipamentos")
    //@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    //@ManyToMany(mappedBy = "equipamentos",  fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    //@ManyToMany(mappedBy = "equipamentos")
 */
@Entity
public class Equipamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    @ManyToOne(cascade=CascadeType.ALL)
    private Categoria categoria;
    private String numeroDeSerie;
    private String numeroDaNotaFiscal;
    //@ManyToMany(cascade=CascadeType.ALL)
    //private Collection<Peca> pecas;
    @ManyToMany( fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private Collection<Peca> pecas;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(Collection<Peca> pecas) {
        this.pecas = pecas;
    }

    public String getNumeroDaNotaFiscal() {
        return numeroDaNotaFiscal;
    }

    public void setNumeroDaNotaFiscal(String numeroDaNotaFiscal) {
        this.numeroDaNotaFiscal = numeroDaNotaFiscal;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    

    public Equipamento() {
        this.pecas = new ArrayList<Peca>();
    }

    public Equipamento(Long id) {
        this.id = id;
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
        if (!(object instanceof Equipamento)) {
            return false;
        }
        Equipamento other = (Equipamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lavor.entidade.Equipamento[id=" + id + "]";
    }

}
