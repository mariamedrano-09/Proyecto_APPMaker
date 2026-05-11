package view;

import javax.swing.*;
import java.awt.*;
import controller.DataStore;
import model.Producto;

public class ProductosPanel extends JPanel {
      private JList<Producto> listaProductos;
      

    public ProductosPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 112));

        JLabel lblTitle = new JLabel("Gestión de Productos", SwingConstants.CENTER);
        ImageIcon iconprod = new ImageIcon(getClass().getResource("/img/add.png"));
        Image imgprod = iconprod.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        lblTitle.setIcon(new ImageIcon(imgprod));
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        listaProductos = new JList<>(DataStore.getProductos().toArray(new Producto[0]));
        
        listaProductos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        listaProductos.setBackground(new Color(240, 248, 255));
        listaProductos.setSelectionBackground(new Color(70, 130, 180));
        listaProductos.setSelectionForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(listaProductos);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);

        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.setBackground(new Color(0, 120, 255));
        btnAgregar.setForeground(Color.WHITE);

        JButton btnEliminar = new JButton("Eliminar Seleccionado");
        btnEliminar.setBackground(new Color(255, 140, 0));
        btnEliminar.setForeground(Color.WHITE);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(new Color(200, 50, 50));
        btnRegresar.setForeground(Color.WHITE);

        bottom.add(btnAgregar);
        bottom.add(btnEliminar);
        bottom.add(btnRegresar);
        add(bottom, BorderLayout.SOUTH);

       btnAgregar.addActionListener(e -> {
    String nombre = JOptionPane.showInputDialog(this, "Nombre del producto:");
    String precioStr = JOptionPane.showInputDialog(this, "Precio del producto:");
    String categoria = JOptionPane.showInputDialog(this, "Categoría del producto:");

    if(nombre != null && precioStr != null && categoria != null) {
        try {
            double precio = Double.parseDouble(precioStr);
            Producto nuevo = new Producto(nombre, precio, categoria);
            DataStore.agregarProducto(nuevo);
            actualizarLista();
            JOptionPane.showMessageDialog(this, "Producto agregado: " + nuevo);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio inválido. Intenta de nuevo.");
        }
    }
});

       btnEliminar.addActionListener(e -> {
    Producto seleccionado = listaProductos.getSelectedValue(); // ahora es Producto
    if(seleccionado != null) {
        DataStore.eliminarProducto(seleccionado); // elimina el objeto
        actualizarLista();
        JOptionPane.showMessageDialog(this, "Producto eliminado: " + seleccionado.getNombre());
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.");
    }
});

        btnRegresar.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.add(new AdminPanel(), BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }

    private void actualizarLista() {
    listaProductos.setListData(DataStore.getProductos().toArray(new Producto[0]));
}

}
