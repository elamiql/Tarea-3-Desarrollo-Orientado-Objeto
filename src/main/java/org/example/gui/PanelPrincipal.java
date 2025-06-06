package org.example.gui;
import org.example.model.Expendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase que representa el panel principal de la interfaz gráfica.
 * Contiene dos subpaneles: uno para el comprador y otro para el expendedor.
 * Este panel gestiona la disposición y la distribución de eventos del mouse entre paneles.
 */
public class PanelPrincipal extends JPanel {
    /**
     * Panel que del comprador.
     */
    private PanelComprador com;

    /**
     * Panel que del expendedor.
     */
    private PanelExpendedor exp;

    /**
     * Constructor del panel principal.
     *
     * @param expendedor El expendedor que se usara en ambos paneles.
     */
    public PanelPrincipal(Expendedor expendedor){
        exp = new PanelExpendedor(expendedor);
        com = new PanelComprador(expendedor, this);

        exp.setPanelComprador(com);

        // Dirige los eventos al panel correspondiente según la posición horizontal
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() < getWidth()/2){
                    com.handleMouseEvent(e);
                }
                else{
                    exp.handleMouseEvent(e);
                }
                repaint();
            }
        });

        this.setBackground(Color.white);

        // Configuración del layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // Añade el panel del comprador a la izquierda
        gbc.weightx = 0.25;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Añade el panel del expendedor a la derecha
        this.add(com, gbc);
        gbc.weightx = 0.75;
        gbc.gridx = 1;
        this.add(exp, gbc);
    }

    /**
     * Actualiza la visualización del expendedor, refrescando su depósito
     * y repintando el panel principal.
     */
    public void refreshDisplay(){
        exp.refreshDeposito();
        this.repaint();
    }
}