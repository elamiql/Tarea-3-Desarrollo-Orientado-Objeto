package org.example.model;

/**
 * Clase abstracta que representa una bebida, la cual es un tipo de producto.
 * Se especifica la marca de la bebida.
 */
public abstract class Bebida extends Producto {
    /**
     * Marca de la bebida.
     */
    private String marca;

    /**
     * Constructor de la clase Bebida.
     *
     * @param serie,  Código único del producto.
     * @param nombre, Nombre del producto.
     * @param precio, Precio del producto.
     * @param marca , Marca de la bebida.
     */
    public Bebida(int serie, String nombre, int precio, String marca) {
        super(serie, nombre, precio);
        this.marca = marca;
    }

    /**
     * Obtiene la marca de la bebida.
     *
     * @return La marca de la bebida.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Método abstracto que describe que se esta bebiendo y la marca de la misma.
     * @return Una String describiendo el consumo de la bebida.
     */
    public abstract String consumirP();
}