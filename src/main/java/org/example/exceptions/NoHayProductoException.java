package org.example.exceptions;

/**
 * Excepción personalizada que se lanza cuando no hay un producto disponible.
 * Hereda de la clase {@link Exception}.
 */
public class NoHayProductoException extends Exception {

    /**
     * Constructor de la excepción NoHayProductoException.
     *
     * @param Errormessage Mensaje de error que describe el motivo del fallo.
     */
    public NoHayProductoException(String Errormessage) {
        super(Errormessage);
    }
}
