package estructura.monticulosbinarios;

public class Nodo {
    int valor;
    Nodo izquierdo, derecho, padre;

    public Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }
}
