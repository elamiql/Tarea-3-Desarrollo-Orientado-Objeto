package org.example.model;

import java.util.ArrayList;

/**
 * Clase genérica deposito.
 * @param <T> tipo de objetos en depósito.
 */
public class Deposito<T> {
    private ArrayList<T> items;

    /**
     * Constructor para depósito vacío.
     */
    public Deposito() {
        this.items = new ArrayList<>();
    }

    /**
     * Agrega un elemento al depósito.
     * @param item a agregar.
     */
    public void addItem(T item) {
        items.add(item);
    }

    public void addItemSalida(T item){
        if (items.isEmpty()){
            items.add(item);
        }
    }

    /**
     * Obtiene un elemento del depósito.
     * @return El primer elemento del deposito o null si está vacío.
     */
    public T getItem() {
        if (items.isEmpty()) {
            return null;
        } else {
            return items.remove(0);
        }
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }
}
