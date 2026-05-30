package view;

import controller.DataStore;
import java.awt.*;
import javax.swing.*;
import model.ItemCarrito;

public class TarjetaCarrito extends JPanel {

    public TarjetaCarrito(
            ItemCarrito item,
            Runnable actualizarLista) {

        setLayout(new BorderLayout(10,10));

        setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        120));

        setBackground(Color.WHITE);

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220,220,220)),
                        BorderFactory.createEmptyBorder(
                                10,10,10,10)));

        JLabel lblNombre =
                new JLabel(
                        item.getProducto().getNombre());

        lblNombre.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        JLabel lblCantidad =
                new JLabel(
                        "Cantidad: "
                        + item.getCantidad());

        JLabel lblPrecio =
                new JLabel(
                        "Precio: $"
                        + item.getProducto()
                              .getPrecio());

        double subtotal =
                item.getCantidad()
                * item.getProducto()
                      .getPrecio();

        JLabel lblSubtotal =
                new JLabel(
                        "Subtotal: $"
                        + subtotal);

        JPanel info = new JPanel(
                new GridLayout(4,1));

        info.setOpaque(false);

        info.add(lblNombre);
        info.add(lblCantidad);
        info.add(lblPrecio);
        info.add(lblSubtotal);

        JButton btnEliminar =
                new JButton("Eliminar");

        btnEliminar.setBackground(
                new Color(220,50,50));

        btnEliminar.setForeground(
                Color.WHITE);

        btnEliminar.addActionListener(e -> {

            DataStore.eliminarDelCarrito(item);

            actualizarLista.run();
        });

        add(info, BorderLayout.CENTER);
        add(btnEliminar, BorderLayout.EAST);
    }
}