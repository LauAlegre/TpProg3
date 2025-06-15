public class Maquina {
    private String nombre;
    private int produccion;

    public Maquina(String nombre, int produccion) {
        this.nombre = nombre;
        this.produccion = produccion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getProduccion() {
        return produccion;
    }
}