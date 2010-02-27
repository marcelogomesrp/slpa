jQuery(document).ready(function() {

        jQuery("input.PhoneMask").mask("(99) 9999-9999");

        jQuery("input.DateMask").mask("99/99/9999");

        jQuery("input.CNPJMask").mask("99.999.999/9999-99");

        jQuery("input.CEPMask").mask("99.999-999");

        // jQuery("input.CurrencyMask").mask("999.999.999.99");
        });


                        function CampoMoeda() {

                                this.onClick = function(campo) {
                                        this.posicionarPonteiro(campo);
                                }

                                this.formatarValor = function(campo) {
                                        campo.value = campo.value.replace('.', '');
                                        campo.value = campo.value.replace(',', '');
                                        switch (campo.value.length) {
                                        case 0:
                                                campo.value = '0.00';
                                                break;
                                        case 1:
                                                campo.value = '0.0' + campo.value;
                                                break
                                        case 2:
                                                campo.value = '0.' + campo.value;
                                                break;
                                        default:
                                                // remover 0 a esquerda
                                                while (campo.value.length > 3
                                                                && campo.value.substring(0, 1) == '0') {
                                                        campo.value = campo.value.substring(1,
                                                                        campo.value.length);
                                                }

                                                campo.value = campo.value.substring(0,
                                                                campo.value.length - 2)
                                                                + '.'
                                                                + campo.value.substring(campo.value.length - 2,
                                                                                campo.value.length)
                                                break;
                                        }
                                }

                                this.getAsc = function(keyCode) {
                                        var chr = '';
                                        switch (keyCode) {
                                        case 48:
                                        case 96: // 0 and numpad 0
                                                chr = '0';
                                                break;
                                        case 49:
                                        case 97: // 1 and numpad 1
                                                chr = '1';
                                                break;
                                        case 50:
                                        case 98: // 2 and numpad 2
                                                chr = '2';
                                                break;
                                        case 51:
                                        case 99: // 3 and numpad 3
                                                chr = '3';
                                                break;
                                        case 52:
                                        case 100: // 4 and numpad 4
                                                chr = '4';
                                                break;
                                        case 53:
                                        case 101: // 5 and numpad 5
                                                chr = '5';
                                                break;
                                        case 54:
                                        case 102: // 6 and numpad 6
                                                chr = '6';
                                                break;
                                        case 55:
                                        case 103: // 7 and numpad 7
                                                chr = '7';
                                                break;
                                        case 56:
                                        case 104: // 8 and numpad 8
                                                chr = '8';
                                                break;
                                        case 57:
                                        case 105: // 9 and numpad 9
                                                chr = '9';
                                                break;
                                        }
                                        return chr;
                                }

                                this.onMouseUp = function(campo) {
                                        this.posicionarPonteiro(campo);
                                }

                                this.onKeyDown = function(campo, event) {

                                        var keyCode = event.which || event.keyCode;
                                        switch (keyCode) {
                                        case 27:
                                                campo.value = '0.00';
                                                this.cancelarEvento(event);
                                                break;
                                        case 36: // Home
                                        case 35: // End
                                        case 37: // Left
                                        case 38: // Up
                                        case 39: // Right
                                        case 40: // Down
                                                this.cancelarEvento(event);
                                                this.posicionarPonteiro(campo);
                                                break;

                                        case 8: // backspace
                                        case 46: // delete
                                                this.retirarNumero(campo);
                                                this.formatarValor(campo);
                                                this.cancelarEvento(event);
                                                this.posicionarPonteiro(campo);
                                                break;
                                        case 48:
                                        case 96: // 0 and numpad 0
                                        case 49:
                                        case 97: // 1 and numpad 1
                                        case 50:
                                        case 98: // 2 and numpad 2
                                        case 51:
                                        case 99: // 3 and numpad 3
                                        case 52:
                                        case 100: // 4 and numpad 4
                                        case 53:
                                        case 101: // 5 and numpad 5
                                        case 54:
                                        case 102: // 6 and numpad 6
                                        case 55:
                                        case 103: // 7 and numpad 7
                                        case 56:
                                        case 104: // 8 and numpad 8
                                        case 57:
                                        case 105: // 9 and numpad 9
                                                campo.value = campo.value + this.getAsc(keyCode);
                                                this.formatarValor(campo);
                                                this.cancelarEvento(event);
                                                this.posicionarPonteiro(campo);
                                                break;
                                        default:
                                                this.cancelarEvento(event);
                                                this.posicionarPonteiro(campo);
                                        }
                                }

                                this.posicionarPonteiro = function(campo) {
                                        var r = campo.createTextRange();
                                        r.collapse();
                                        r.moveStart("character", campo.value.length);
                                        r.moveEnd("character", campo.value.length);
                                        r.select();
                                }

                                this.retirarNumero = function(campo) {
                                        campo.value = campo.value.substring(0,
                                                        (campo.value.length - 1));
                                }

                                this.cancelarEvento = function(event) {
                                        if (event.stopPropagation)
                                                event.stopPropagation();
                                        else
                                                event.cancelBubble = true;

                                        if (event.preventDefault)
                                                event.preventDefault();
                                        else
                                                event.returnValue = false;
                                }
                        }

                        var campoMoeda = new CampoMoeda();

