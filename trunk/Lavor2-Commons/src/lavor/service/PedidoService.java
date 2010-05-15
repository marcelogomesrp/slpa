/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lavor.dao.PedidoDao;
import lavor.entidade.Cliente;
import lavor.entidade.EquipamentoCliente;
import lavor.entidade.Pedido;
import lavor.entidade.PostoDeAtendimento;
import lavor.entidade.Situacao;
import lavor.utils.GenericExceptionMessageType;

/**
 *
 * @author marcelo
 */
public class PedidoService implements Serializable{
    private PedidoDao pedidoDao;
    private ServiceException serviceException;
    private ClienteService clienteService;
    private RevendaService revendaService;
    private EquipamentoClienteService equipamentoClienteService;

    public PedidoService() {
        this.serviceException = new ServiceException();
    }

    public PedidoDao getPedidoDao() {
        return pedidoDao;
    }

    public void setPedidoDao(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    public Pedido SalvarPedidoPeca(Pedido pedido) throws ServiceException{
        this.serviceException = new ServiceException();
        try{
            //pedido.setEquipamentoCliente(new EquipamentoCliente());
            pedido.setEquipamentoCliente(null);
            pedido.setCliente(null);
            pedido = pedidoDao.salvarPedidoPeca(pedido);
            //pedido = pedidoDao.salvar(pedido);
        }catch(Exception ex){
            throw new ServiceException("Ocorreu um erro ao tentar salvar" + ex.getMessage() + ex.getCause(), ex);
        }

        return pedido;
    }
    public Pedido Salvar(Pedido pedido) throws ServiceException{
        this.serviceException = new ServiceException();
        try{
            if(this.PodeSerSalva(pedido)){
                // TODO: Warning - Verificar a revenda que esta sendo gravada
                if(pedido.getCliente().getId() == null){
                    pedido.setCliente(clienteService.Salvar(pedido.getCliente()));
                }
                if(pedido.getRevenda().getId() == null){
                    pedido.setRevenda(revendaService.Salvar(pedido.getRevenda()));
                }
                if(pedido.getEquipamentoCliente().getId() == null){
                    pedido.setEquipamentoCliente(equipamentoClienteService.Salvar(pedido.getEquipamentoCliente()));
                }
                pedido = this.pedidoDao.salvar(pedido);
            }
        }catch(Exception ex){
            throw new ServiceException("Ocorreu um erro ao tentar salvar" + ex.getMessage() + ex.getCause(), ex);
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

    public List<Pedido> PesquisarPedidoPorPosto(PostoDeAtendimento postoDeAtendimento) {
        return pedidoDao.pesquisarPorPosto(postoDeAtendimento);
    }

    public List<Pedido> PesquisarPedidoPorPostoESituacao(PostoDeAtendimento postoDeAtendimento, Situacao situacao) {
        return pedidoDao.pesquisarPorPostoESituacao(postoDeAtendimento, situacao);
    }

    public List<Pedido> PesquisarPedidoPorSituacao(Situacao situacao) {
        return pedidoDao.PesquisarPorSituacao(situacao);
    }

    public Pedido Atualizar(Pedido pedido) throws ServiceException{
        this.serviceException = new ServiceException();
        try{
            pedido = pedidoDao.atualizar(pedido);
        }catch(Exception ex){
            throw new ServiceException("Ocorreu um erro ao tentar salvar" + ex.getMessage() + ex.getCause(), ex);
        }
        return pedido;
    }

    public List<Pedido> PesquisarPedidoPorSituacaoEPrioridade(Situacao situacao, Boolean prioridade) {
        return pedidoDao.PesquisarPorSituacao(situacao, prioridade);
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public EquipamentoClienteService getEquipamentoClienteService() {
        return equipamentoClienteService;
    }

    public void setEquipamentoClienteService(EquipamentoClienteService equipamentoClienteService) {
        this.equipamentoClienteService = equipamentoClienteService;
    }

    public RevendaService getRevendaService() {
        return revendaService;
    }

    public void setRevendaService(RevendaService revendaService) {
        this.revendaService = revendaService;
    }

    public List<Pedido> PesquisarPedidoPorPostoEPeriodo(PostoDeAtendimento postoDeAtendimento, Date inicio, Date fim) {
        return pedidoDao.PesquisarPedidoPorPostoEPeriodo(postoDeAtendimento, inicio, fim);
    }

    public List<Pedido> PesquisarPedidoPorCliente(Cliente cliente) {
        return pedidoDao.PesquisarPedidoPorCliente(cliente);
    }

    public List<Pedido> PesquisarPedidoPorPeriodo(Date inicio, Date fim) {
        return pedidoDao.PesquisarPedidoPorPeriodo(inicio, fim);
    }



}
