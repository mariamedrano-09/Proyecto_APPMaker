/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.Producto;
import util.ArchivoProductos;
import controller.DataStore;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ProductCardAdmin extends JPanel {

    private final Producto producto;
    private final Runnable onUpdate; // para refrescar panel

    public ProductCardAdmin(Producto producto, Runnable onUpdate) {
        this.producto = producto;
        this.onUpdate = onUpdate;

        setOpaque(false);
        setPreferredSize(new Dimension(220, 320));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //---------------- IMAGEN ----------------
        JLabel lblImg = new JLabel();
        lblImg.setAlignmentX(CENTER_ALIGNMENT);
        lblImg.setPreferredSize(new Dimension(180, 120));
        lblImg.setIcon(loadScaledIcon(producto.getImagenPath(), 180, 120));

        //---------------- INFO ----------------
        JLabel lblNombre = new JLabel(producto.getNombre());
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombre.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblPrecio = new JLabel("$ " + String.format("%,.0f", producto.getPrecio()));
        lblPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblPrecio.setForeground(new Color(0, 110, 255));
        lblPrecio.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblCat = new JLabel(producto.getCategoria());
        lblCat.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblCat.setAlignmentX(CENTER_ALIGNMENT);

        //---------------- BOTONES ADMIN ----------------
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(255, 170, 0));
        btnEditar.setForeground(Color.WHITE);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(200, 50, 50));
        btnEliminar.setForeground(Color.WHITE);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        acciones.setOpaque(false);
        acciones.add(btnEditar);
        acciones.add(btnEliminar);

        //---------------- ENSAMBLAR ----------------
        add(lblImg);
        add(Box.createVerticalStrut(6));
        add(lblNombre);
        add(lblPrecio);
        add(lblCat);
        add(Box.createVerticalGlue());
        add(acciones);

        //---------------- EVENTOS ----------------

        // EDITAR
        btnEditar.addActionListener(e -> editarProducto());

        // ELIMINAR
        btnEliminar.addActionListener(e -> {
            int op = JOptionPane.showConfirmDialog(
                    this,
                    "¿Eliminar producto?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (op == JOptionPane.YES_OPTION) {
                DataStore.eliminarProducto(producto);
                ArchivoProductos.sobrescribirProductos(DataStore.getProductos());
                onUpdate.run();
            }
        });
    }

    private void editarProducto() {
        String nuevoNombre = JOptionPane.showInputDialog(this, "Nombre:", producto.getNombre());
        String nuevoPrecioStr = JOptionPane.showInputDialog(this, "Precio:", producto.getPrecio());
        String nuevaCategoria = JOptionPane.showInputDialog(this, "Categoría:", producto.getCategoria());

        try {
            if (nuevoNombre != null && nuevoPrecioStr != null && nuevaCategoria != null) {
                double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);

                Producto actualizado = new Producto(
                        nuevoNombre,
                        nuevoPrecio,
                        nuevaCategoria,
                        producto.getImagenPath()
                );

                DataStore.eliminarProducto(producto);
                DataStore.agregarProducto(actualizado);
                ArchivoProductos.sobrescribirProductos(DataStore.getProductos());
                onUpdate.run();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }

    private ImageIcon loadScaledIcon(String path, int w, int h) {
        try {
            if (path == null || path.isEmpty()) {
                return new ImageIcon(new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB));
            }
            ImageIcon icon = new ImageIcon(getClass().getResource(path));
            Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            return new ImageIcon(new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.setColor(new Color(220, 220, 220));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
    }
}