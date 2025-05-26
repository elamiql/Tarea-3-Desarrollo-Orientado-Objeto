package org.example.exceptions;

/**
 * Excepción personalizada que se lanza cuando el pago realizado es incorrecto.
 * Hereda de la clase {@link Exception}.
 */
public class PagoIncorrectoException extends Exception {

    /**
     * Constructor de la excepción PagoIncorrectoException.
     *
     * @param ErrorMesage Mensaje de error que describe el fallo.
     */
    public PagoIncorrectoException(String ErrorMesage) {
        super(ErrorMesage);
    }
}