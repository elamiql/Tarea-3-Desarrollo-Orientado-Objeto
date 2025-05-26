package org.example.model;

/**
 * Enum que representa los precios de los productos.
 */
public enum Precios {

    /**
     * Precio de la bebida Coca-Cola.
     */
    COCA_COLA(1000),

    /**
     * Precio de la bebida Sprite.
     */
    SPRITE(800),

    /**
     * Precio de la bebida Fanta.
     */
    FANTA(800),

    /**
     * Precio del dulce Super8.
     */
    SUPER8(400),

    /**
     * Precio del dulce Snickers.
     */
    SNICKERS(400);

    /**
     * Precio de cada producto.
     */
    private final int precio;

    /**
     * Constructor del enum Precios.
     *
     * @param precio, Precio del producto.
     */
    Precios(int precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public int getPrecio() {
        return precio;
    }
}
