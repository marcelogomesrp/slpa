/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lavor.utils;

/**
 *
 * @author marcelo
 */

public class GenericExceptionMessage {


        private GenericExceptionMessageType type;
        private String identifier;
        private String description;
        private String detail;
        private Throwable cause;

        public GenericExceptionMessage(GenericExceptionMessageType type, String description) {

                this.type = type;
                this.description = description;
        }

        public GenericExceptionMessage(GenericExceptionMessageType type, String description, String detail) {

                this(type, description);
                this.detail = detail;
        }

        public GenericExceptionMessage(GenericExceptionMessageType type, String description, String detail, Throwable cause) {

                this(type, description, detail);
                this.cause = cause;
        }

        public GenericExceptionMessage(String identifier, GenericExceptionMessageType type, String description) {

                this(type, description);
                this.identifier = identifier;
        }

        public GenericExceptionMessage(String identifier, GenericExceptionMessageType type, String description, String detail) {

                this(identifier, type, description);
                this.detail = detail;
        }

        public GenericExceptionMessage(String identifier, GenericExceptionMessageType type, String description, String detail, Throwable cause) {

                this(identifier, type, description, detail);
                this.cause = cause;
        }

        public GenericExceptionMessageType getType() {

                return this.type;
        }

        public String getIdentifier() {

                return this.identifier;
        }

        public String getDescription() {

                return this.description;
        }

        public String getDetail() {

                return this.detail;
        }

        public Throwable getCause() {

                return this.cause;
        }

        public boolean hasIdentifier() {

                return !StringUtils.isNullOrEmpty(this.getIdentifier());
        }

        public String toString() {

                return this.getType().toString().concat(" - ")
                                        .concat(((this.hasIdentifier()) ? "for identifier ".concat(this.getIdentifier()) : "" ).concat(" - "))
                                        .concat(this.getDescription()).concat(" - ")
                                        .concat(this.getDetail());
        }
}
