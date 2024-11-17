package estructura.ejerciciomonticulos;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import javax.swing.JOptionPane;
public class EjercicioMonticulos {

    private Nodo raiz;
    private int size;

    public EjercicioMonticulos() {
        this.raiz = null;
        this.size = 0;
    }

    public void insertar(String nombre, int valor) {
        Nodo nuevoNodo = new Nodo(nombre,valor);
        if (raiz == null) {
            raiz = nuevoNodo;
        } else {
            insertarAlFinal(nuevoNodo);
            heapifyUp(nuevoNodo);
        }
        size++;
    }

    private void insertarAlFinal(Nodo nuevoNodo) {
        if (raiz == null) {
            raiz = nuevoNodo;
            return;
        }

        List<Nodo> cola = new LinkedList<>();
        cola.add(raiz);

        Nodo nodoActual = null;
        while (!cola.isEmpty()) {
            nodoActual = cola.remove(0);

            if (nodoActual.izquierdo == null) {
                nodoActual.izquierdo = nuevoNodo;
                nuevoNodo.padre = nodoActual;
                break;
            } else {
                cola.add(nodoActual.izquierdo);
            }

            if (nodoActual.derecho == null) {
                nodoActual.derecho = nuevoNodo;
                nuevoNodo.padre = nodoActual;
                break;
            } else {
                cola.add(nodoActual.derecho);
            }
        }
    }

    private void heapifyUp(Nodo nodo) {
        while (nodo.padre != null && nodo.padre.valor < nodo.valor) {
            int temp = nodo.padre.valor;
            String temp2 = nodo.padre.nombre;
            
            nodo.padre.valor = nodo.valor;
            nodo.padre.nombre = nodo.nombre;
            
            nodo.valor = temp;
            nodo.nombre = temp2;
            
            nodo = nodo.padre;
        }
    }

    public String extraerMax() {
        if (raiz == null) {
            throw new IllegalStateException("El montículo está vacío");
        }
        String valorMaximo = raiz.nombre+"("+raiz.valor+")";

        Nodo ultimoNodo = obtenerUltimoNodo();
        raiz.valor = ultimoNodo.valor;
        raiz.nombre = ultimoNodo.nombre;
        eliminarUltimoNodo();

        heapifyDown(raiz);
        size--;
        
        return valorMaximo;
    }

    private Nodo obtenerUltimoNodo() {
        if (raiz == null) {
            return null;
        }

        List<Nodo> cola = new LinkedList<>();
        cola.add(raiz);
        Nodo nodoActual = null;

        while (!cola.isEmpty()) {
            nodoActual = cola.remove(0);
            if (nodoActual.izquierdo != null) {
                cola.add(nodoActual.izquierdo);
            }
            if (nodoActual.derecho != null) {
                cola.add(nodoActual.derecho);
            }
        }
        return nodoActual;
    }

    private void eliminarUltimoNodo() {
        if (raiz == null) {
            return;
        }

        List<Nodo> cola = new LinkedList<>();
        cola.add(raiz);
        Nodo nodoActual = null;
        Nodo padreDelUltimo = null;

        while (!cola.isEmpty()) {
            nodoActual = cola.remove(0);

            if (nodoActual.izquierdo != null) {
                cola.add(nodoActual.izquierdo);
                padreDelUltimo = nodoActual;
            }
            if (nodoActual.derecho != null) {
                cola.add(nodoActual.derecho);
                padreDelUltimo = nodoActual;
            }
        }
        if (padreDelUltimo != null) {
            if (padreDelUltimo.derecho != null) {
                padreDelUltimo.derecho = null;
            } else if (padreDelUltimo.izquierdo != null) {
                padreDelUltimo.izquierdo = null;
            }
        } else {
            raiz = null;
        }
    }

    private void heapifyDown(Nodo nodo) {
        if (nodo == null) {
            return;
        }

        Nodo hijoIzquierdo = nodo.izquierdo;
        Nodo hijoDerecho = nodo.derecho;
        
        Nodo mayorHijo = nodo;
        if (hijoIzquierdo != null && hijoIzquierdo.valor > mayorHijo.valor) {
            mayorHijo = hijoIzquierdo;
        }
        if (hijoDerecho != null && hijoDerecho.valor > mayorHijo.valor) {
            mayorHijo = hijoDerecho;
        }

        if (mayorHijo != nodo) {
            int temp = nodo.valor;
            String temp2 = nodo.nombre;
            
            nodo.valor = mayorHijo.valor;
            nodo.nombre = mayorHijo.nombre;
            
            mayorHijo.valor = temp;
            mayorHijo.nombre = temp2;
            
            heapifyDown(mayorHijo);
        }
    }

    public int obtenerMax() {
        if (raiz != null) {
            return raiz.valor;
        }else{
            JOptionPane.showMessageDialog(null,"ERROR, montículo vacío");
            return -1;
        }
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public String obtenerHeapComoTexto() {
        ArrayList<String> resultado = new ArrayList<>();
        generarTextoNodo(raiz, "", true, resultado);

        String textoFinal = "";
        for (String linea : resultado) {
            textoFinal += linea + "\n";
        }
        return textoFinal;
    }

    private void generarTextoNodo(Nodo nodo, String prefix, boolean isLeft, ArrayList<String> resultado) {
        if (nodo != null) {
            // Agregar la línea actual al resultado
            resultado.add(prefix + (isLeft ? "├── " : "└── ") + nodo.nombre+"("+nodo.valor+")");

            // Generar texto para los nodos izquierdo y derecho
            generarTextoNodo(nodo.izquierdo, prefix + (isLeft ? "│   " : "    "), true, resultado);
            generarTextoNodo(nodo.derecho, prefix + (isLeft ? "│   " : "    "), false, resultado);
        }
    }
}
