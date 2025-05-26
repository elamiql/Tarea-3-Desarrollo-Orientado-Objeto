package org.example.model;

import org.example.exceptions.NoHayProductoException;
import org.example.exceptions.PagoIncorrectoException;
import org.example.exceptions.PagoInsuficienteException;

/**
 * Clase que representa un comprador que interactúa con el expendedor.
 * El comprador usa moneda y recibe vuelto si es necesario.
 */
public class Comprador {


    /**
     * Representa el sonido de consumir el producto.
     */
    private String sonido;

    /**
     * Cantidad de vuelto que el comprador recibe.
     */
    private int vuelto;
    /**
     * Constructor de la clase Comprador.
     *
     * @param m Moneda que utiliza el comprador.
     * @param cualProducto Índice del producto que se decea.
     * @param exp Expendedor que se utiliza.
     * @throws NoHayProductoException Si no hay un producto disponible.
     * @throws PagoInsuficienteException Si el pago es insuficiente.
     * @throws PagoIncorrectoException Si el pago es incorrecto.
     */
    public Comprador(Moneda m, int cualProducto, Expendedor exp)
            throws NoHayProductoException, PagoInsuficienteException, PagoIncorrectoException {

        exp.comprarProducto(m, cualProducto);  // Intenta comprar el producto.
        Producto producto = exp.getProducto();

        // Si se compra un producto, se asigna el sonido.
        if (producto != null) {
            sonido = producto.consumirP();
        } else {
            sonido = null;  // Si no se logra comprar no hay sonido.
        }

        vuelto = 0;  // Inicializa el vuelto en 0.
        Moneda moneda;
        moneda = exp.getVuelto();  // Obtiene el vuelto del expendedor.

        // Suma todo el vuelto obtenido en monedas de 100.
        while (moneda != null) {
            vuelto += moneda.getValor();
            moneda = exp.getVuelto();
        }
    }

    /**
     * Obtiene el vuelto total.
     *
     * @return El monto total de vuelto.
     */
    public int cuantoVuelto() {
        return vuelto;
    }

    /**
     * Obtiene el sonido que hace el comprador al consumir un producto.
     *
     * @return El sonido que representa el consumo del producto.
     */
    public String queBebiste() {
        return sonido;
    }
}

