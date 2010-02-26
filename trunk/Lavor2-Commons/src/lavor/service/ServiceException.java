/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.service;

import lavor.utils.GenericException;

/**
 *
 * @author marcelo
 */

public class ServiceException extends GenericException {

        private static final long serialVersionUID = 7993374458991329342L;

        public ServiceException() { }

        public ServiceException(Throwable cause) {

                super(cause);
        }

        public ServiceException(String message, Throwable cause) {

                super(message, cause);
        }
}
