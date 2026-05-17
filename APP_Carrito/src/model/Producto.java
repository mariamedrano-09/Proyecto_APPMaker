package model;

import java.util.Objects;

public class Producto {
    private String nombre;
    private double precio;
    private String categoria;
    private String imagenPath; // NUEVO: ruta recurso o ruta relativa

    // Constructor anterior (compatibilidad)
    public Producto(String nombre, double precio, String categoria) {
        this(nombre, precio, categoria, null);
    }

    public Producto(String nombre, double precio, String categoria, String imagenPath) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.imagenPath = imagenPath;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }
    public String getImagenPath() { return imagenPath; }

    // Para guardar en archivo (NO usar toString para UI)
    public String serialize() {
        // nombre;precio;categoria;imagen
        return nombre + ";" + precio + ";" + categoria + ";" + (imagenPath == null ? "" : imagenPath);
    }

    // Para mostrar en UI (tarjeta o lista)
    @Override
    public String toString() {
        return nombre + " - $" + String.format("%,.0f", precio) + " (" + categoria + ")";
    }

    // Importante: DataStore.agregarAlCarrito compara equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto p = (Producto) o;
        return Objects.equals(nombre, p.nombre) && Objects.equals(categoria, p.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, categoria);
    }
}