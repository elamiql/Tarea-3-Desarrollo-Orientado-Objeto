package org.example;
import org.example.model.*;

public class Main {
    public static void main(String[] args) {
        try {
            Expendedor exp = new Expendedor(2);

            // Comprador compra una Fanta con moneda de 1000
            Comprador comprador = new Comprador(new Moneda1000(), Expendedor.FANTA, exp);

            // Producto que quedó listo para entrega en el expendedor
            exp.getProducto();
            Producto productoSalida = exp.getProducto();

            System.out.println("Producto comprado:");
            System.out.println("Nombre: " + productoSalida.getNombre());
            System.out.println("Sonido al consumir: " + productoSalida.consumirP());
            System.out.println("Vuelto que recibió comprador: " + comprador.cuantoVuelto());
            System.out.println("Sonido que el comprador escuchó: " + comprador.queBebiste());

        } catch (Exception e) {
            System.out.println("Error en la compra o en el test: " + e.getMessage());
        }
    }
}
