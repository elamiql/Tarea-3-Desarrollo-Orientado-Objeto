package org.example.gui;
import org.example.model.Expendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPrincipal extends JPanel {
    private PanelComprador com;
    private PanelExpendedor exp;

    public PanelPrincipal(Expendedor expendedor){
        exp = new PanelExpendedor(expendedor);
        com = new PanelComprador(expendedor, this);

        exp.setPanelComprador(com);

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

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 0.25;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        this.add(com, gbc);
        gbc.weightx = 0.75;
        gbc.gridx = 1;
        this.add(exp, gbc);
    }

    public void refreshDisplay(){
        exp.refreshDeposito();
        this.repaint();
    }
}