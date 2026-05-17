package view;

import javax.swing.*;
import java.awt.*;
import controller.DataStore;

public class Compras_Panel extends JPanel {
    private JList<String> listaHistorial;

    public Compras_Panel() {
        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 112)); // Fondo azul oscuro

        // Título
        JLabel lblTitle = new JLabel("Historial de Compras", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Lista dinámica desde DataStore
        listaHistorial = new JList<>(DataStore.getHistorial().toArray(new String[0]));
        listaHistorial.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        listaHistorial.setBackground(new Color(240, 248, 255));
        listaHistorial.setSelectionBackground(new Color(70, 130, 180));
        listaHistorial.setSelectionForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(listaHistorial);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scroll, BorderLayout.CENTER);

        // Panel inferior con botón regresar
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(new Color(200, 50, 50));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        bottom.add(btnRegresar);
        add(bottom, BorderLayout.SOUTH);

        // Acción de regresar al panel principal
        btnRegresar.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.add(new Usuarios_Panel(), BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }

    // Método para refrescar la lista en caso de que se actualice
    public void actualizarLista() {
        listaHistorial.setListData(DataStore.getHistorial().toArray(new String[0]));
    }
}
