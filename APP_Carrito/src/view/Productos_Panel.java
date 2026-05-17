package view;

import controller.DataStore;
import model.Producto;
import util.ArchivoProductos;

import javax.swing.*;
import java.awt.*;

public class Productos_Panel extends JPanel {

    private JPanel grid; // contenedor de tarjetas

    public Productos_Panel() {
        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 112));

        // ---------------- TÍTULO ----------------
        JLabel lblTitle = new JLabel("Gestión de Productos", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        // ---------------- GRID DE TARJETAS ----------------
        grid = new JPanel(new WrapLayout(FlowLayout.LEFT, 18, 18));
        grid.setOpaque(false);
        grid.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JScrollPane scroll = new JScrollPane(grid);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);

        // ---------------- BOTONES INFERIORES ----------------
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);

        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.setBackground(new Color(0, 120, 255));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(new Color(200, 50, 50));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        bottom.add(btnAgregar);
        bottom.add(btnRegresar);
        add(bottom, BorderLayout.SOUTH);

        // ---------------- CARGA INICIAL ----------------
        cargarProductos();
        refrescarTarjetas();

        // ---------------- EVENTOS ----------------

        // AGREGAR PRODUCTO
        btnAgregar.addActionListener(e -> agregarProducto());

        // REGRESAR AL PANEL ADMIN
        btnRegresar.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.add(new Admin_Panel(), BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }

    // =====================================================
    // MÉTODOS
    // =====================================================

    private void cargarProductos() {
        DataStore.limpiarProductos();
        for (Producto p : ArchivoProductos.leerProductos()) {
            DataStore.agregarProducto(p);
        }
    }

    private void refrescarTarjetas() {
        grid.removeAll();

        for (Producto p : DataStore.getProductos()) {
            grid.add(new ProductCardAdmin(p, this::refrescarTarjetas));
        }

        grid.revalidate();
        grid.repaint();
    }

    private void agregarProducto() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del producto:");
        String precioStr = JOptionPane.showInputDialog(this, "Precio:");
        String categoria = JOptionPane.showInputDialog(this, "Categoría:");
        String imagen = JOptionPane.showInputDialog(this, "Ruta imagen (Ej: /img/laptop.png):");

        try {
            if (nombre != null && precioStr != null && categoria != null) {
                double precio = Double.parseDouble(precioStr);

                Producto nuevo = new Producto(nombre, precio, categoria, imagen);
                DataStore.agregarProducto(nuevo);
                ArchivoProductos.guardarProducto(nuevo);

                refrescarTarjetas();
                JOptionPane.showMessageDialog(this, "Producto agregado correctamente");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }
}

