package org.example.model;

/**
 * Clase que representa el dulce Super8.
 * Hereda de la clase abstracta Dulce.
 */
public class Super8 extends Dulce {

    /**
     * Constructor de la clase Super8.
     *
     * @param codigo Código del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param sabor  Sabor del dulce.
     */
    public Super8(int codigo, String nombre, int precio, String sabor) {
        super(codigo, nombre, Precios.SUPER8.getPrecio(), sabor, "icon/super8.png");
    }

    /**
     * Describe el consumo del dulce Super8.
     *
     * @return Cadena que indica el consumo de Super8 y su sabor.
     */
    @Override
    public String consumirP() {
        return "Comiendo " + getSabor();
    }
}
