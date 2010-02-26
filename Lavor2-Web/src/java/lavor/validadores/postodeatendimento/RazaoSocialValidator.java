/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.validadores.postodeatendimento;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import lavor.entidade.PostoDeAtendimento;
import lavor.service.PostoDeAtendimentoService;
import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author marcelo
 */
public class RazaoSocialValidator implements Validator{


    


    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String erro = "";
        String razaoSocial = (String) o;

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PostoDeAtendimentoService postoDeAtendimentoService = (PostoDeAtendimentoService) context.getBean("postoDeAtendimentoService");

        PostoDeAtendimento postoDeAtendimento = postoDeAtendimentoService.pesquisarPorRazaoSocial(razaoSocial);
        try{
            if(postoDeAtendimento.getId()!= null){
                erro+="Razao social ja cadastrada";
            }
        }catch(Exception e){
            System.out.println("Erro razaoSocialValidator");
        }
        

        if(razaoSocial.length() > 60){
            erro += "A razao social deve ser menor que 60";
        }
        if(lavor.utils.StringUtils.isNullOrEmpty(razaoSocial)) {
            erro += "A razao social nao foi informada";
        }
        if(razaoSocial.equalsIgnoreCase("marcelo")){
            erro += "Razao social já cadastrada";
        }

        if(erro.length() > 0){
            FacesMessage message = new FacesMessage();
            message.setDetail("Razao social invalida");
            message.setSummary(erro);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

}
