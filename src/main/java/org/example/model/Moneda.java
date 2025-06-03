package org.example.model;

/**
 * Clase abstracta moneda.
 * Implementa la interfaz Comparable.
 */
public abstract class Moneda implements Comparable<Moneda> {
    private int serie;
    private static int contador=0;
    /**
     * Constructor de Moneda.
     * Inicializa moneda.
     */
    public Moneda() {
        serie=contador;
        contador++;
    }
    public int getSerie(){
        return serie;
    }
    public void setSerie(int n){
        serie=n;
    }
    /**
     * Método abstracto valor.
     * Cada clase hija debe implementar este método.
     * @return el valor de la moneda.
     */
    public abstract int getValor();
    public abstract String getImagePath();
    /**
     * Sobrescribe el método toString.
     * Muestra numero de serie y valor .
     *
     * @return caracteristicas de moneda.
     */
    @Override
    public String toString() {
        return "Número de serie: " + this.hashCode() + ", valor: " + getValor();
    }

    /**
     * Compara monedas.
     * La comparación se realiza por el valor de la moneda.
     *
     * @param otraMoneda la moneda con la que se compara
     * @return un valor negativo si esta moneda es menor,
     *         un valor positivo si esta moneda es mayor,
     *         o cero si ambas monedas tienen el mismo valor.
     */
    @Override
    public int compareTo(Moneda otraMoneda) {
        return Integer.compare(this.getValor(), otraMoneda.getValor());
    }
}