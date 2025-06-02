package org.example.model;

import org.example.exceptions.NoHayProductoException;
import org.example.exceptions.PagoIncorrectoException;
import org.example.exceptions.PagoInsuficienteException;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que simula un expendedor de productos.

 */
public class Expendedor {

    /**
     * Constante que representa el índice de CocaCola en el expendedor.
     */
    public static final int COCA = 1;

    /**
     * Constante que representa el índice de Sprite en el expendedor.
     */
    public static final int SPRITE = 2;

    /**
     * Constante que representa el índice de Fanta en el expendedor.
     */
    public static final int FANTA = 3;

    /**
     * Constante que representa el índice de Super8 en el expendedor.
     */
    public static final int SUPER8 = 4;

    /**
     * Constante que representa el índice de Snickers en el expendedor.
     */
    public static final int SNICKERS = 5;

    /**
     * Depósito que contiene las monedas de vuelto que se dan al comprador.
     */
    private Deposito<Moneda> monedasVuelto;

    private Deposito<Producto> depositoSalida;

    /**
     * Lista de depósitos de productos disponibles en el expendedor.
     */
    private List<Deposito<Producto>> productos;

    private int numProductos;
    /**
     * Constructor del expendedor. Inicializa los depósitos de productos y monedas.
     *
     * @param numProductos Número de productos a agregar al expendedor.
     */
    public Expendedor(int numProductos) {
        productos = new ArrayList<>();
        monedasVuelto = new Deposito<>();
        depositoSalida = new Deposito<>();
        this.numProductos = numProductos;

        // Inicializa los depósitos de productos para cada tipo de producto.
        for (int i = 0; i < 5; i++) {
            productos.add(new Deposito<Producto>());
        }

        // Añade productos al expendedor.
        for (int i = 0; i < numProductos; i++) {
            productos.get(COCA - 1).addItem(new CocaCola(i, "CocaCola", Precios.COCA_COLA.getPrecio(), "CocaCola"));
            productos.get(SPRITE - 1).addItem(new Sprite(i, "Sprite", Precios.SPRITE.getPrecio(), "Sprite"));
            productos.get(FANTA - 1).addItem(new Fanta(i, "Fanta", Precios.FANTA.getPrecio(), "Fanta"));
            productos.get(SUPER8 - 1).addItem(new Super8(i, "Super8", Precios.SUPER8.getPrecio(), "Super8"));
            productos.get(SNICKERS - 1).addItem(new Snickers(i, "Snickers", Precios.SNICKERS.getPrecio(), "Snicker"));
        }
    }


    public void productoSalida(Producto producto){
        depositoSalida.addItem(producto);
    }
    /**
     * Permite comprar un producto a través del expendedor si el pago es correcto.
     *
     * @param m La moneda utilizada para la compra del producto.
     * @param nombreProducto,  El índice del producto que se desea comprar.
     * @return El producto comprado.
     * @throws PagoIncorrectoException Si la moneda es nula.
     * @throws PagoInsuficienteException Si la moneda proporcionada es insuficiente.
     * @throws NoHayProductoException Si no hay producto disponible en el expendedor.
     */

    public void comprarProducto(Moneda m, String nombreProducto)
            throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {

        // Verifica si la moneda es nula.
        if (m == null) {
            throw new PagoIncorrectoException("No tienes moneda (Moneda nula)");
        }
        int cual = obtenerIndiceProducto(nombreProducto);
        if(cual == -1){
            throw new NoHayProductoException("Producto no disponible");
        }

        // Obtiene el precio del producto basado en el índice.
        int precioProductos = Precios.values()[cual - 1].getPrecio();

        // Obtiene el depósito de productos del tipo seleccionado.
        Deposito<Producto> depositoProducto = productos.get(cual - 1);

        // Verifica si el depósito de productos está vacío.
        if (depositoProducto.isEmpty()) {
            throw new NoHayProductoException("No hay producto en el depósito");
        }

        // Obtiene el valor de la moneda.
        int valMoneda = m.getValor();

        // Verifica si la moneda es insuficiente para cubrir el precio.
        if (valMoneda < precioProductos) {
            throw new PagoInsuficienteException("Valor de moneda insuficiente");
        }

        // Calcula el cambio y lo devuelve en monedas del mas alto valor.
        int cambio = valMoneda - precioProductos;
        Moneda nuevamoneda=null;
        while (cambio >= 1000){
            nuevamoneda=new Moneda1000();
            monedasVuelto.addItem(nuevamoneda);
            System.out.println("el numero de serie de la moneda es:" +nuevamoneda.getSerie());
            cambio = cambio - 1000;
        }

        while (cambio >= 500){
            nuevamoneda=new Moneda500();
            monedasVuelto.addItem(nuevamoneda);
            System.out.println("el numero de serie de la moneda es:" +nuevamoneda.getSerie());
            cambio = cambio - 500;
        }

        while (cambio >= 100) {
            nuevamoneda=new Moneda100();
            System.out.println("el numero de serie de la moneda es:" +nuevamoneda.getSerie());
            monedasVuelto.addItem(nuevamoneda);
            cambio = cambio - 100;
        }

        Producto productoComprado =  depositoProducto.getItem();
        productoSalida(productoComprado);
        System.out.println("Producto comprado y añadido al deposito de productos comprados: " + productoComprado.getNombre());
        System.out.println("el numero de serie de "+ nombreProducto +" es: "+ productoComprado.getCodigo());
    }

    /**
     * Obtiene el vuelto.
     *
     * @return una moneda de vuelto o nada si ya no quedan.
     */

    private int obtenerIndiceProducto(String nombreProducto) {
        switch (nombreProducto) {
            case "Coca Cola":
                return COCA;
            case "Sprite":
                return SPRITE;
            case "Fanta":
                return FANTA;
            case "Super8":
                return SUPER8;
            case "Snickers":
                return SNICKERS;
            default:
                return -1;
        }
    }

    public List<Moneda> getVueltoEnMonedas(){
        List<Moneda> vueltoMonedas = new ArrayList<>(monedasVuelto.getAllItems());
        monedasVuelto.clearItems();
        return vueltoMonedas;
    }

    public int getVuelto() {
        int totalVuelto = 0;
        for(Moneda moneda : monedasVuelto.getAllItems()){
            totalVuelto += moneda.getValor();
        }
        return totalVuelto;
    }

    public Producto getProducto(){
        return depositoSalida.getItem();
    }

    public List<Deposito<Producto>> getProductos(){
        return productos;
    }

    public Deposito<Producto> getDepositoSalida(){
        return depositoSalida;
    }

    public int getNumProductos(){
        return numProductos;
    }
}