package org.example.model;

/**
 * Clase que representa la bebida Sprite.
 * Extiende la clase abstracta Bebida.
 */
public class Sprite extends Bebida {


    /**
     * Constructor de la clase Sprite.
     *
     * @param codigo Codigo del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param marca Marca de la bebida.
     */
    public Sprite(int codigo, String nombre, int precio, String marca) {
        super(codigo, nombre, precio, marca, "icon/sprite.png");
    }

    /**
     * Describe el consumo de la bebida Sprite.
     *
     * @return Una cadena que indica consumo de Sprite.
     */
    @Override
    public String consumirP() {
        return "Bebiendo " + getMarca();
    }
}