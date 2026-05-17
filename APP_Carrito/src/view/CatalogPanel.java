package view;

import controller.DataStore;
import javax.swing.*;
import java.awt.*;
import model.Producto;
import util.ArchivoProductos;

public class CatalogPanel extends JPanel {

    private JPanel grid; // contenedor de tarjetas

    public CatalogPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 112)); // tu estilo 
        // Encabezado
        JLabel lblTitle = new JLabel("Catálogo de Productos", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Asegurar productos cargados (para usuarios que no pasan por ProductosPanel)
        if (DataStore.getProductos().isEmpty()) {
            DataStore.limpiarProductos();
            for (Producto p : ArchivoProductos.leerProductos()) {
                DataStore.agregarProducto(p);
            }
        }

        // Contenedor “wrap” de tarjetas
        grid = new JPanel(new WrapLayout(FlowLayout.LEFT, 18, 18));
        grid.setOpaque(false);
        grid.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Llenar tarjetas
        refrescarTarjetas();

        JScrollPane scroll = new JScrollPane(grid);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);

        // Barra inferior
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(new Color(200, 50, 50));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        bottom.add(btnRegresar);
        add(bottom, BorderLayout.SOUTH);

        btnRegresar.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.add(new Usuarios_Panel(), BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }

    private void refrescarTarjetas() {
        grid.removeAll();
        for (Producto p : DataStore.getProductos()) {
            grid.add(new Product_Tarjetas(p));
        }
        grid.revalidate();
        grid.repaint();
    }
}