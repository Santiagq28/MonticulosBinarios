package estructura.monticulosbinarios;

public class Nodo {
    String nombre;
    int valor;
    Nodo izquierdo, derecho, padre;

    public Nodo(String nombre,int valor) {
        this.nombre = nombre;
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }
}
