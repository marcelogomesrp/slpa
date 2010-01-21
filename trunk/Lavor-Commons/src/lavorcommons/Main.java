/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavorcommons;

import lavor.entidade.PostoDeAtendimento;
import lavor.service.PostoDeAtendimentoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author marcelo
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
        for(int x = 0; x < 10000; x++){
            PostoDeAtendimentoService pa = (PostoDeAtendimentoService) context.getBean("postoDeAtendimentoService");
            PostoDeAtendimento postoDeAtendimento = new PostoDeAtendimento();
            postoDeAtendimento.setNome("Salvo com o hibernate + c3p0 n" + x);
            pa.Salvar(postoDeAtendimento);
        }
        

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

}
