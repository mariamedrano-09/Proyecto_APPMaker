package controller;

import java.util.ArrayList;
import java.util.List;
import model.ItemCarrito;
import model.Producto;

public class DataStore {
    private static List<String> historial = new ArrayList<>();
    private static List<String> deseos = new ArrayList<>();
    private static List<String> usuarios = new ArrayList<>();
    private static List<Producto> productos = new ArrayList<>();
    private static List<ItemCarrito> carrito = new ArrayList<>();
    

    public static void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public static void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    public static List<Producto> getProductos() {
        return productos;
    }


    // Métodos para el carrito    
    public static void agregarAlCarrito(Producto producto, int cantidad) {
    // Si ya existe el producto en el carrito, sumamos la cantidad
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
    // Métodos para historial
    public static void agregarAlHistorial(String producto) {
        historial.add(producto);
    }

    public static List<String> getHistorial() {
        return historial;
    }
    public static void agregarADeseos(String producto) {
    deseos.add(producto);
}

    public static void eliminarDeDeseos(String producto) {
        deseos.remove(producto);
    }

    public static List<String> getDeseos() {
        return deseos;
    }
    
    
    public static void agregarUsuario(String usuario) {
    usuarios.add(usuario);
    }

    public static void eliminarUsuario(String usuario) {
        usuarios.remove(usuario);
    }

    public static List<String> getUsuarios() {
        return usuarios;
    }

}
