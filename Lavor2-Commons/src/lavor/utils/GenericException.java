/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcelo
 */

public class GenericException extends Exception {

        private List<GenericExceptionMessage> messages  = new ArrayList<GenericExceptionMessage>();

        private static final long serialVersionUID = -8105085494583488352L;

        public GenericException() {

                super();
                this.messages = new ArrayList<GenericExceptionMessage>();
        }

        public GenericException(String message) {

                super(message);
                this.addMessage(GenericExceptionMessageType.ERROR, message);
        }

        public GenericException(String message, Throwable cause) {

                super(message, cause);
                this.addMessage(GenericExceptionMessageType.ERROR, message, null, cause);
        }

        public GenericException(Throwable e) {

                super(e);
                this.addMessage(GenericExceptionMessageType.ERROR, e.getMessage(), null, e);
        }

        public GenericException(GenericExceptionMessage genericExceptionMessage) {

                super(genericExceptionMessage.getDescription(), genericExceptionMessage.getCause());
        }

        public void addMessage(GenericExceptionMessageType type, String description) {

                this.addMessage(new GenericExceptionMessage(type, description));
        }

        public void addMessage(String identifier, GenericExceptionMessageType type, String description) {

                this.addMessage(identifier, new GenericExceptionMessage(type, description));
        }

        public void addMessage(GenericExceptionMessageType type, String description, String detail) {

                this.addMessage(new GenericExceptionMessage(type, description, detail));
        }

        public void addMessage(String identifier, GenericExceptionMessageType type, String description, String detail) {

                this.addMessage(identifier, new GenericExceptionMessage(type, description, detail));
        }

        public void addMessage(GenericExceptionMessageType type, String description, String detail, Throwable cause) {

                this.addMessage(new GenericExceptionMessage(type, description, detail, cause));
        }

        public void addMessage(String identifier, GenericExceptionMessageType type, String description, String detail, Throwable cause) {

                this.addMessage(identifier, new GenericExceptionMessage(type, description, detail, cause));
        }

        public void addMessage(GenericExceptionMessage genericExceptionMessage) {

                this.addMessage(null, genericExceptionMessage);
        }

        public void addMessage(String identifier, GenericExceptionMessage genericExceptionMessage) {

                this.messages.add(genericExceptionMessage);
        }

        public Iterable<GenericExceptionMessage> getMessages() {

                return this.messages;
        }

        public boolean hasMessages() {

                return this.messages.size() > 0;
        }

        public boolean containMessagesForIdentifier(String identifier) {

                return this.getFirstMessageForIdentifier(identifier) != null;
        }

        public GenericExceptionMessage getFirstMessageForIdentifier(String identifier) {

                GenericExceptionMessage messageFinded = null;

                for (GenericExceptionMessage message : this.messages) {

                        if(message.hasIdentifier()) {

                                if(message.getIdentifier().equals(identifier)) {

                                        messageFinded = message;
                                        break;
                                }
                        }
                }

                return messageFinded;
        }

        public Iterable<GenericExceptionMessage> getMessagesForIdentifier(String identifier) {

                List<GenericExceptionMessage> messagesFinded = null;

                if(this.containMessagesForIdentifier(identifier)) {

                        messagesFinded = new ArrayList<GenericExceptionMessage>();

                        for (GenericExceptionMessage message : this.messages) {

                                if(message.hasIdentifier()) {

                                        if(message.getIdentifier().equals(identifier)) {

                                                messagesFinded.add(message);
                                        }
                                }
                        }
                }

                return messagesFinded;
        }

        @Override
        public String toString() {

                StringBuilder sb = new StringBuilder("[") ;

                for (GenericExceptionMessage message : this.messages) {

                        sb.append(message.toString());
                        sb.append(", ");
                }
                sb.append("]");

                return sb.toString();
        }
}
