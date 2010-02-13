/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavorcommons;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import lavor.entidade.Pedido;
import lavor.service.PedidoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author marcelo
 * acesso tecmac-al@bol.com.br
 * 123456
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//        PostoDeAtendimento postoDeAtendimento = new PostoDeAtendimento();
//        postoDeAtendimento.setNome("Marcelo");
//        System.out.println("Posto de atendimento " + postoDeAtendimento.getNome() ) ;
//        PostoDeAtendimentoDao postoDeAtendimentoDao = new PostoDeAtendimentoDaoImp();
//        postoDeAtendimentoDao.salvar(postoDeAtendimento);
//        System.out.println("Posto Salvo com sucesso");

        
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        PostoDeAtendimento pa = (PostoDeAtendimento) context.getBean("postoDeAtendimento");
//        pa.setNome("ok spring");
//        System.out.println("Nome " + pa.getNome());

        
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PedidoService pe = (PedidoService) context.getBean("pedidoService");
        List<Pedido> pedidos = pe.LocalizarTodosOsPedidos();

        for(Pedido p:pedidos){
            System.out.println("Pedido id: " + p.getId());
            try{
                System.out.println("\tCliente: " + p.getCliente().getId());
                System.out.println("\tCliente nome: " + p.getCliente().getNome());
            }catch(Exception e){
                System.err.println("ERRO: " + e.getMessage() + e.getCause());
            }
        }

//        for(int x = 0; x < 10000; x++){
//            PostoDeAtendimentoService pa = (PostoDeAtendimentoService) context.getBean("postoDeAtendimentoService");
//            PostoDeAtendimento postoDeAtendimento = new PostoDeAtendimento();
//            postoDeAtendimento.setNome("Salvo com o hibernate + c3p0 n" + x);
//            pa.Salvar(postoDeAtendimento);
//        }

//        ServicoService ss = (ServicoService) context.getBean("servicoService");
//        Servico s = new Servico();
//        s.setNome("Abre motor");
//        s.setValor(9F);
//        ss.SalvarServico(s);

        
//        ClienteService cs = (ClienteService) context.getBean("clienteService");
//        for(Cliente cliente:cs.BuscarClientePorNome("teste%")){
//            System.out.println(cliente.getNome());
//        }

//
//        Categoria cat = new Categoria("nova categoria", "cheio de equipamento", Boolean.TRUE);
//        PecaService pecaService = (PecaService) context.getBean("pecaService");
//        EquipamentoService es = (EquipamentoService) context.getBean("equipamentoService");
//        //for(int i = 0 ; i < 100; i++){
//            Equipamento equipamento = new Equipamento();
//            equipamento.setNome("Equipamento power");
//            equipamento.setCategoria(cat);
//            List<Peca> pecas = new ArrayList<Peca>();
//            for(Peca peca:pecaService.BuscarTodasAsPecas()){
//                pecas.add(peca);
//            }
//            es.Salvar(equipamento);
            
        //}
//        ClienteService cs = (ClienteService) context.getBean("clienteService");
//        PedidoService ps = (PedidoService) context.getBean("pedidoService");
//        Pedido pedido = new Pedido();
//        Cliente cliente = new Cliente();
//        cliente.setNome("Cliente criado pelo pedido");
//        pedido.setDataDaSolicitacao(new Date());
//        pedido.setCliente(cliente);
//        pedido.setMensagem("Pedido criado com cliente");
//        ps.SalvarPedido(pedido);


//        for(int x = 0; x < 10; x++){
//        Cliente cli = new Cliente();
//        //cl.setId(1L);
//        cli.setNome("teste c = " + x);
//        cs.Salvar(cli);
//        //Main.persist(cli);
//        }
//        org.apache.log4j.Logger log = org.apache.log4j.Logger.getRootLogger();
//        JOptionPane.showMessageDialog(null, "Log esta " + log.getLevel().toString());
//        log.debug("mensagem debug ***************************************************************************************************************************");
//        log.error("mensagem erro ***************************************************************************************************************************");

//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        PostoDeAtendimentoService postoDeAtendimentoService = (PostoDeAtendimentoService) context.getBean("postoDeAtendimentoService");
//
//        PostoDeAtendimento pa = postoDeAtendimentoService.BuscarPorEmailESenha("marcelo", "admin...");
//        if(pa == null){
//            System.out.println("isso");
//        }

//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        PedidoService pedidoService = (PedidoService) context.getBean("pedidoService");
//        PostoDeAtendimentoService postoDeAtendimentoService =  (PostoDeAtendimentoService) context.getBean("postoDeAtendimentoService");
//        PecaService pecaService =  (PecaService) context.getBean("pecaService");
//        Pedido pedido = new Pedido();
//        pedido.setDataDaSolicitacao(new Date());
//        pedido.setPostoDeAtendimento(postoDeAtendimentoService.BuscarPorEmailESenha("marcelo", "admin..."));
//
//        System.out.println("Posto " + pedido.getPostoDeAtendimento().getNome());
//        List<PedidoItem> lista = new ArrayList<PedidoItem>();
//        for(Long x = 1L ; x < 4; x++){
//            PedidoItem itemPedido = new PedidoItem();
//            itemPedido.setPeca(pecaService.BuscarPecaPorId(x));
//            lista.add(itemPedido);
//        }
//        pedido.setItensPedido(lista);
////        for(ItemPedido it:pedido.getItensPedido()){
////            it.setPedido(pedido);
////        }
//        Pedido ped = pedidoService.SalvarPedido(pedido);
//       // System.out.println(ped.getId());

    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lavor-CommonsPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }




}
