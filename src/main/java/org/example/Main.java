package org.example;
import org.example.gui.Ventana;
import org.example.model.*;

public class Main {
    public static void main(String[] args) {
        Expendedor expendedor = new Expendedor(4);
        new Ventana(expendedor);
    }
}

