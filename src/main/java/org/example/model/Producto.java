package org.example.model;

/**
 * Clase abstracta producto.
 * Contiene informacion como codigo, nombre y precio.
 * Esta clase debe sera extendida.
 */
public abstract class Producto {
    /**
     * Codigo del producto.
     */
    private int codigo;

    /**
     * Nombre del producto.
     */
    private String nombre;

    /**
     * Precio del producto.
     */
    private int precio;

    /**
     * Constructor de la clase Producto.
     *
     * @param codigo, Codigo del producto.
     * @param nombre, Nombre del producto.
     * @param precio, Precio del producto.
     */
    public Producto(int codigo, String nombre, int precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Obtiene el codigo del producto.
     *
     * @return El codigo del producto.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Setea el codigo del producto.
     *
     * @param codigo Nuevo codigo del producto.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Setea el nombre del producto.
     *
     * @param nombre Nuevo nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Setea el precio del producto.
     *
     * @param precio Nuevo precio del producto.
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * Método abstracto del consumo del producto.
     *
     * @return Una descripcion del sabor.
     */
    public abstract String consumirP();
}