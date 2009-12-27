/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavorcommons;

import bit.entidade.PostoDeAtendimento;

/**
 *
 * @author marcelo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PostoDeAtendimento postoDeAtendimento = new PostoDeAtendimento();
        postoDeAtendimento.setNome("Marcelo");
        System.out.println("Posto de atendimento " + postoDeAtendimento.getNome() ) ;
    }

}
