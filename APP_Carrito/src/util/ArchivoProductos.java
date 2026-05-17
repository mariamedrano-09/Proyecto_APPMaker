package util;

import model.Producto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoProductos {
    private static final String RUTA = "src/data/productos.txt"; 
    
    public static void guardarProducto(Producto p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA, true))) {
            bw.write(p.serialize());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Producto> leerProductos() {
        ArrayList<Producto> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                String nombre = datos[0];
                double precio = Double.parseDouble(datos[1]);
                String categoria = datos[2];
                String imagen = (datos.length >= 4 && !datos[3].trim().isEmpty()) ? datos[3].trim() : null;

                Producto p = new Producto(nombre, precio, categoria, imagen);
                lista.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static void sobrescribirProductos(List<Producto> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA))) {
            for (Producto p : lista) {
                bw.write(p.serialize());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}