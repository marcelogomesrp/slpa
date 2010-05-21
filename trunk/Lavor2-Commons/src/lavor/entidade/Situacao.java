/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.entidade;

/**
 *
 * @author marcelo
 */
public enum Situacao {
    Cadastrado, // Aberto
    Cancelado,
    Processado,
    Finalizado,
    Recusado,
    Erro,
    //Rejeitado, //Pedido com erro + recusado. o recusado é finalizador
}
