/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.guaraba.facade;


import br.com.guaraba.modelo.Peca;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcelo
 */
@Stateless
public class PecaFacade extends AbstractFacade<Peca>{
    @PersistenceContext(unitName = "LavorPU")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public PecaFacade() {
        super(Peca.class);
    }

    public void ConfirmaArquivo(){
         String SQL_itemNovo        = "INSERT INTO peca (SELECT * FROM peca_novo);";
         String SQL_itemExistente   = "UPDATE peca INNER JOIN peca_existente ON peca.codigo_produto = peca_existente.codigo_produto SET peca.valor = peca_existente.valor, peca.codigo_use = peca_existente.codigo_use, peca.ipi = peca_existente.ipi, peca.situacao = peca_existente.situacao;";

         String SQL_limpa_importacao       = "DELETE FROM peca_importacao;";
         String SQL_limpa_item_novo        = "DELETE FROM peca_novo;";
         String SQL_limpa_item_existente   = "DELETE FROM peca_existente;";


         RunNativeSQL(SQL_itemNovo);
         RunNativeSQL(SQL_itemExistente);

         RunNativeSQL(SQL_limpa_importacao);
         RunNativeSQL(SQL_limpa_item_existente);
         RunNativeSQL(SQL_limpa_item_novo);
    }


    public Peca findByCodigo(String codigoProduto){
        String SQL = "SELECT p from Peca p  WHERE codigoProduto = :codigoProduto";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("codigoProduto", codigoProduto);
        List<Peca> listaPeca = findByParam(SQL, params);
        if(listaPeca.size() == 1  ){
            return listaPeca.get(0);
        }
        return new Peca();
    }
    

    public Peca findByCodigoLimpo(String codigoProduto){
        String codigoLimpo = codigoProduto.replace(".", "").replace(" ", "").toUpperCase();
        String SQL = "SELECT p from Peca p  WHERE codigoLimpo = :codigoLimpo";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("codigoLimpo", codigoLimpo);
        List<Peca> listaPeca = findByParam(SQL, params);
        if(listaPeca.size() == 1  ){
            return listaPeca.get(0);
        }
        return new Peca();
    }

}





