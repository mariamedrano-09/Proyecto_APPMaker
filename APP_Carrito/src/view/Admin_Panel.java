package view;

import javax.swing.*;
import java.awt.*;

public class Admin_Panel extends JPanel {
    
    public Admin_Panel() {
        setLayout(new BorderLayout());
       setBackground(new Color(30, 60, 120));
        // Encabezado con logo, saludo y cerrar sesión
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel logo = new JLabel("APP UnicorMarker");
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/carrito.png"));
        Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(img));
         
        logo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        logo.setForeground(Color.WHITE);

        JLabel saludo = new JLabel("Hola, Administrador!", SwingConstants.RIGHT);
        saludo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        saludo.setForeground(Color.WHITE);

        JButton btnLogout = new JButton("Cerrar");
        ImageIcon iconOut = new ImageIcon(getClass().getResource("/img/close.png"));
        Image imgOut = iconOut.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnLogout.setIcon(new ImageIcon(imgOut));
        btnLogout.setBackground(new Color(200, 50, 50));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightHeader.setOpaque(false);
        rightHeader.add(saludo);
        rightHeader.add(btnLogout);

        header.add(logo, BorderLayout.WEST);
        header.add(rightHeader, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Opciones del administrador
        JPanel center = new JPanel(new GridLayout(2, 2, 20, 20));
        center.setOpaque(false);

        //BOTTON GESTIONAR USUARIOS E ICONO
        JButton btnGestionUsuarios = new JButton("Gestión de Usuarios");        
        ImageIcon iconUser = new ImageIcon(getClass().getResource("/img/admin.png"));
        Image imgUser = iconUser.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);        
        btnGestionUsuarios.setBackground(new Color(0, 120, 255));
        btnGestionUsuarios.setForeground(Color.WHITE);
        btnGestionUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGestionUsuarios.setIcon(new ImageIcon(imgUser));
        btnGestionUsuarios.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnGestionUsuarios.setIconTextGap(10);

         //BOTTON GESTIONAR PRODUCTOS E ICONO
        JButton btnGestionProductos = new JButton("Gestión de Productos");
        ImageIcon iconProd = new ImageIcon(getClass().getResource("/img/add.png"));
        Image imgProd = iconProd.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnGestionProductos.setBackground(new Color(0, 120, 255));
        btnGestionProductos.setForeground(Color.WHITE);
        btnGestionProductos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGestionProductos.setIcon(new ImageIcon(imgProd));
        btnGestionProductos.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnGestionProductos.setIconTextGap(10);

        //BOTTON GESTIONAR REPORTES E ICONO
        JButton btnReportes = new JButton("Reportes de Ventas");
        ImageIcon iconRep = new ImageIcon(getClass().getResource("/img/report.png"));
        Image imgRep = iconRep.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnReportes.setBackground(new Color(0, 180, 100));
        btnReportes.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnReportes.setForeground(Color.WHITE);
        btnReportes.setIcon(new ImageIcon(imgRep));
        btnReportes.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnReportes.setIconTextGap(10);

        //BOTTON GESTIONAR INVENTARIO E ICONO - NO FUNCIONAL
        JButton btnInventario = new JButton("Inventario");
        ImageIcon iconInv = new ImageIcon(getClass().getResource("/img/inventario.png"));
        Image imgInv = iconInv.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnInventario.setBackground(new Color(255, 140, 0));
        btnInventario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnInventario.setForeground(Color.WHITE);
        btnInventario.setIcon(new ImageIcon(imgInv));
        btnInventario.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnInventario.setIconTextGap(10);
        
        //BOTTON GESTIONAR CONFIGURACION PARA PARAMETROS E ICONO - NO FUNCIONAL
        JButton btnConfiguracion = new JButton("Configuración");
        ImageIcon iconConf = new ImageIcon(getClass().getResource("/img/config.png"));
        Image imgConf = iconConf.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnConfiguracion.setBackground(new Color(255, 80, 150));
        btnConfiguracion.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnConfiguracion.setForeground(Color.WHITE);
        btnConfiguracion.setIcon(new ImageIcon(imgConf));
        btnConfiguracion.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnConfiguracion.setIconTextGap(10);
        

        center.add(btnGestionUsuarios);
        center.add(btnGestionProductos);
        center.add(btnReportes);
        center.add(btnInventario);
        center.add(btnConfiguracion);

        add(center, BorderLayout.CENTER);

         btnGestionUsuarios.addActionListener(e -> { 
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.add(new Usuarios(), BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();            
        });
         // Acción para abrir ProductManagementPanel
        btnGestionProductos.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.add(new Productos_Panel(), BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
        // Acción de cerrar sesión
        btnLogout.addActionListener(e -> {
            controller.DataStore.vaciarCarrito();
            controller.DataStore.getHistorial().clear();
            controller.DataStore.getDeseos().clear();
    
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.add(new Login_Panel(), BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
            JOptionPane.showMessageDialog(this, "Sesión cerrada correctamente.");
        });
        
       
        
    }
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    GradientPaint gp = new GradientPaint(
            0, 0, new Color(70, 130, 255),
            0, getHeight(), new Color(0, 200, 255)
    );

    g2.setPaint(gp);
    g2.fillRect(0, 0, getWidth(), getHeight());
}
}
