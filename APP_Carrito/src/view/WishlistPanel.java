package view;

import javax.swing.*;
import java.awt.*;
import controller.DataStore;

public class WishlistPanel extends JPanel {
    private JList<String> listaDeseos;

    public WishlistPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 112)); // Fondo azul oscuro

        // Título
        JLabel lblTitle = new JLabel("Lista de Deseos", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Lista dinámica desde DataStore
        listaDeseos = new JList<>(DataStore.getDeseos().toArray(new String[0]));
        listaDeseos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        listaDeseos.setBackground(new Color(240, 248, 255));
        listaDeseos.setSelectionBackground(new Color(255, 105, 180)); // Rosa
        listaDeseos.setSelectionForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(listaDeseos);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scroll, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);

        JButton btnEliminar = new JButton("Eliminar Seleccionado");
        btnEliminar.setBackground(new Color(255, 80, 150));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(new Color(200, 50, 50));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        bottom.add(btnEliminar);
        bottom.add(btnRegresar);
        add(bottom, BorderLayout.SOUTH);

        // Acción de eliminar producto
        btnEliminar.addActionListener(e -> {
            String seleccionado = listaDeseos.getSelectedValue();
            if(seleccionado != null) {
                DataStore.eliminarDeDeseos(seleccionado);
                actualizarLista();
                JOptionPane.showMessageDialog(this, seleccionado + " eliminado de la lista de deseos.");
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.");
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

    // Método para refrescar la lista
    private void actualizarLista() {
        listaDeseos.setListData(DataStore.getDeseos().toArray(new String[0]));
    }
}
