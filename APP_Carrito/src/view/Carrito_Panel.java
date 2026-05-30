package view;

import javax.swing.*;
import java.awt.*;
import controller.DataStore;
import model.ItemCarrito;
import model.Pedido;
import java.util.ArrayList;

public class Carrito_Panel extends JPanel {

    private JPanel panelTarjetas;
    private JLabel lblTotal;

    public Carrito_Panel() {

        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 112));

        // TÍTULO
        JLabel lblTitle = new JLabel("Carrito de Compras", SwingConstants.CENTER);

        ImageIcon iconcart =
                new ImageIcon(getClass().getResource("/img/carro.png"));

        Image imgcart =
                iconcart.getImage().getScaledInstance(
                        32, 32, Image.SCALE_SMOOTH);

        lblTitle.setIcon(new ImageIcon(imgcart));

        lblTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 26));

        lblTitle.setForeground(Color.WHITE);

        lblTitle.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 0, 20, 0));

        add(lblTitle, BorderLayout.NORTH);

        // PANEL DE TARJETAS
        panelTarjetas = new JPanel();

        panelTarjetas.setLayout(
                new BoxLayout(
                        panelTarjetas,
                        BoxLayout.Y_AXIS));

        panelTarjetas.setBackground(
                new Color(240, 248, 255));

        JScrollPane scroll =
                new JScrollPane(panelTarjetas);

        scroll.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 20, 10, 20));

        add(scroll, BorderLayout.CENTER);

        // PANEL INFERIOR
        JPanel bottom = new JPanel();

        bottom.setOpaque(false);

        lblTotal = new JLabel("TOTAL: $0");

        lblTotal.setForeground(Color.WHITE);

        lblTotal.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        18));

        JButton btnComprar =
                new JButton("Finalizar Compra");

        btnComprar.setBackground(
                new Color(0, 180, 100));

        btnComprar.setForeground(
                Color.WHITE);

        btnComprar.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        14));

        JButton btnRegresar =
                new JButton("Regresar");

        btnRegresar.setBackground(
                new Color(200, 50, 50));

        btnRegresar.setForeground(
                Color.WHITE);

        btnRegresar.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        14));

        bottom.add(lblTotal);
        bottom.add(Box.createHorizontalStrut(20));
        bottom.add(btnComprar);
        bottom.add(btnRegresar);

        add(bottom, BorderLayout.SOUTH);

        // CARGAR TARJETAS
        actualizarLista();

        // FINALIZAR COMPRA
        btnComprar.addActionListener(e -> {

            if (DataStore.getCarrito().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "El carrito está vacío.");

                return;
            }

            double total = 0;

            for (ItemCarrito item :
                    DataStore.getCarrito()) {

                total += item.getCantidad()
                        * item.getProducto().getPrecio();
            }

            Pedido pedido =
                    new Pedido(
                            new ArrayList<>(
                                    DataStore.getCarrito()),
                            total);

            DataStore.agregarPedido(pedido);

            DataStore.agregarAlHistorial(
                    pedido.toString());

            DataStore.vaciarCarrito();

            actualizarLista();

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido enviado correctamente.\n"
                    + "Total: $"
                    + String.format("%,.0f", total));
        });

        // REGRESAR
        btnRegresar.addActionListener(e -> {

            JFrame frame =
                    (JFrame) SwingUtilities
                            .getWindowAncestor(this);

            frame.getContentPane().removeAll();

            frame.add(
                    new Usuarios_Panel(),
                    BorderLayout.CENTER);

            frame.revalidate();
            frame.repaint();
        });
    }

    // ACTUALIZAR TARJETAS
    private void actualizarLista() {

        panelTarjetas.removeAll();

        double total = 0;

        for (ItemCarrito item :
                DataStore.getCarrito()) {

            panelTarjetas.add(
                    new TarjetaCarrito(
                            item,
                            this::actualizarLista));

            panelTarjetas.add(
                    Box.createVerticalStrut(10));

            total += item.getCantidad()
                    * item.getProducto().getPrecio();
        }

        lblTotal.setText(
                "TOTAL: $"
                + String.format("%,.0f", total));

        panelTarjetas.revalidate();
        panelTarjetas.repaint();
    }
}