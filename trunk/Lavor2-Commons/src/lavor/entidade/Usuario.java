/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author marcelo
 * @version 1
 *
 */


@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    @Column(length=60, nullable=false, unique=true)
    private String email;
    @Column(length=32, nullable=false)
    private String senha;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="ultimo_login")
    private Date ultimoLogin;
    @Enumerated(EnumType.STRING)
    @Column(name="tipo_usuario",length=20, nullable=false)
    private TipoUsuario tipoUsuario;
    private Boolean ativo;

    public Usuario() {
    }

    public Usuario(Usuario usuario) throws Exception{
        try{
            this.id             = usuario.getId();
            this.ativo          = usuario.getAtivo();
            this.email          = usuario.getEmail();
            this.senha          = usuario.getSenha();
            this.tipoUsuario    = usuario.getTipoUsuario();
            this.ultimoLogin    = usuario.getUltimoLogin();
        }catch(NullPointerException ex){
            throw new Exception("O objeto usuario nao e valido");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws Exception {
        if(!lavor.utils.StringUtils.isNullOrEmpty(senha)){
            this.senha = lavor.utils.StringUtils.GerarMD5(senha);
        }
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        try{
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
            if ((this.id == null && other.id != null) ||  (this.id != null && !this.id.equals(other.id))) {
                return false;
            }
        }catch(Exception e){
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "lavor.entidade.Usuario[id=" + id + " email=" + email + "]";
    }

}
