/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.entidade;

import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author marcelo
 */
@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="postodeatendimento_id", referencedColumnName="id")
    private PostoDeAtendimento postoDeAtendimento;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDaSolicitacao;
    @OneToMany(mappedBy = "idPedido", cascade=CascadeType.ALL)
    private List<PedidoItem> itensPedido;


    //@OneToMany(mappedBy="item_pedido", cascade=CascadeType.ALL)
    //@JoinColumn(name="itemid", referencedColumnName="id")
    //private List<ItemPedido> itensPedido;




    public Pedido() {
        this.itensPedido = new ArrayList<PedidoItem>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataDaSolicitacao() {
        return dataDaSolicitacao;
    }

    public void setDataDaSolicitacao(Date dataDaSolicitacao) {
        this.dataDaSolicitacao = dataDaSolicitacao;
    }

    public List<PedidoItem> getItensPedido() {
        if(itensPedido == null){
            itensPedido = new ArrayList<PedidoItem>();
        }
        return itensPedido;
    }

    public void setItensPedido(List<PedidoItem> itensPedido) {
        for(PedidoItem pedido:itensPedido){
            pedido.setIdPedido(this);
        }
        this.itensPedido = itensPedido;
    }

    public PostoDeAtendimento getPostoDeAtendimento() {
        return postoDeAtendimento;
    }

    public void setPostoDeAtendimento(PostoDeAtendimento postoDeAtendimento) {
        this.postoDeAtendimento = postoDeAtendimento;
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