/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;

public class Usuarios_Panel extends JPanel {
    
    public Usuarios_Panel() {
        
        setLayout(new BorderLayout());
        // Fondo degradado
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

        JLabel saludo = new JLabel("Hola, Maria C.", SwingConstants.RIGHT);
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

        // Panel central con botones coloridos
        JPanel center = new JPanel(new GridLayout(2, 2, 20, 20));
        center.setOpaque(false);

        JButton btnCatalogo = new JButton("Catálogo de Productos");
        ImageIcon iconProd = new ImageIcon(getClass().getResource("/img/catalogo.png"));
        Image imgProd = iconProd.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnCatalogo.setBackground(new Color(0, 120, 255));
        btnCatalogo.setForeground(Color.WHITE);
        btnCatalogo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCatalogo.setIcon(new ImageIcon(imgProd));
        btnCatalogo.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnCatalogo.setIconTextGap(10);

        JButton btnCarrito = new JButton("Carrito de Compras");
        ImageIcon iconCat = new ImageIcon(getClass().getResource("/img/carro.png"));
        Image imgCat = iconCat.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnCarrito.setBackground(new Color(0, 180, 100));
        btnCarrito.setForeground(Color.WHITE);
        btnCarrito.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCarrito.setIcon(new ImageIcon(imgCat));
        btnCarrito.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnCarrito.setIconTextGap(10);

        JButton btnHistorial = new JButton("Historial de Compras");
        ImageIcon iconHist = new ImageIcon(getClass().getResource("/img/historial.png"));
        Image imgHist = iconHist.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnHistorial.setBackground(new Color(255, 140, 0));
        btnHistorial.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnHistorial.setForeground(Color.WHITE);
        btnHistorial.setIcon(new ImageIcon(imgHist));
        btnHistorial.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnHistorial.setIconTextGap(10);
        
        JButton btnDeseos = new JButton("Lista de Deseos");
        ImageIcon iconDes = new ImageIcon(getClass().getResource("/img/deseo.png"));
        Image imgDes = iconDes.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnDeseos.setBackground(new Color(255, 80, 150));
        btnDeseos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDeseos.setForeground(Color.WHITE);
        btnDeseos.setIcon(new ImageIcon(imgDes));
        btnDeseos.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnDeseos.setIconTextGap(10);

        center.add(btnCatalogo);
        center.add(btnCarrito);
        center.add(btnHistorial);
        center.add(btnDeseos);

        add(center, BorderLayout.CENTER);

        // Banner inferior
        JPanel footer = new JPanel();
        footer.setOpaque(false);
        JLabel oferta = new JLabel("¡Oferta Especial! Descuentos en toda la tienda esta semana.");
        oferta.setForeground(Color.BLACK);
        footer.add(oferta);

        add(footer, BorderLayout.SOUTH);
        
    btnCatalogo.addActionListener(e -> {
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    frame.getContentPane().removeAll();
    frame.add(new CatalogPanel(), BorderLayout.CENTER);
    frame.revalidate();
    frame.repaint();
   });
    
    btnCarrito.addActionListener(e -> {
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    frame.getContentPane().removeAll();
    frame.add(new Carrito_Panel(), BorderLayout.CENTER);
    frame.revalidate();
    frame.repaint();
   });
    
    btnHistorial.addActionListener(e -> {
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    frame.getContentPane().removeAll();
    frame.add(new Compras_Panel(), BorderLayout.CENTER);
    frame.revalidate();
    frame.repaint();
   });
    
    btnDeseos.addActionListener(e -> {
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    frame.getContentPane().removeAll();
    frame.add(new Deseos_Panel(), BorderLayout.CENTER);
    frame.revalidate();
    frame.repaint();
   });
    
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

