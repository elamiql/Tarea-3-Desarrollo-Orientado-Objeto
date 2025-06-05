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
                imagenFondo = ImageIO.read(new File("icon/fondo.png"));

            }
            catch (IOException e){
                System.err.println("Error al cargar imagen" + e.getMessage());
                imagenFondo = null;
            }
        }

        public PanelExpendedor(Expendedor expendedor){
            this.expendedor = expendedor;
            this.setPreferredSize(new Dimension(300, 137));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBackground(new Color(0xFFFFFF));
            this.add(Box.createVerticalStrut(10));
            Label();
            this.add(Box.createVerticalStrut(630));
            recogerProducto();
            deposito();
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleMouseEvent(e);
                }
            });;
        }

        public void setPanelComprador(PanelComprador panelComprador){
            this.panelComprador = panelComprador;
        }

        private void Label(){
            JPanel tittlePanel = new JPanel();
            tittlePanel.setLayout(new BoxLayout(tittlePanel, BoxLayout.X_AXIS));
            tittlePanel.setOpaque(false);

            JLabel titulo = new JLabel("Expendedor");
            titulo.setFont(new Font("Comic Sans", Font.BOLD, 20));
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
            tomarProductoButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            tomarProductoButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
            tomarProductoButton.setBackground(new Color(0xFFFFFF));
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
                    System.out.println("Altura del depositoPanel: " + depositoPanel.getPreferredSize().height);
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
            depositoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            depositoPanel.setBackground(new Color(0xFFFFFF, true));
            depositoPanel.setPreferredSize(new Dimension(200, 100));

            this.add(depositoPanel);
        }

        public void refreshDeposito() {
            // Limpiar el panel
            depositoPanel.removeAll();

            // Mostrar producto recogido
            if (!expendedor.getDepositoSalida().isEmpty()) {
                Producto comprado = expendedor.getDepositoSalida().peek();
                Image imagenOriginal = comprado.getImagen();

                // Escalar imagen del producto a 64x64
                Image imagenEscalada = imagenOriginal.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(imagenEscalada));
                depositoPanel.add(imageLabel);
            }

            // Crear subpanel para el vuelto
            JPanel subPanelVuelto = new JPanel();
            subPanelVuelto.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            subPanelVuelto.setBackground(new Color(0x000F123, true));

            int cambio = expendedor.getVuelto();
            JLabel subLabel = new JLabel("$" + vuelto);
            subLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            subLabel.setForeground(Color.white);
            subPanelVuelto.add(subLabel);


            while (cambio >= 1000) {
                ImageIcon icono = new ImageIcon("icon/moneda1000.png");
                Image imagen = icono.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                JLabel label = new JLabel(new ImageIcon(imagen));
                subPanelVuelto.add(label);
                cambio -= 1000;
            }

            while (cambio >= 500) {
                ImageIcon icono = new ImageIcon("icon/moneda500.png");
                Image imagen = icono.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                JLabel label = new JLabel(new ImageIcon(imagen));
                subPanelVuelto.add(label);
                cambio -= 500;
            }

            while (cambio >= 100) {
                ImageIcon icono = new ImageIcon("icon/moneda100.png");
                Image imagen = icono.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                JLabel label = new JLabel(new ImageIcon(imagen));
                subPanelVuelto.add(label);
                cambio -= 100;
            }

            // Agregar subpanel al panel principal
            depositoPanel.add(subPanelVuelto);

            // Refrescar la vista
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

            for (int i = 0; i < depositos.size(); i++) {
                Deposito<Producto> deposito = depositos.get(i);

                if (deposito.size() == 0) {

                    for (int j = 0; j < numProductos; j++) {
                        Producto nuevoProducto = null;

                        switch (i) {
                            case Expendedor.COCA - 1:
                                nuevoProducto = new CocaCola(j, "CocaCola", Precios.COCA_COLA.getPrecio(), "CocaCola");
                                break;

                            case Expendedor.SPRITE - 1:
                                nuevoProducto = new Sprite(j, "Sprite", Precios.SPRITE.getPrecio(), "Sprite");
                                break;

                            case Expendedor.FANTA - 1:
                                nuevoProducto = new Fanta(j, "Fanta", Precios.FANTA.getPrecio(), "Fanta");
                                break;

                            case Expendedor.SUPER8 - 1:
                                nuevoProducto = new Super8(j, "Super8", Precios.SUPER8.getPrecio(), "Super8");
                                break;

                            case Expendedor.SNICKERS - 1:
                                nuevoProducto = new Snickers(j, "Snickers", Precios.SNICKERS.getPrecio(), "Snickers");
                                break;

                            default:
                                System.out.println("Índice desconocido: " + i);
                        }

                        if (nuevoProducto != null) {
                            deposito.addItem(nuevoProducto);
                        }
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

            int xStart = 30;
            int yStart = 40;
            int x = xStart;
            int y = yStart;

            int productosPorFila = 8;
            int espacioVertical = getHeight()/5 - 55;
            int espacioPrecio = 20;

            Font precioFont = new Font("Comic sans", Font.BOLD, 16);
            g.setFont(precioFont);
            Color precioColor = Color.BLACK;
            g.setColor(precioColor);

            for (int i = 0; i < expendedor.getProductos().size(); i++){
                Deposito<Producto> deposito = expendedor.getProductos().get(i);
                for (int j=0; j<deposito.getAllItems().size(); j++){
                    Producto p = deposito.getAllItems().get(j);
                    g.drawImage(p.getImagen(), x, y, 88, 88, this);
                    String precio = "$" + p.getPrecio();
                    int precioX = x + (88 - g.getFontMetrics().stringWidth(precio)) / 2;
                    int precioY = y + 88 + espacioPrecio;
                    g.drawString(precio, precioX, precioY);
                    x += 800/8;

                    if ((j+1)%productosPorFila == 0){
                        x = xStart;
                        y += espacioVertical;
                    }
                }
                yStart += espacioVertical + 20;
                y=yStart;
                x=xStart;
            }
        }
    }