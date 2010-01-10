/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lavor.dao.PecaDao;
import lavor.entidade.Peca;

/**
 *
 * @author marcelo
 */
public class PecaService {
    @Resource
    private PecaDao pecaDao;

    public PecaDao getPecaDao() {
        return pecaDao;
    }

    public void setPecaDao(PecaDao pecaDao) {
        this.pecaDao = pecaDao;
    }

    public PecaService() {
    }

    public Peca SalvarPeca(Peca peca) throws Exception{
        PecaPodeSerSalva(peca);
        peca = this.pecaDao.salvar(peca);
        return peca;
    }

    public Peca AtualizarPeca(Peca peca){
        return pecaDao.atualizar(peca);
    }

    private void PecaPodeSerSalva(Peca peca) throws Exception{
        StringBuilder msg = new StringBuilder();
        if(peca.getNome().isEmpty()){
            msg.append("O nome da peca deve ser informado");
        }


        if(msg.length() > 0){
            throw new Exception(msg.toString());
        }
    }

    public List<Peca> BuscarPecaPorNome(String nome){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nome", nome);
        String SQL = "SELECT p FROM Peca p WHERE p.nome LIKE :nome";
        List<Peca> pecas = pecaDao.listPesqParam(SQL, params);
        return pecas;
    }

    public Peca BuscarPecaPorId(Long id){
        return pecaDao.pesquisarPorId(id);
    }

    public List<Peca> BuscarTodasAsPecas(){
        return pecaDao.todos();
    }




}
