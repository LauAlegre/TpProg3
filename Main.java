import java.util.*;

public class Main {
    public static void main(String[] args) {
        String rutaArchivo = "maquinas.txt";

        int[] piezasTotales = new int[1];
        List<Maquina> maquinas = LectorArchivo.leerMaquinas(rutaArchivo, piezasTotales);

        if (maquinas.isEmpty()) {
            System.out.println("No se pudieron cargar las máquinas.");
            return;
        }

        SecuenciaMaquinas sistema = new SecuenciaMaquinas(maquinas, piezasTotales[0]);
        sistema.resolverConBacktracking();
        sistema.resolverConGreedy();
    }
}