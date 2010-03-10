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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name="pedido")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_posto_de_atendimento")
    private PostoDeAtendimento postoDeAtendimento;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name="id_revenda")
    private Revenda revenda;
    @OneToOne
    @JoinColumn(name="id_equipamento_cliente")
    private EquipamentoCliente equipamentoCliente;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDoPedido;
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
    @Column(name="valor_total")
    private Float valorTotal;

    

    public Pedido() {
        this.postoDeAtendimento = new PostoDeAtendimento();
        this.cliente            = new Cliente();
        this.revenda            = new Revenda();
        this.equipamentoCliente = new EquipamentoCliente();
        this.dataDoPedido       = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataDoPedido() {
        return dataDoPedido;
    }

    public void setDataDoPedido(Date dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }

    public EquipamentoCliente getEquipamentoCliente() {
        return equipamentoCliente;
    }

    public void setEquipamentoCliente(EquipamentoCliente equipamentoCliente) {
        this.equipamentoCliente = equipamentoCliente;
    }

    public PostoDeAtendimento getPostoDeAtendimento() {
        return postoDeAtendimento;
    }

    public void setPostoDeAtendimento(PostoDeAtendimento postoDeAtendimento) {
        this.postoDeAtendimento = postoDeAtendimento;
    }

    public Revenda getRevenda() {
        return revenda;
    }

    public void setRevenda(Revenda revenda) {
        this.revenda = revenda;
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lavor.entidade.Pedido[id=" + id + "]";
    }

}
