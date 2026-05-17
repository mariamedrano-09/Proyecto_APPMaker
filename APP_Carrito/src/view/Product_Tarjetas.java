/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.DataStore;
import model.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Product_Tarjetas extends JPanel {

    private final Producto producto;

    public Product_Tarjetas(Producto producto) {
        this.producto = producto;

        setOpaque(false);
        setPreferredSize(new Dimension(220, 300));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //---------------- IMAGEN ----------------
        JLabel lblImg = new JLabel();
        lblImg.setAlignmentX(CENTER_ALIGNMENT);
        lblImg.setPreferredSize(new Dimension(180, 120));
        lblImg.setHorizontalAlignment(SwingConstants.CENTER);
        lblImg.setIcon(loadScaledIcon(producto.getImagenPath(), 180, 120));

        //---------------- NOMBRE ----------------
        JLabel lblNombre = new JLabel(producto.getNombre());
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombre.setForeground(new Color(30, 30, 30));
        lblNombre.setAlignmentX(CENTER_ALIGNMENT);

        //---------------- PRECIO ----------------
        JLabel lblPrecio = new JLabel("$ " + String.format("%,.0f", producto.getPrecio()));
        lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPrecio.setForeground(new Color(0, 110, 255));
        lblPrecio.setAlignmentX(CENTER_ALIGNMENT);

        //---------------- CATEGORIA ----------------
        JLabel lblCat = new JLabel(producto.getCategoria());
        lblCat.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblCat.setForeground(new Color(90, 90, 90));
        lblCat.setAlignmentX(CENTER_ALIGNMENT);

        //---------------- BOTONES ----------------
        JButton btnCarrito = new JButton("Añadir");
        btnCarrito.setBackground(new Color(0, 120, 255));
        btnCarrito.setForeground(Color.WHITE);
        btnCarrito.setFocusPainted(false);

        JButton btnDeseos = new JButton("❤");
        btnDeseos.setBackground(new Color(255, 80, 150));
        btnDeseos.setForeground(Color.WHITE);
        btnDeseos.setFocusPainted(false);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        acciones.setOpaque(false);
        acciones.add(btnCarrito);
        acciones.add(btnDeseos);

        //---------------- ENSAMBLAR ----------------
        add(lblImg);
        add(Box.createVerticalStrut(8));
        add(lblNombre);
        add(Box.createVerticalStrut(4));
        add(lblPrecio);
        add(lblCat);
        add(Box.createVerticalGlue());
        add(acciones);

        //---------------- EVENTOS ----------------
        btnCarrito.addActionListener(e -> {
            String cantidadStr = JOptionPane.showInputDialog(this, "Ingrese cantidad:");
            try {
                int cantidad = Integer.parseInt(cantidadStr);
                if (cantidad > 0) {
                    DataStore.agregarAlCarrito(producto, cantidad);
                    JOptionPane.showMessageDialog(this,
                            producto.getNombre() + " x" + cantidad + " agregado al carrito.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida");
            }
        });

        btnDeseos.addActionListener(e -> {
            DataStore.agregarADeseos(producto.getNombre());
            JOptionPane.showMessageDialog(this,
                    producto.getNombre() + " agregado a deseos");
        });
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

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = 20;
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
        g2.setColor(new Color(220, 220, 220));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

        g2.dispose();
    }
}