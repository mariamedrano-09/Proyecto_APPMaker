package view;

import javax.swing.*;
import java.awt.*;
import controller.DataStore;

public class Usuarios extends JPanel {
    private JList<String> listaUsuarios;

    public Usuarios() {
        setLayout(new BorderLayout());
        setBackground(new Color(25, 25, 112));

        // Título
        JLabel lblTitle = new JLabel("Gestión de Usuarios", SwingConstants.CENTER);
        ImageIcon iconuser = new ImageIcon(getClass().getResource("/img/admin.png"));
        Image imguser = iconuser.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        lblTitle.setIcon(new ImageIcon(imguser));
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Lista dinámica de usuarios desde DataStore
        listaUsuarios = new JList<>(DataStore.getUsuarios().toArray(new String[0]));
        listaUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        listaUsuarios.setBackground(new Color(240, 248, 255));
        listaUsuarios.setSelectionBackground(new Color(70, 130, 180));
        listaUsuarios.setSelectionForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(listaUsuarios);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scroll, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);

        JButton btnAgregar = new JButton("Agregar Usuario");
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

        // Acción de agregar usuario
        btnAgregar.addActionListener(e -> {
            String nuevoUsuario = JOptionPane.showInputDialog(this, "Ingrese nombre del nuevo usuario:");
            if(nuevoUsuario != null && !nuevoUsuario.trim().isEmpty()) {
                DataStore.agregarUsuario(nuevoUsuario);
                actualizarLista();
                JOptionPane.showMessageDialog(this, "Usuario agregado: " + nuevoUsuario);
            }
        });

        // Acción de eliminar usuario
        btnEliminar.addActionListener(e -> {
            String seleccionado = listaUsuarios.getSelectedValue();
            if(seleccionado != null) {
                DataStore.eliminarUsuario(seleccionado);
                actualizarLista();
                JOptionPane.showMessageDialog(this, "Usuario eliminado: " + seleccionado);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un usuario para eliminar.");
            }
        });

        // Acción de regresar al AdminPanel
        btnRegresar.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.add(new AdminPanel(), BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }

    // Método para refrescar la lista
    private void actualizarLista() {
        listaUsuarios.setListData(DataStore.getUsuarios().toArray(new String[0]));
    }
}
