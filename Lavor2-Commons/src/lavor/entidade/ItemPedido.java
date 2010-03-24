/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.entidade;

import static javax.persistence.GenerationType.IDENTITY;


import java.io.Serializable;
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
@Table(name="item_pedido")
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    @OneToOne()
    //@ManyToOne
    @JoinColumn(name="id_pedido")
    private Pedido pedido;
    @OneToOne()
    @JoinColumn(name="id_peca")
    private Peca peca;
    private Float valor;

    public ItemPedido() {
        this.peca   = new Peca();
        this.pedido = new Pedido();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
        if (!(object instanceof ItemPedido)) {
            return false;
        }
        ItemPedido other = (ItemPedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lavor.entidade.ItemPedido[id=" + id + "]";
    }

}
