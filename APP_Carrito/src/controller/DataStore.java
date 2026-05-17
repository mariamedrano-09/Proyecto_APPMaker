package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

import model.ItemCarrito;
import model.Producto;

public class DataStore {

    //  PRODUCTOS EN MEMORIA
    private static List<Producto> productos = new ArrayList<>();

    // CARRITO
    private static List<ItemCarrito> carrito = new ArrayList<>();

    //  HISTORIAL
    private static List<String> historial = new ArrayList<>();

    //  DESEOS
    private static List<String> deseos = new ArrayList<>();


    /* ================= PRODUCTOS ================= */

    public static void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public static void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    public static List<Producto> getProductos() {
        return productos;
    }

    public static void limpiarProductos() {
        productos.clear();
    }


    /* ================= CARRITO ================= */

    public static void agregarAlCarrito(Producto producto, int cantidad) {

        for(ItemCarrito item : carrito) {

            if(item.getProducto().equals(producto)) {

                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }

        carrito.add(new ItemCarrito(producto, cantidad));
    }

    public static void eliminarDelCarrito(ItemCarrito item) {
        carrito.remove(item);
    }

    public static List<ItemCarrito> getCarrito() {
        return carrito;
    }

    public static void vaciarCarrito() {
        carrito.clear();
    }


    /* ================= HISTORIAL ================= */

    public static void agregarAlHistorial(String item) {
        historial.add(item);
    }

    public static List<String> getHistorial() {
        return historial;
    }


    /* ================= DESEOS ================= */

    public static void agregarADeseos(String producto) {
        deseos.add(producto);
    }

    public static void eliminarDeDeseos(String producto) {
        deseos.remove(producto);
    }

    public static List<String> getDeseos() {
        return deseos;
    }
    
    
 // ================= COLA DE PEDIDOS =================
        private static Queue<model.Pedido> colaPedidos = new LinkedList<>();

        public static void agregarPedido(model.Pedido pedido) {
            colaPedidos.add(pedido); // ENCOLAR (FIFO)
        }

        public static model.Pedido procesarPedido() {
            return colaPedidos.poll(); // DESENCOLAR
        }

        public static Queue<model.Pedido> getColaPedidos() {
            return colaPedidos;
        }
        }