import java.util.*;

public class SecuenciaMaquinas {

    private List<Maquina> mejorSolucion = new ArrayList<>();
    private int mejorPuestaFun = Integer.MAX_VALUE;
    private int mejorCantidadPiezas = 0;
    private int cantidadEstados = 0;

    private List<Maquina> maquinasDisponibles;
    private int cantPiezas;

    public SecuenciaMaquinas(List<Maquina> maquinasDisponibles, int cantPiezas) {
        this.maquinasDisponibles = maquinasDisponibles;
        this.cantPiezas = cantPiezas;
    }

    /*
     * Estrategia de resolución con Backtracking:
     * - Se genera un árbol de exploración probando todas las combinaciones posibles de máquinas.
     * - Cada nodo representa una secuencia parcial de máquinas activadas.
     * - Un estado final es aquel en el que la suma de piezas es igual al objetivo.
     * - Se aplican podas:
     * - Si la secuencia actual ya usa más activaciones que la mejor solución conocida, se descarta.
     * - Se cuenta la cantidad total de estados explorados como métrica de costo.
     */
    public void resolverConBacktracking() {
        List<Maquina> actuales = new LinkedList<>();
        encontrarSecuencia(0, actuales);
        mostrarResultadoBacktracking();
    }

    private void encontrarSecuencia(int produccionActual, List<Maquina> actuales) {
        cantidadEstados++;

        if (produccionActual == cantPiezas) {
            if (actuales.size() < mejorPuestaFun) {
                mejorPuestaFun = actuales.size();
                mejorCantidadPiezas = produccionActual;
                mejorSolucion = new ArrayList<>(actuales);
            }
            return;
        }

        if (produccionActual > cantPiezas || actuales.size() >= mejorPuestaFun) return;

        for (Maquina m : maquinasDisponibles) {
            actuales.add(m);
            encontrarSecuencia(produccionActual + m.getProduccion(), actuales);
            actuales.remove(actuales.size() - 1);
        }
    }

    private void mostrarResultadoBacktracking() {
        System.out.println("\n Backtracking:");
        System.out.print("Secuencia: ");
        for (Maquina m : mejorSolucion) {
            System.out.print(m.getNombre() + " ");
        }
        System.out.println("\nPiezas producidas: " + mejorCantidadPiezas);
        System.out.println("Activaciones: " + mejorPuestaFun);
        System.out.println("Estados generados: " + cantidadEstados);
    }

    /*
     * Estrategia de resolución con Greedy:
     * - Los candidatos son las máquinas disponibles, ordenadas de mayor a menor producción.
     * - En cada iteración se elige la máquina que más piezas produce sin pasarse del objetivo.
     * - Se calcula cuántas veces puede usarse esa máquina (división entera).
     * - No se garantiza encontrar una solución óptima, pero se obtiene una solución rápida.
     * - Se mide la cantidad de candidatos considerados como métrica de costo.
     */
    public void resolverConGreedy() {
        List<Maquina> candidatos = new LinkedList<>(maquinasDisponibles);
        candidatos.sort((a, b) -> Integer.compare(b.getProduccion(), a.getProduccion()));

        List<Maquina> solucion = new LinkedList<>();
        int produccionActual = 0;
        int candidatosConsiderados = 0;

        while (produccionActual < cantPiezas && !candidatos.isEmpty()) {
            Maquina maquina = seleccionar(candidatos);
            candidatosConsiderados++;

            int piezasRestantes = cantPiezas - produccionActual;
            int veces = piezasRestantes / maquina.getProduccion();

            for (int i = 0; i < veces; i++) {
                solucion.add(maquina);
                produccionActual += maquina.getProduccion();
            }

            candidatos.remove(maquina);
        }

        mostrarResultadoGreedy(solucion, produccionActual, candidatosConsiderados);
    }

    private Maquina seleccionar(List<Maquina> candidatos) {
        return candidatos.get(0);
    }

    private void mostrarResultadoGreedy(List<Maquina> solucion, int produccion, int candidatos) {
        System.out.println("\n Greedy:");
        System.out.print("Secuencia: ");
        for (Maquina m : solucion) {
            System.out.print(m.getNombre() + " ");
        }
        System.out.println("\nPiezas producidas: " + produccion);
        System.out.println("Activaciones: " + solucion.size());
        System.out.println("Candidatos considerados: " + candidatos);
    }
}