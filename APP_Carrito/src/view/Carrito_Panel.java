package view;

import javax.swing.*;
import java.awt.*;
import controller.DataStore;
import model.ItemCarrito;

public class Carrito_Panel extends JPanel {  
    private JList<ItemCarrito> listaCarrito;


    public Carrito_Panel() {
        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 112));

        // Título
        JLabel lblTitle = new JLabel("Carrito de Compras", SwingConstants.CENTER);
        ImageIcon iconcart = new ImageIcon(getClass().getResource("/img/carro.png"));
        Image imgcart = iconcart.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        lblTitle.setIcon(new ImageIcon(imgcart));
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Lista dinámica desde DataStore
        listaCarrito = new JList<>(DataStore.getCarrito().toArray(new ItemCarrito[0]));
        listaCarrito.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        listaCarrito.setBackground(new Color(240, 248, 255));
        listaCarrito.setSelectionBackground(new Color(70, 130, 180));
        listaCarrito.setSelectionForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(listaCarrito);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scroll, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);

        JButton btnComprar = new JButton("Finalizar Compra");
        btnComprar.setBackground(new Color(0, 180, 100));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JButton btnCancelar = new JButton("Eliminar Seleccionado");
        btnCancelar.setBackground(new Color(255, 140, 0));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(new Color(200, 50, 50));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        bottom.add(btnComprar);
        bottom.add(btnCancelar);
        bottom.add(btnRegresar);
        add(bottom, BorderLayout.SOUTH);

        // Acción de finalizar compra
      btnComprar.addActionListener(e -> {
    double total = 0;
    for (ItemCarrito item : DataStore.getCarrito()) {
        total += item.getProducto().getPrecio() * item.getCantidad();
    }
    // Crear pedido
    model.Pedido pedido = new model.Pedido(
            new java.util.ArrayList<>(DataStore.getCarrito()),
            total
    );

    // ENCOLAR pedido
    DataStore.agregarPedido(pedido);
    // Guardar en historial
    DataStore.agregarAlHistorial(pedido.toString());

    // Vaciar carrito
    DataStore.vaciarCarrito();
    actualizarLista();

    JOptionPane.showMessageDialog(this,
            "Pedido agregado a la cola de procesamiento.\nTotal: $" + String.format("%,.0f", total));
});

        

        // Acción de eliminar producto
        btnCancelar.addActionListener(e -> {
            ItemCarrito seleccionado = listaCarrito.getSelectedValue();
    if(seleccionado != null) {
        DataStore.eliminarDelCarrito(seleccionado);
        actualizarLista();
        JOptionPane.showMessageDialog(this, "Producto eliminado del carrito.");
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.");
    }
});

        // Acción de regresar al panel principal
        btnRegresar.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.add(new Usuarios_Panel(), BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }

    // Método para refrescar la lista
    private void actualizarLista() {
    listaCarrito.setListData(DataStore.getCarrito().toArray(new ItemCarrito[0]));
}
}
