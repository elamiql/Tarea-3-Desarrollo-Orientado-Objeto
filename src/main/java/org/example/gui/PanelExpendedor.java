package org.example.gui;
import org.example.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;
    private PanelComprador panelComprador;
    private JPanel depositoPanel;
    private JButton tomarProductoButton;
    private Image imagenFondo;
    int vuelto = 0;

    {
        try {
            imagenFondo = ImageIO.read(new File("icon/icon.png"));

        }
        catch (IOException e){
            System.err.println("Error al cargar imagen" + e.getMessage());
            imagenFondo = null;
        }
    }

    public PanelExpendedor(Expendedor expendedor){
        this.expendedor = expendedor;
        this.setPreferredSize(new Dimension(504, 137));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0xFFFFFF));
        this.add(Box.createVerticalStrut(20));
        Label();
        this.add(Box.createVerticalStrut(620));
        recogerProducto();
        deposito();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    public void setPanelComprador(PanelComprador panelComprador){
        this.panelComprador = panelComprador;
    }

    private void Label(){
        JPanel tittlePanel = new JPanel();
        tittlePanel.setLayout(new BoxLayout(tittlePanel, BoxLayout.X_AXIS));
        tittlePanel.setOpaque(false);

        JLabel titulo = new JLabel("Expendedor");
        titulo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setOpaque(false);

        tittlePanel.add(Box.createHorizontalGlue());
        tittlePanel.add(titulo);
        tittlePanel.add(Box.createHorizontalGlue());
        this.add(tittlePanel);
    }

    private void recogerProducto(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        tomarProductoButton = new JButton("Recoger Producto y Vuelto");
        tomarProductoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        tomarProductoButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        tomarProductoButton.setBackground(new Color(0xE8FFAF));
        tomarProductoButton.setForeground(Color.black);
        tomarProductoButton.setBorder(BorderFactory.createRaisedBevelBorder());
        tomarProductoButton.setOpaque(true);

        tomarProductoButton.addActionListener(e ->{
            Producto productoRecogido = expendedor.getProducto();
            List<Moneda> vueltoMonedas = expendedor.getVueltoEnMonedas();
            if (productoRecogido != null){
                JOptionPane.showMessageDialog(null, "Has recogido " + productoRecogido.getNombre());
                panelComprador.agregarVuelto(vueltoMonedas);
                refreshDeposito();
                depositoPanel.removeAll();
                repaint();
            } else{
                JOptionPane.showMessageDialog(null, "No hay producto");
            }
        });
        add(tomarProductoButton);
        add(Box.createVerticalGlue());
    }

    private void deposito(){
        depositoPanel = new JPanel();
        depositoPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        depositoPanel.setBackground(new Color(0xFFFFFF, true));
        depositoPanel.setPreferredSize(new Dimension(200, 100));

        this.add(depositoPanel, BorderLayout.SOUTH);
    }

    public void refreshDeposito(){
        if (!expendedor.getDepositoSalida().isEmpty()){
            depositoPanel.removeAll();

            Producto comprado = expendedor.getDepositoSalida().peek();
            ImageIcon imageIcon = new ImageIcon(comprado.getImagen());

            JLabel imageLabel = new JLabel(imageIcon);
            depositoPanel.add(imageLabel);

            depositoPanel.revalidate();
            depositoPanel.repaint();
        }

        JPanel subPanelVuelto = new JPanel();
        subPanelVuelto.setLayout(new FlowLayout(FlowLayout.CENTER, -20, 0));
        subPanelVuelto.setBackground(new Color(0x000F123, true));
        subPanelVuelto.setPreferredSize(new Dimension(500, 80));

        int vuelto = expendedor.getVuelto();
        JLabel subLabel = new JLabel("$" + vuelto + "      ");
        subLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        subLabel.setForeground(Color.white);
        subPanelVuelto.add(subLabel);

        for (int i=0; i<vuelto; i+=100){
            JLabel labelMoneda = new JLabel(new ImageIcon("icon/moneda.png"));
            subPanelVuelto.add(labelMoneda);
        }

        depositoPanel.add(subPanelVuelto);
        depositoPanel.revalidate();
        depositoPanel.repaint();
    }

    public void handleMouseEvent(MouseEvent e){
        int xInicio = 50;
        int yInicio = 50;
        int ancho = 600;
        int alto = 580;

        int xClic = e.getX();
        int yClic = e.getY();

        if (xClic >= xInicio && xClic <= (xInicio + ancho) && yClic >= yInicio && yClic <= (yInicio + alto)){
            rellenarDeposito(expendedor.getNumProductos());
        }
    }

    private void rellenarDeposito(int numProductos){
        List<Deposito<Producto>> depositos = expendedor.getProductos();
        for (int i=0; i<depositos.size(); i++){
            Deposito<Producto> deposito = depositos.get(i);
            while (depositos.size() < numProductos){
                switch (i) {
                    case Expendedor.COCA - 1:
                        deposito.addItem(new CocaCola(deposito.size(), "CocaCola", Precios.COCA_COLA.getPrecio(), "CocaCola"));
                        break;

                    case Expendedor.SPRITE - 1:
                        deposito.addItem(new Sprite(deposito.size(), "Sprite", Precios.SPRITE.getPrecio(), "Sprite"));
                        break;

                    case Expendedor.FANTA - 1:
                        deposito.addItem(new Fanta(deposito.size(), "Fanta", Precios.FANTA.getPrecio(), "Fanta"));
                        break;

                    case Expendedor.SUPER8 - 1:
                        deposito.addItem(new Super8(deposito.size(), "Super8", Precios.SUPER8.getPrecio(), "Super8"));
                        break;

                    case Expendedor.SNICKERS - 1:
                        deposito.addItem(new Snickers(deposito.size(), "Snickers", Precios.SNICKERS.getPrecio(), "Snickers"));
                        break;
                }
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        if (imagenFondo != null){
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
        else{
            System.out.println("No hay imagen");
        }

        int xStart = 60;
        int yStart = 70;
        int x = xStart;
        int y = yStart;

        int productosPorFila = 8;
        int espacioVertical = getHeight()/5 - 50;
        int espacioPrecio = 20;

        Font precioFont = new Font("Comic sans", Font.BOLD, 16);
        g.setFont(precioFont);
        Color precioColor = Color.BLACK;
        g.setColor(precioColor);

        for (int i = 0; i < expendedor.getProductos().size(); i++){
            Deposito<Producto> deposito = expendedor.getProductos().get(i);
            for (int j=0; j<deposito.getAllItems().size(); j++){
                Producto p = deposito.getAllItems().get(j);
                g.drawImage(p.getImagen(), x, y, 64, 64, this);
                String precio = "$" + p.getPrecio();
                int precioX = x + (64 - g.getFontMetrics().stringWidth(precio)) / 2;
                int precioY = y + 64 + espacioPrecio;
                g.drawString(precio, precioX, precioY);
                x += 504/8;

                if ((j+1)%productosPorFila == 0){
                    x = xStart;
                    y += espacioVertical;
                }
            }
            yStart += espacioVertical;
            y=yStart;
            x=xStart;
        }
    }
}