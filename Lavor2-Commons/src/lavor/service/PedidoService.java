/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.io.Serializable;
import java.util.Date;
import lavor.dao.PedidoDao;
import lavor.entidade.Pedido;
import lavor.entidade.Revenda;
import lavor.utils.GenericExceptionMessageType;

/**
 *
 * @author marcelo
 */
public class PedidoService implements Serializable{
    private PedidoDao pedidoDao;
    private ServiceException serviceException;

    public PedidoService() {
        this.serviceException = new ServiceException();
    }

    public PedidoDao getPedidoDao() {
        return pedidoDao;
    }

    public void setPedidoDao(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    public Pedido Salvar(Pedido pedido) throws ServiceException{
        this.serviceException = new ServiceException();
        try{
            if(this.PodeSerSalva(pedido)){
                // TODO: Warning - Verificar a revenda que esta sendo gravada
                pedido = this.pedidoDao.salvar(pedido);
            }
        }catch(Exception ex){
            throw new ServiceException("Ocorreu um erro ao tentar salvar", ex);
        }
        return pedido;
    }

    private boolean PodeSerSalva(Pedido pedido) throws ServiceException {
        this.DataValida(pedido.getDataDoPedido());

        if (serviceException.hasMessages()) {
            throw serviceException;
        }
        return true;
    }

    private void DataValida(Date dataDoPedido) {
        if(dataDoPedido == null){
            serviceException.addMessage(GenericExceptionMessageType.WARNING, "A data deve ser informada");
        }
    }



}
