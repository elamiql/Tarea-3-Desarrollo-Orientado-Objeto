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

/**
 * PanelComprador es un panel de interfaz gráfica para que el usuario interactúe como comprador.
 * Permite insertar monedas, seleccionar productos y realizar compras.
 */
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


    /**
     * Constructor del PanelComprador.
     * @param expendedor El expendedor asociado.
     * @param panelPrincipal El panel principal de la interfaz.
     */
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
        listaMonedas = new ArrayList<>();
        inicializarMonedas();

        actualizarPantalla();
        actualizarMonedasPanel();


    }
    private void inicializarMonedas() {

        listaMonedas.add(new Moneda1000());
        listaMonedas.add(new Moneda1000());

        // Calcular total
        for (Moneda m : listaMonedas) {
            totalMonedas += m.getValor();
        }
    }

    /**
     * Agrega etiquetas al panel.
     */
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

        totalMonedasLabel = new JLabel("Tus monedas: $"+totalMonedas);
        totalMonedasLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        totalMonedasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(totalMonedasLabel);
    }

    /**
     * Actualiza la etiqueta con el producto seleccionado.
     */
    private void actualizarProductoSel(){
        productoSeleccionado = (String) comboBox.getSelectedItem();
        productoSelLabel.setText("Producto seleccionado: " + productoSeleccionado);
    }

    /**
     * Actualiza la etiqueta con el total de monedas disponibles.
     * @param total Total de monedas.
     */
    public void actualizarTotalMonedas(int total){
        totalMonedas = total;
        totalMonedasLabel.setText("Tus Monedas: $" + totalMonedas);
    }

    /**
     * Agrega las monedas de vuelto al panel.
     * @param vueltoMonedas Lista de monedas devueltas.
     */
    public void agregarVuelto(List<Moneda> vueltoMonedas){
        for (Moneda moneda : vueltoMonedas){
            totalMonedas += moneda.getValor();
            monedas.add(moneda);
            addMonedaToPanel(moneda);
        }
        totalMonedasLabel.setText("Tus Monedas: $" + totalMonedas);
        monedasPanel.revalidate(); //Anuncia cambio
        monedasPanel.repaint();    //Vuelve a dibujar
    }

    /**
     * Crea y muestra el panel para ingresar monedas.
     */
    private void Monedas() {
        // Panel horizontal que contiene el texto "Añadir monedas"
        añadirMonedas = new JPanel();
        añadirMonedas.setLayout(new BoxLayout(añadirMonedas, BoxLayout.Y_AXIS));
        añadirMonedas.setOpaque(false); // Fondo transparente

        JLabel monedasLabel = new JLabel("Ingresar Monedas");
        monedasLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        monedasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        añadirMonedas.add(monedasLabel);

        // Espaciador horizontal
        añadirMonedas.add(Box.createVerticalStrut(10));

        // Panel principal que contendrá la imagen y los botones de monedas, dispuestos horizontalmente
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false); // Fondo transparente

        // Panel que contendrá los botones de monedas, dispuestos verticalmente
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(0xFFFFFF, true));
        contentPanel.setOpaque(false);
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon imageIcon = loadImage();

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 15));

        // Agregar la imagen al panel horizontal principal
        mainPanel.add(imageLabel);

        // Agregar botones de monedas al panel vertical
        contentPanel.add(createMonedaButton("$100"));
        contentPanel.add(createMonedaButton("$500"));
        contentPanel.add(createMonedaButton("$1000"));

        guardarMonedas();

        mainPanel.add(contentPanel);

        add(añadirMonedas);
        add(mainPanel);
    }

    /**
     * Construye el panel que muestra las monedas del usuario.
     */
    private void tusMonedas(){
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setBackground(new Color(0xFFFFFF));

        monedasPanel = new JPanel();
        monedasPanel.setLayout(new BoxLayout(monedasPanel, BoxLayout.Y_AXIS));
        monedasPanel.setBackground(new Color(0xFFFFFF));
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

    /**
     * Agrega visualmente una moneda al panel de monedas del comprador.
     * @param nuevaMoneda Moneda agregada.
     */
    private void addMonedaToPanel(Moneda nuevaMoneda){
        listaMonedas.add(nuevaMoneda);
        actualizarMonedasPanel();
    }

    /**
     * Crea un botón que representa una moneda con un valor determinado.
     * @param valor Valor de la moneda.
     * @return Botón configurado.
     */
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

                    addMonedaToPanel(nuevaMoneda);
                    int total = 0;
                    for (Moneda moneda : listaMonedas) {
                        total += moneda.getValor();
                    }
                    actualizarTotalMonedas(total);
                }
            }
        });
        return button;
    }

    /**
     * Agrega el botón de "Ingresar" para confirmar el ingreso de monedas.
     */
    private void guardarMonedas(){
        JButton ingresar = new JButton("Ingresar");
        ingresar.setFont(new Font("Times New Roman", Font.BOLD, 20));
        ingresar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        ingresar.setBorder(BorderFactory.createRaisedBevelBorder());
        ingresar.setBackground(new Color(0xFFFFFF));
        ingresar.setForeground(Color.BLACK);
        ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = 0;
                actualizarPantalla();
            }
        });
        contentPanel.add(ingresar);
    }

    /**
     * Actualiza visualmente el panel con las monedas.
     */
    private void actualizarMonedasPanel(){
        listaMonedas.sort((m1, m2) -> Integer.compare(m2.getValor(), m1.getValor()));
        monedasPanel.removeAll();

        for (Moneda moneda : listaMonedas){
            JPanel monedaPanel = new JPanel();
            monedaPanel.setLayout(new BoxLayout(monedaPanel, BoxLayout.X_AXIS));
            monedaPanel.setBackground(new Color(0xFFFFFF));

            JLabel label = new JLabel("Moneda $ "+ moneda.getValor());
            label.setFont(new Font("Times New Roman", Font.BOLD, 16));

            try {
                BufferedImage monedaImage = ImageIO.read(new File(moneda.getImagePath()));
                ImageIcon monedaIcon = new ImageIcon(monedaImage.getScaledInstance(64 + 25, 64 + 25, Image.SCALE_SMOOTH));
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

    /**
     * Refresca la pantalla del comprador.
     */
    public void actualizarPantalla(){
        if (temp == 1){
            // Limpia todos los componentes antes de reconstruir la interfaz
            this.removeAll();

            // Reconstruye la interfaz desde cero
            Labels();

            int total = 0;
            for (Moneda moneda : listaMonedas) {
                total += moneda.getValor();
            }
            actualizarTotalMonedas(total);

            this.add(Box.createVerticalStrut(15));
            tusMonedas();
            this.add(Box.createVerticalStrut(15));
            Monedas();
            this.add(Box.createVerticalStrut(15));

            // Refresca visualmente el panel
            this.revalidate();
            this.repaint();
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

    /**
     * Carga y escala una imagen desde archivo.
     * @return ImageIcon escalado o null si ocurre error.
     */
    private ImageIcon loadImage() {
        try {
            File imagePath = new File("icon/coinAcceptor.png");
            BufferedImage originalImage = ImageIO.read(imagePath);
            int width = 64 + 30;
            int height = 64 + 30;
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Crea el panel para seleccionar un producto.
     */
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

    /**
     * Obtiene el precio correspondiente al producto.
     * @param nombreProducto Nombre del producto.
     * @return Precio.
     */
    private Precios obtenerPrecioEnum(String nombreProducto) {
        switch (nombreProducto) {
            case "Coca-Cola": return Precios.COCA_COLA;
            case "Sprite": return Precios.SPRITE;
            case "Fanta": return Precios.FANTA;
            case "Super8": return Precios.SUPER8;
            case "Snickers": return Precios.SNICKERS;
            default: return null;
        }
    }

    /**
     * Agrega el botón de compra y define su acción.
     */
    private void addCompraButton(){
        JButton compraButton = new JButton("Comprar");
        compraButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        compraButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        compraButton.setBorder(BorderFactory.createRaisedBevelBorder());
        compraButton.setBackground(new Color(0xFFFFFF));
        compraButton.setForeground(Color.BLACK);


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

                Precios precioEnum = obtenerPrecioEnum(productoSeleccionado);

                if (precioEnum == null) {
                    JOptionPane.showMessageDialog(PanelComprador.this, "Seleccione un producto válido");
                    return;
                }

                if (totalMonedas < precioEnum.getPrecio()) {
                    JOptionPane.showMessageDialog(PanelComprador.this, "No te alcanza broder");
                    return;
                }

                if (procesarCompra(precioEnum.getPrecio(), productoSeleccionado)) {
                    compraLograda = true;
                }
                if (compraLograda) {
                    // Si la compra tuvo éxito, se actualiza la interfaz
                    panelPrincipal.refreshDisplay();
                    actualizarMonedasPanel();

                    // Pregunta al usuario si desea hacer otra compra
                    int opcion = JOptionPane.showConfirmDialog(
                            PanelComprador.this,
                            "¿Deseas realizar otra compra?",
                            "Compra realizada con éxito",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (opcion == JOptionPane.NO_OPTION) {
                        // Si el usuario no desea comprar otra cosa, reinicia selección
                        productoSeleccionado = "Ninguno";
                        productoSelLabel.setText("Producto Seleccionado: Ninguno");
                        temp = 1;
                        actualizarPantalla();
                        actualizarMonedasPanel();
                    }
                }
                // Si no se logró la compra pero sí se seleccionó producto
                else if (!productoSeleccionado.equals("Ninguno")) {
                    int precio = Precios.valueOf(productoSeleccionado.toUpperCase().replace("-", "_")).getPrecio();
                    if (totalMonedas < precio) {
                        JOptionPane.showMessageDialog(
                                PanelComprador.this,
                                "No te alcanza broder"
                        );
                        // reinicia selección
                        productoSeleccionado = "Ninguno";
                        productoSelLabel.setText("Producto Seleccionado: Ninguno");
                        temp = 1;
                        actualizarPantalla();
                        actualizarMonedasPanel();
                    }
                }

            }
        });

        // Agrega el botón al panel
        add(compraButton);
    }

    /**
     * Remueve monedas de la lista interna hasta completar el monto necesario.
     * @param cantidad Cantidad requerida.
     * @return Lista de monedas usadas o null si no alcanza.
     */
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

    /**
     * Intenta procesar la compra del producto.
     * @param precioProducto Precio del producto.
     * @param cual Nombre del producto.
     * @return true si la compra fue exitosa, false en caso contrario.
     */
    private boolean procesarCompra(int precioProducto, String cual){
        List<Moneda> monedasUsadas = removerMonedas(precioProducto);
        int x=0;
        if (monedasUsadas != null){
            try {
                expendedor.comprarProducto(monedasUsadas, cual);
                for(Moneda moneda:monedasUsadas){
                     x +=moneda.getValor();
                }
                actualizarTotalMonedas(totalMonedas - x);
                JOptionPane.showMessageDialog(PanelComprador.this, "Compra realizada: " + cual);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al comprar: " + e.getMessage());
                listaMonedas.addAll(monedasUsadas);
                return false;
            }
        }
        else{
            return false;
        }
    }

    /**
     * Maneja eventos del mouse para depuración o futura interacción.
     * @param e Evento del mouse.
     */
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
