package org.example.model;
/**
 * Clase moneda1000.
 * Extiende la clase abstracta Moneda.
 */
public class Moneda1000 extends Moneda {

    /**
     * Constructor Moneda1000.
     * Inicializa una nueva instancia de Moneda1000.
     */
    public Moneda1000() {
        super();
    }

    /**
     * Obtiene el valor de la moneda.
     *
     * @return el valor de la moneda (1000)
     */
    public int getValor() {
        return 1000;
    }

    /**
     * Obtiene el camino de la imagen de moneda.
     * @return el camino de la imagen de moneda.
     */
    public String getImagePath(){
        return "icon/moneda1000.png";
    }
}   