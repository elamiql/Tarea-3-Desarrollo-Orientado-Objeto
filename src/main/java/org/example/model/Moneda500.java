package org.example.model;
/**
 * Clase moneda de 500.
 * Extiende la clase abstracta Moneda.
 */
public class Moneda500 extends Moneda {

    /**
     * Constructor Moneda500.
     * Inicializa una nueva instancia de Moneda500.
     * * @param n, numero de serie de la moneda,
     */
    public Moneda500() {
        super();
    }

    /**
     * Obtiene el valor de la moneda.
     *
     * @return el valor de la moneda (500)
     */
    public int getValor() {
        return 500;
    }

    public String getImagePath(){
        return "icon/moneda.png";
    }

}