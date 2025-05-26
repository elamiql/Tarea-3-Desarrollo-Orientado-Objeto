package org.example.model;

/**
 * Clase que representa el dulce Snickers.
 * Hereda de la clase abstracta Dulce.
 */
public class Snickers extends Dulce {

    /**
     * Constructor de la clase Snickers.
     *
     * @param codigo Código del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param sabor  Sabor del dulce.
     */
    public Snickers(int codigo, String nombre, int precio, String sabor) {
        super(codigo, nombre, Precios.SNICKERS.getPrecio(), sabor);
    }

    /**
     * Describe el consumo del dulce Snickers.
     *
     * @return Cadena que indica el consumo de Snicker y su sabor.
     */
    @Override
    public String consumirP() {
        return "Consumiendo " + getSabor();
    }
}
