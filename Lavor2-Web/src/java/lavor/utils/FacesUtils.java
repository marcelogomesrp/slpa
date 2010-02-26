/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.utils;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import lavor.service.ServiceException;

/**
 *
 * @author marcelo
 */
public class FacesUtils {

        public static void adicionarMensagem(String msg, Exception e, String mensagemAmigavel) {

                if(e instanceof ServiceException) {

                        adicionarMensagem(msg, (GenericException)e, mensagemAmigavel);

                } else {

                        adicionarMensagem(msg, GenericExceptionMessageType.ERROR, mensagemAmigavel, e.getMessage());
                }
        }

        public static void adicionarMensagem(String msg, GenericException e, String mensagemAmigavel) {

                if(e.hasMessages()) {

                        for (GenericExceptionMessage mensagem : e.getMessages()) {

                                adicionarMensagem(msg, mensagem);
                        }

                } else {

                        adicionarMensagem(msg, GenericExceptionMessageType.ERROR, mensagemAmigavel, e.getMessage());
                }
        }

        public static void adicionarMensagem(String msg, GenericExceptionMessage mensagem) {

                adicionarMensagem(msg, mensagem.getType(), mensagem.getDescription(), mensagem.getDetail());
        }

        public static void adicionarMensagem(String msg, GenericExceptionMessageType tipo, String descricao) {

                adicionarMensagem(msg, tipo, descricao, null);
        }

        public static void adicionarMensagem(String msg, GenericExceptionMessageType tipo, String descricao, String detalhes) {

                Severity severity = getSeverityAdequadoParaOTipo(tipo);

                FacesContext.getCurrentInstance().addMessage(msg, new FacesMessage(severity, descricao, detalhes));
        }

        private static Severity getSeverityAdequadoParaOTipo(GenericExceptionMessageType tipo) {

                Severity severity = null;

                switch (tipo) {

                        case WARNING:
                                severity = FacesMessage.SEVERITY_WARN;
                                break;

                        case ERROR:
                                severity = FacesMessage.SEVERITY_ERROR;
                                break;

                        case INFO:
                                severity = FacesMessage.SEVERITY_INFO;
                                break;

                        default:
                                severity = FacesMessage.SEVERITY_ERROR;
                }

                return severity;
        }

        @SuppressWarnings("unchecked")
        public static <T> T getBean(String nomeDoBean){

        return (T) getFacesContext().getExternalContext().getSessionMap().get(nomeDoBean);
        }

        public static FacesContext getFacesContext() {

                return FacesContext.getCurrentInstance();
        }

        public static HttpSession getSession() {

                return (HttpSession)getFacesContext().getExternalContext().getSession(false);
        }

        public static ServletRequest getRequest() {

                return (ServletRequest)getFacesContext().getExternalContext().getRequest();
        }

        public static String getRequestParameter(String paramName) {

                return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(paramName);
        }
}
