/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.entidade;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author marcelo
 */
@Entity
public class Peca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String codigoImportacao;
    //@ManyToMany(mappedBy = "pecas")
    //private Collection<Equipamento> equipamentos;
    @ManyToMany(mappedBy = "pecas", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Equipamento> equipamentos;
    //DecimalFormat valor = new DecimalFormat("#,###.00");
    private Float valor;
    @OneToOne
    private Servico servico;

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    

    public Peca() {
        this.servico = new Servico();
        //equipamentos = new ArrayList<Equipamento>();
    }

    public String getCodigoImportacao() {
        return codigoImportacao;
    }

    public void setCodigoImportacao(String codigoImportacao) {
        this.codigoImportacao = codigoImportacao;
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
        return "lavor.entidade.Peca[id=" + id + "]";
    }

}
