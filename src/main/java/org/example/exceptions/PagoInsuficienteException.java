package org.example.exceptions;

/**
 * Excepción personalizada que se lanza cuando el pago realizado es insuficiente.
 * Hereda de la clase {@link Exception}.
 */
public class PagoInsuficienteException extends Exception {

    /**
     * Constructor de la excepción PagoInsuficienteException.
     *
     * @param Errormesage Mensaje de error que describe el motivo del fallo.
     */
    public PagoInsuficienteException(String Errormesage) {
        super(Errormesage);
    }
}