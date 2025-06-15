import java.io.*;
import java.util.*;

public class LectorArchivo {
    public static List<Maquina> leerMaquinas(String rutaArchivo, int[] piezasTotales) {
        List<Maquina> maquinas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            piezasTotales[0] = Integer.parseInt(br.readLine());
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String nombre = partes[0];
                int produccion = Integer.parseInt(partes[1]);
                maquinas.add(new Maquina(nombre, produccion));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return maquinas;
    }
}