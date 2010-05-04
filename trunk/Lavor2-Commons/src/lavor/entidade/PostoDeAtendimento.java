/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name="posto_de_atendimento")
public class PostoDeAtendimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    @Column(name="razao_social", length=60, nullable=false, unique=true)
    private String razaoSocial;
    @Column(name="cnpj", length=14, nullable=false, unique=true)
    private String cnpj;
    @Column(name="inscricao_estadual", length=14)
    private String inscricaoEstadual;
    @Column(name="nome_fantasia")
    private String nomeFantasia;
    @OneToOne()
    @JoinColumn(nullable=false, unique=true,name="usuario_id")
    private Usuario usuario;
    @Column(name="endereco", length=255)
    private String endereco;
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_cidade")
    private Cidade cidade;
    @Column(length=100)
    private String bairro;
    @Column(length=9)
    private String cep;
    @Column(length=10)
    private String telefone;
    @Column(length=10)
    private String celular;
    @Column(length=60, name="nome_contato")
    private String nomeContato;
    private String msn;
    private String skype;
    private String fax;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDeCadastro;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDeAtualizacao;
    private Boolean visivel;

    public PostoDeAtendimento() {
        this.usuario        = new Usuario();
        this.cidade         = new Cidade();
        this.dataDeCadastro = new Date();
        this.visivel        = Boolean.TRUE;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep.replaceAll("[^\\d{L}]", "");
        //this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj.replaceAll("[^\\d{L}]", "");
        //this.cnpj = cnpj;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular.replaceAll("[^\\d{L}]", "");
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }



    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone.replaceAll("[^\\d{L}]", "");
    }

    public Date getDataDeAtualizacao() {
        return dataDeAtualizacao;
    }

    public void setDataDeAtualizacao(Date dataDeAtualizacao) {
        this.dataDeAtualizacao = dataDeAtualizacao;
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Boolean getVisivel() {
        return visivel;
    }

    public void setVisivel(Boolean visivel) {
        this.visivel = visivel;
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
        if (!(object instanceof PostoDeAtendimento)) {
            return false;
        }
        PostoDeAtendimento other = (PostoDeAtendimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lavor.entidade.PostoDeAtendimento[id=" + id + "]";
    }

}
