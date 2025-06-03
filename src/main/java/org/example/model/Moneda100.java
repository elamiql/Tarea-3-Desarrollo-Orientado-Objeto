package org.example.model;

/**
 * Clase moneda de 100.
 * Extiende la clase abstracta Moneda.
 */
public class Moneda100 extends Moneda {
    /**
     * Constructor Moneda100.
     * Inicializa una nueva instancia de Moneda100.
     */
    public Moneda100() {
        super();
    }

    /**
     * Obtiene el valor de la moneda.
     *
     * @return el valor de la moneda (100)
     */
    public int getValor() {
        return 100;
    }

    public String getImagePath(){
        return "icon/moneda2.png";
    }
}