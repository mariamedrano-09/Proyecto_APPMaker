package view;

import controller.DataStore;
import javax.swing.*;
import java.awt.*;
import model.Producto;

public class CatalogPanel extends JPanel {
    public CatalogPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 112));

        JLabel lblTitle = new JLabel("Catálogo de Productos", SwingConstants.CENTER);
        ImageIcon iconcatal = new ImageIcon(getClass().getResource("/img/catalogo.png"));
        Image imgcatal = iconcatal.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        lblTitle.setIcon(new ImageIcon(imgcatal));
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        JList<Producto> listaProductos = new JList<>(DataStore.getProductos().toArray(new Producto[0]));

        listaProductos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        listaProductos.setBackground(new Color(240, 248, 255));
        listaProductos.setSelectionBackground(new Color(70, 130, 180));
        listaProductos.setSelectionForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(listaProductos);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scroll, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);

        JButton btnAddCarrito = new JButton("Agregar al Carrito");
        btnAddCarrito.setBackground(new Color(0, 120, 255));
        btnAddCarrito.setForeground(Color.WHITE);
        btnAddCarrito.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(new Color(200, 50, 50));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        bottom.add(btnAddCarrito);
        bottom.add(btnRegresar);
        add(bottom, BorderLayout.SOUTH);

        // Acción de agregar al carrito    
        btnAddCarrito.addActionListener(e -> {
    Producto seleccionado = listaProductos.getSelectedValue();
    if(seleccionado != null) {
        String cantidadStr = JOptionPane.showInputDialog(this, "Ingrese cantidad:");
        try {
            int cantidad = Integer.parseInt(cantidadStr);
            if(cantidad > 0) {
                DataStore.agregarAlCarrito(seleccionado, cantidad);
                JOptionPane.showMessageDialog(this, seleccionado.getNombre() + " x" + cantidad + " agregado al carrito.");
            } else {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.");
            }
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un producto primero.");
    }
});


        // Acción de regresar al panel principal
        btnRegresar.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.add(new UserPanel(), BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }
}
