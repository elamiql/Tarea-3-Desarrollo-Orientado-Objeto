package org.example.gui;

import org.example.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Ventana es una clase que representa la ventana principal de la interfaz gráfica
 * para una máquina expendedora.
 */
public class Ventana extends JFrame {

    /**
     * Constructor de la clase Ventana.
     *
     * @param expendedor el expendedor asociado a esta ventana
     */
    public Ventana(Expendedor expendedor) {
        PanelPrincipal panelPrincipal = new PanelPrincipal(expendedor);
        this.add(panelPrincipal);
        configurarVentana();
    }

    /**
     * Configura las propiedades de la ventana.
     */
    private void configurarVentana() {
        this.setTitle("Maquina Expendedora"); // Título de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Al cerrar la ventana se termina el programa
        this.setSize(1260, 800); // Tamaño de la ventana
        this.setResizable(false); // Impide que la ventana sea redimensionada
        this.setLocationRelativeTo(null); // Centra la ventana
        this.setVisible(true); // Hace visible la ventana

        // Cargar el icono de la ventana
        try {
            File iconFile = new File("icon/icon.png");
            if (iconFile.exists()) {
                this.setIconImage(ImageIO.read(iconFile));
            } else {
                System.err.println("Archivo no encontrado: " + iconFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
