package org.example.gui;

import org.example.model.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PanelComprador extends JPanel {
    private PanelPrincipal panelPrincipal;
    private List<Moneda> monedas = new ArrayList<>();
    private JPanel monedasPanel, contentPanel, añadirMonedas, mainPanel;
    private JLabel productoSelLabel;
    private JLabel totalMonedasLabel;
    private String productoSeleccionado = "Ninguno";
    private JComboBox<String> comboBox;
    private int totalMonedas = 0;
    private Expendedor expendedor;
    int temp = 1;
    private List<Moneda> listaMonedas = new ArrayList<>();

    public PanelComprador(Expendedor expendedor, PanelPrincipal panelPrincipal){
        this.expendedor = expendedor;
        this.panelPrincipal = panelPrincipal;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0xE8E8DE));
        Labels();
        this.add(Box.createVerticalStrut(15));
        tusMonedas();
        this.add(Box.createVerticalStrut(15));
        actualizarPantalla();
        this.add(Box.createVerticalStrut(15));
    }

    private void Labels(){
        JLabel titulo = new JLabel("Comprador");
        titulo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titulo);
        this.add(Box.createVerticalStrut(5));

        productoSelLabel = new JLabel("Producto Seleccionado: Ninguno");
        productoSelLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        productoSelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(productoSelLabel);
        this.add(Box.createVerticalStrut(5));

        totalMonedasLabel = new JLabel("Tus monedas: $0");
        totalMonedasLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        totalMonedasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(totalMonedasLabel);
    }

    private void actualizarProductoSel(){
        productoSeleccionado = (String) comboBox.getSelectedItem();
        productoSelLabel.setText("Producto seleccionado: " + productoSeleccionado);
    }

    public void actualizarTotalMonedas(int total){
        totalMonedas = total;
        totalMonedasLabel.setText("Tus Monedas: $" + totalMonedas);
    }

    public void agregarVuelto(List<Moneda> vueltoMonedas){
        for (Moneda moneda : vueltoMonedas){
            totalMonedas += moneda.getValor();
            monedas.add(moneda);
            addMonedaToPanel(moneda);
        }
        totalMonedasLabel.setText("Tus Monedas: $" + totalMonedas);
        monedasPanel.revalidate();
        monedasPanel.repaint();
    }

    private void Monedas(){
        añadirMonedas = new JPanel();
        añadirMonedas.setLayout(new BoxLayout(añadirMonedas, BoxLayout.X_AXIS));
        añadirMonedas.setOpaque(false);

        JLabel monedasLabel = new JLabel("Añadir monedas:");
        monedasLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        monedasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        añadirMonedas.add(monedasLabel);
        añadirMonedas.add(Box.createVerticalStrut(10));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setOpaque(false);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(0xFFFFFF, true));
        contentPanel.setOpaque(false);
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon imageIcon = loadImage();
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 15));
        mainPanel.add(imageLabel);

        contentPanel.add(createMonedaButton("$100"));
        contentPanel.add(createMonedaButton("$500"));
        contentPanel.add(createMonedaButton("$1000"));

        guardarMonedas();
        mainPanel.add(contentPanel);
        add(añadirMonedas);
        add(mainPanel);
    }

    private void tusMonedas(){
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setBackground(new Color(0xFFFFFF));

        monedasPanel = new JPanel();
        monedasPanel.setLayout(new BoxLayout(monedasPanel, BoxLayout.Y_AXIS));
        monedasPanel.setBackground(new Color(0x000FFF));
        monedasPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        monedasPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel monedasLabel = new JLabel("Tus Monedas:");
        monedasLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        monedasPanel.add(monedasLabel);

        JScrollPane scrollPane = new JScrollPane(monedasPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPanel.add(scrollPane);
        add(contentPanel);
    }

    private void addMonedaToPanel(Moneda nuevaMoneda){
        listaMonedas.add(nuevaMoneda);
        actualizarMonedasPanel();
    }

    private JButton createMonedaButton(String valor){
        JButton button = new JButton(valor);
        button.setFont(new Font("Times New Roman", Font.BOLD, 20));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setBackground(new Color(0xFFFFFF));
        button.setForeground(Color.BLACK);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Moneda nuevaMoneda = null;
                switch (valor){
                    case "$100":
                        nuevaMoneda = new Moneda100();
                        System.out.println("el numero de serie de la moneda es:" +nuevaMoneda.getSerie());

                        break;
                    case "$500":
                        nuevaMoneda = new Moneda500();
                        System.out.println("el numero de serie de la moneda es:" +nuevaMoneda.getSerie());
                        break;
                    case "$1000":
                        nuevaMoneda = new Moneda1000();
                        System.out.println("el numero de serie de la moneda es:" +nuevaMoneda.getSerie());
                        break;
                }
                if (nuevaMoneda != null){
                    monedas.add(nuevaMoneda);
                    addMonedaToPanel(nuevaMoneda);
                    int total = 0;
                    for(Moneda moneda : monedas){
                        total += moneda.getValor();
                    }
                    actualizarTotalMonedas(total);
                }
            }
        });
        return button;
    }

    private void guardarMonedas(){
        JButton ingresar = new JButton("Ingresar");
        ingresar.setFont(new Font("Times New Roman", Font.BOLD, 20));
        ingresar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        ingresar.setBorder(BorderFactory.createRaisedBevelBorder());
        ingresar.setBackground(new Color(0xFFF000));
        ingresar.setForeground(Color.WHITE);
        ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = 0;
                actualizarPantalla();
            }
        });
        contentPanel.add(ingresar);
    }

    private void actualizarMonedasPanel(){
        listaMonedas.sort((m1, m2) -> Integer.compare(m2.getValor(), m1.getValor()));
        monedasPanel.removeAll();

        for (Moneda moneda : listaMonedas){
            JPanel monedaPanel = new JPanel();
            monedaPanel.setLayout(new BoxLayout(monedaPanel, BoxLayout.X_AXIS));
            monedaPanel.setBackground(new Color(0xffaadd));

            JLabel label = new JLabel("Moneda $ "+ moneda.getValor());
            label.setFont(new Font("Times New Roman", Font.BOLD, 16));

            try {
                BufferedImage monedaImage = ImageIO.read(new File(moneda.getImagePath()));
                ImageIcon monedaIcon = new ImageIcon(monedaImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                JLabel monedaLabel = new JLabel(monedaIcon);
                monedaPanel.add(monedaLabel);
            } catch (IOException e){
                e.printStackTrace();
            }
            monedaPanel.add(label);
            monedasPanel.add(monedaPanel);

        }
        monedasPanel.revalidate();
        monedasPanel.repaint();
    }

    public void actualizarPantalla(){
        if (temp == 1){
            Monedas();
            this.add(Box.createVerticalStrut(15));
            repaint();
        } else{
            mainPanel.removeAll();
            añadirMonedas.removeAll();
            revalidate();
            repaint();
            Producto();
            this.add(Box.createVerticalStrut(15));
            addCompraButton();
            this.add(Box.createVerticalStrut(15));
        }
    }

    private ImageIcon loadImage() {
        try {
            File imagePath = new File("icon/icon.png");
            BufferedImage originalImage = ImageIO.read(imagePath);
            int width = 100;
            int height = 100;
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void Producto(){
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setBackground(new Color(0xFFFFFF));

        JPanel productosPanel = new JPanel();
        productosPanel.setLayout(new BoxLayout(productosPanel, BoxLayout.Y_AXIS));
        productosPanel.setBackground(new Color(0xFFFFFF));
        productosPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel productosLabel = new JLabel("Seleccionar Producto");
        productosLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        productosPanel.add(productosLabel);
        productosPanel.add(Box.createVerticalStrut(15));

        String[] productos = {"Ninguno", "Coca-Cola", "Fanta", "Sprite", "Super8", "Snickers"};
        comboBox = new JComboBox<>(productos);
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        comboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, comboBox.getPreferredSize().height));

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProductoSel();
            }
        });

        productosPanel.add(comboBox);
        contentPanel.add(productosPanel);
        add(contentPanel);
    }

    private void addCompraButton(){
        JButton compraButton = new JButton("Comprar");
        compraButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        compraButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        compraButton.setBorder(BorderFactory.createRaisedBevelBorder());
        compraButton.setBackground(new Color(0xFFFFFF));
        compraButton.setForeground(Color.WHITE);

        compraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!expendedor.getDepositoSalida().isEmpty()){
                    JOptionPane.showMessageDialog(PanelComprador.this, "El depósito ya tiene un producto");
                    return;
                }

                actualizarProductoSel();
                System.out.println(productoSeleccionado);
                boolean compraLograda = false;

                switch (productoSeleccionado){
                    case "Ninguno":
                        JOptionPane.showMessageDialog(PanelComprador.this, "Seleccione un producto");
                        break;
                    case "Coca-Cola":
                        if (procesarCompra(Precios.COCA_COLA.getPrecio(), "Coca-Cola")){
                            compraLograda = true;
                        }
                        break;
                    case "Sprite":
                        if (procesarCompra(Precios.COCA_COLA.getPrecio(), "Sprite")){
                            compraLograda = true;
                        }
                        break;

                    case "Fanta":
                        if (procesarCompra(Precios.COCA_COLA.getPrecio(), "Fanta")){
                            compraLograda = true;
                        }
                    break;

                    case "Super8":
                        if (procesarCompra(Precios.COCA_COLA.getPrecio(), "Super8")){
                            compraLograda = true;
                        }
                    break;

                    case "Snickers":
                        if (procesarCompra(Precios.COCA_COLA.getPrecio(), "Snickers")){
                            compraLograda = true;
                        }
                    break;
                }
                if (compraLograda){
                    panelPrincipal.refreshDisplay();
                    actualizarMonedasPanel();
                }
                else if (!productoSeleccionado.equals("Ninguno")){
                    if (Precios.SNICKERS.getPrecio() > totalMonedas){
                        JOptionPane.showMessageDialog(PanelComprador.this, "No te alcanza broder");
                    }
                }
            }
        });
        add(compraButton);
    }

    private List<Moneda> removerMonedas(int cantidad){
        List<Moneda> monedasParaRemover = new ArrayList<>();
        int totalRecolectado = 0;

        listaMonedas.sort((m1, m2) -> Integer.compare(m2.getValor(), m1.getValor()));

        for (Moneda moneda : listaMonedas){
            monedasParaRemover.add(moneda);
            totalRecolectado += moneda.getValor();

            if (totalRecolectado >= cantidad){
                break;
            }
        }

        if (totalRecolectado >= cantidad){
            listaMonedas.removeAll(monedasParaRemover);
            return monedasParaRemover;
        }
        else{
            return null;
        }
    }

    private boolean procesarCompra(int precioProducto, String cual){
        List<Moneda> monedasUsadas = removerMonedas(precioProducto);
        if (monedasUsadas != null){
            try {
                expendedor.comprarProducto(monedasUsadas.get(0), cual);
                actualizarTotalMonedas(totalMonedas - monedasUsadas.get(0).getValor());
                JOptionPane.showMessageDialog(PanelComprador.this, "Compra realizada: " + cual);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al comprar: " + e.getMessage());
                monedas.addAll(monedasUsadas);
                return false;
            }
        }
        else{
            return false;
        }
    }

    public void handleMouseEvent(MouseEvent e){
        System.out.println("Mouse click in PanelComprador at x=: "+ e.getX() + "y=" + e.getY());
        if (e.getX() > 100 && e.getY() < 200){
            actualizarProductoSel();
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}