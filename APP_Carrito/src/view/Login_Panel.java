/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import util.ArchivoUsuarios;

public class Login_Panel extends JPanel {
    
    public Login_Panel() {
        
        setLayout(new BorderLayout());
        // Fondo degradado
        setBackground(new Color(30, 60, 120));

        // Panel superior con logo y título
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setOpaque(false);

        JLabel logo = new JLabel("APP UnicorMarker");
   
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/carrito.png"));
        Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
         logo.setIcon(new ImageIcon(img));
        
        logo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitle = new JLabel("BIENVENIDOS");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Inicia sesión para continuar");
        lblSubtitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSubtitle.setForeground(Color.LIGHT_GRAY);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(Box.createVerticalStrut(20));
        header.add(logo);
        header.add(Box.createVerticalStrut(5));
        header.add(lblTitle);
        header.add(lblSubtitle);

        // Panel central con campos
        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.anchor = GridBagConstraints.NORTH; 
       

                    // PANEL PARA CORREO
            JPanel panelUser = new JPanel(new BorderLayout());
            panelUser.setBackground(Color.WHITE);
            JLabel iconUser = new JLabel(new ImageIcon(getClass().getResource("/img/mail.png")));
            iconUser.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));         

            JTextField txtUser = new JTextField();            
            txtUser.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            panelUser.add(iconUser, BorderLayout.WEST);
            panelUser.add(txtUser, BorderLayout.CENTER);


                    // PANEL CONTRASEÑA
            JPanel panelPass = new JPanel(new BorderLayout());
            panelPass.setBackground(Color.WHITE);

            JLabel iconPass = new JLabel(new ImageIcon(getClass().getResource("/img/eye.png")));
            iconPass.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

            JPasswordField txtPass = new JPasswordField();
            txtPass.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            panelPass.add(iconPass, BorderLayout.WEST);
            panelPass.add(txtPass, BorderLayout.CENTER);
            


        JLabel btnUser = new JLabel("Entrar como Usuario");
        JLabel btnAdmin = new JLabel("Entrar como Administrador");
        btnUser.setForeground(Color.WHITE);
        btnAdmin.setForeground(Color.WHITE);
        JButton btnLogin = new JButton("INICIAR SESIÓN");
        
        btnLogin.setBackground(new Color(0, 120, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.putClientProperty("JButton.buttonType", "roundRect");

        gbc.gridy++;
         center.add(Box.createVerticalStrut(20), gbc);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        JLabel txtprueba = new JLabel("Usuario o Correo electrónico:");
        center.add(txtprueba, gbc);
        txtprueba.setBackground(new Color(0, 120, 255));
        txtprueba.setForeground(Color.WHITE);   
        txtprueba.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridy++;
        center.add(panelUser, gbc);      

        gbc.gridy++;
        JLabel txtprueba2 = new JLabel("Contraseña:");
        center.add(txtprueba2, gbc);
        txtprueba2.setBackground(new Color(0, 120, 255));
        txtprueba2.setForeground(Color.WHITE);  
        txtprueba2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridy++;
        center.add(panelPass, gbc);   

        gbc.gridy++;
        gbc.gridwidth = 1;
        center.add(btnUser, gbc);
        gbc.gridx++;
        center.add(btnAdmin, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        center.add(btnLogin, gbc);

        gbc.gridy++;
        gbc.weighty = 1; 
        center.add(Box.createVerticalGlue(), gbc);
        
        
        // Panel inferior con enlace
        JPanel footer = new JPanel();
        footer.setOpaque(false);
        JLabel lblForgot = new JLabel("¿Olvidaste tu contraseña?");
        lblForgot.setForeground(Color.LIGHT_GRAY);
        footer.add(lblForgot);

        // Añadir todo al panel principal
        add(header, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
        
        
      // Acción para validar credenciales ACCESO
       btnLogin.addActionListener(e -> {
            String user = txtUser.getText();
            String pass = new String(txtPass.getPassword());

            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

           String rol = ArchivoUsuarios.validarUsuario(user, pass);

if(rol != null){

    frame.getContentPane().removeAll();

    if(rol.equals("USER")){
        frame.add(new Usuarios_Panel(), BorderLayout.CENTER);

    }else if(rol.equals("ADMIN")){
        frame.add(new Admin_Panel(), BorderLayout.CENTER);
    }

    frame.revalidate();
    frame.repaint();

}else{    
                JOptionPane.showMessageDialog(this, "Datos incorrectos o Vacios, Verifique¡");
            }
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


      


