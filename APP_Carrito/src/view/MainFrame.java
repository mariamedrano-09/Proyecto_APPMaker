/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class MainFrame extends javax.swing.JFrame {
    
     private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());
  
    public MainFrame() {
        setTitle("MiApp - Sistema de Compras");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel inicial (Login)
        add(new LoginPanel(), BorderLayout.CENTER);
        
    }

    public static void main(String[] args) {
          try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
           /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
