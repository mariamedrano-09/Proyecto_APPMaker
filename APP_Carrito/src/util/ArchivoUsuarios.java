/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.*;
import java.util.*;

public class ArchivoUsuarios {

    private static final String RUTA = "src/data/usuarios.txt";

    public static String validarUsuario(String usuario, String password) {

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                String user = datos[0];
                String pass = datos[1];
                String rol = datos[2];

                if (user.equals(usuario) && pass.equals(password)) {
                    return rol;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}