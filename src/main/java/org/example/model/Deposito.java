package org.example.model;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Agrega un elemento al deposito solo si este está vacío.
     * @param item Elemento a agregar si el deposito está vacío.
     */
    public void addItemSalida(T item){
        if (items.isEmpty()){
            items.add(item);
        }
    }

    /**
     * Obtiene todos los elemento del depósito.
     * @return Todos los elementos del deposito o null si está vacío.
     */
    public List<T> getAllItems(){
        return new ArrayList<>(items);
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

    /**
     * Verifica si el depósito está vacío.
     * @return true si el depósito está vacío, de lo contrario false.
     */
    public boolean isEmpty(){
        return items.isEmpty();
    }

    /**
     * Devuelve el primer elemento del deposito sin eliminarlo.
     * @return El primer elemento del deposito, o null si está vacío.
     */
    public T peek(){
        if (!items.isEmpty()){
            return items.get(0);
        }
        return null;
    }

    /**
     * Elimina el elemento en la posicion especificada del deposito.
     * @param item Indice del elemento a eliminar.
     * @return El elemento eliminado.
     */
    public T remove(int item){
        return items.remove(item);
    }

    /**
     * Devuelve la cantidad de elementos en el deposito.
     * @return Numero de elementos en el deposito.
     */
    public int size(){
        return items.size();
    }

    /**
     * Elimina todos los elementos del depsito.
     */
    public void clearItems(){
        items.clear();
    }
}
