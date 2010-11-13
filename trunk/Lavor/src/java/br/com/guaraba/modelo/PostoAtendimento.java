/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marcelo
 */
@Entity
//@Table(name = "posto_atendimento", catalog = "lavor", schema = "")
@Table(name = "posto_atendimento", catalog = "lavor")
//@NamedQueries({
//    @NamedQuery(name = "PostoAtendimento.findAll", query = "SELECT p FROM PostoAtendimento p"),
//    @NamedQuery(name = "PostoAtendimento.findById", query = "SELECT p FROM PostoAtendimento p WHERE p.id = :id"),
//    @NamedQuery(name = "PostoAtendimento.findByDataAtualizacao", query = "SELECT p FROM PostoAtendimento p WHERE p.dataAtualizacao = :dataAtualizacao"),
//    @NamedQuery(name = "PostoAtendimento.findByDataCadastro", query = "SELECT p FROM PostoAtendimento p WHERE p.dataCadastro = :dataCadastro"),
//    @NamedQuery(name = "PostoAtendimento.findByAtivo", query = "SELECT p FROM PostoAtendimento p WHERE p.ativo = :ativo"),
//    @NamedQuery(name = "PostoAtendimento.findByBairro", query = "SELECT p FROM PostoAtendimento p WHERE p.bairro = :bairro"),
//    @NamedQuery(name = "PostoAtendimento.findByCelular", query = "SELECT p FROM PostoAtendimento p WHERE p.celular = :celular"),
//    @NamedQuery(name = "PostoAtendimento.findByCep", query = "SELECT p FROM PostoAtendimento p WHERE p.cep = :cep"),
//    @NamedQuery(name = "PostoAtendimento.findByCidade", query = "SELECT p FROM PostoAtendimento p WHERE p.cidade = :cidade"),
//    @NamedQuery(name = "PostoAtendimento.findByCnpj", query = "SELECT p FROM PostoAtendimento p WHERE p.cnpj = :cnpj"),
//    @NamedQuery(name = "PostoAtendimento.findByCodigo", query = "SELECT p FROM PostoAtendimento p WHERE p.codigo = :codigo"),
//    @NamedQuery(name = "PostoAtendimento.findByContato", query = "SELECT p FROM PostoAtendimento p WHERE p.contato = :contato"),
//    @NamedQuery(name = "PostoAtendimento.findByEmail", query = "SELECT p FROM PostoAtendimento p WHERE p.email = :email"),
//    @NamedQuery(name = "PostoAtendimento.findByEndereco", query = "SELECT p FROM PostoAtendimento p WHERE p.endereco = :endereco"),
//    @NamedQuery(name = "PostoAtendimento.findByEstado", query = "SELECT p FROM PostoAtendimento p WHERE p.estado = :estado"),
//    @NamedQuery(name = "PostoAtendimento.findByFax", query = "SELECT p FROM PostoAtendimento p WHERE p.fax = :fax"),
//    @NamedQuery(name = "PostoAtendimento.findByInscricaoEstadual", query = "SELECT p FROM PostoAtendimento p WHERE p.inscricaoEstadual = :inscricaoEstadual"),
//    @NamedQuery(name = "PostoAtendimento.findByMsn", query = "SELECT p FROM PostoAtendimento p WHERE p.msn = :msn"),
//    @NamedQuery(name = "PostoAtendimento.findByNomeFantasia", query = "SELECT p FROM PostoAtendimento p WHERE p.nomeFantasia = :nomeFantasia"),
//    @NamedQuery(name = "PostoAtendimento.findByRazaoSocial", query = "SELECT p FROM PostoAtendimento p WHERE p.razaoSocial = :razaoSocial"),
//    @NamedQuery(name = "PostoAtendimento.findBySenha", query = "SELECT p FROM PostoAtendimento p WHERE p.senha = :senha"),
//    @NamedQuery(name = "PostoAtendimento.findByTelefone", query = "SELECT p FROM PostoAtendimento p WHERE p.telefone = :telefone"),
//    @NamedQuery(name = "PostoAtendimento.findByUf", query = "SELECT p FROM PostoAtendimento p WHERE p.uf = :uf")})
public class PostoAtendimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "data_atualizacao")
    @Temporal(TemporalType.DATE)
    private Date dataAtualizacao;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Column(name = "ativo")
    private Boolean ativo;
    @Column(name = "bairro", length = 255)
    private String bairro;
    @Column(name = "celular", length = 255)
    private String celular;
    @Column(name = "cep", length = 255)
    private String cep;
    @Column(name = "cidade", length = 255)
    private String cidade;
    @Column(name = "cnpj", length = 255)
    private String cnpj;
    @Column(name = "codigo", length = 255)
    private String codigo;
    @Column(name = "contato", length = 255)
    private String contato;
    @Column(name = "email", length = 255)
    private String email;
    @Column(name = "endereco", length = 255)
    private String endereco;
    @Column(name = "estado", length = 255)
    private String estado;
    @Column(name = "fax", length = 255)
    private String fax;
    @Column(name = "inscricao_estadual", length = 255)
    private String inscricaoEstadual;
    @Column(name = "msn", length = 255)
    private String msn;
    @Column(name = "nome_fantasia", length = 255)
    private String nomeFantasia;
    @Column(name = "razao_social", length = 255)
    private String razaoSocial;
    @Column(name = "senha", length = 255)
    private String senha;
    @Column(name = "telefone", length = 255)
    private String telefone;
    @Column(name = "uf", length = 255)
    private String uf;

    public PostoAtendimento() {
    }

    public PostoAtendimento(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PostoAtendimento)) {
            return false;
        }
        PostoAtendimento other = (PostoAtendimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.guaraba.modelo.PostoAtendimento[id=" + id + "]";
    }

}
