package org.example.model;

/**
 * Clase abstracta que representa un dulce, que es un tipo de producto.
 * Se especifica el sabor del dulce.
 */
public abstract class Dulce extends Producto {
    /**
     * El sabor del dulce.
     */
    private String sabor;

    /**
     * Constructor de la clase Dulce.
     *
     * @param codigo, Código del producto.
     * @param nombre, Nombre del producto.
     * @param precio, Precio del producto.
     * @param sabor,  Sabor del dulce.
     */
    public Dulce(int codigo, String nombre, int precio, String sabor) {
        super(codigo, nombre, precio);
        this.sabor = sabor;
    }

    /**
     * Obtiene el sabor del dulce.
     *
     * @return El sabor del dulce.
     */
    public String getSabor() {
        return sabor;
    }

    /**
     * Setea el sabor del dulce.
     *
     * @param sabor El nuevo sabor del dulce.
     */
    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    /**
     * Devuelve una descripción del consumo del producto.
     *
     * @return Una cadena de texto que señala que esta consumiendo el dulce y su sabor.
     */
    @Override
    public String consumirP() {
        return "Comiendo " + this.sabor;
    }
}