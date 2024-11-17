package estructura.monticulosbinarios;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class MinHeap {
    private Nodo raiz;
    private int size;

    public MinHeap() {
        this.raiz = null;
        this.size = 0;
    }

    public void insertar(String nombre,int valor) {
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
        while (nodo.padre != null && nodo.padre.valor > nodo.valor) {
            intercambiarNodos(nodo, nodo.padre);
            nodo = nodo.padre;
        }
    }

    public String extraerMin() {
        if (raiz == null) {
            throw new IllegalStateException("El montículo está vacío");
        }
        String valorMinimo = raiz.nombre+"("+ raiz.valor+")";

        Nodo ultimoNodo = obtenerUltimoNodo();
        intercambiarNodos(raiz,ultimoNodo);
        eliminarUltimoNodo();

        heapifyDown(raiz);
        size--;
        
        return valorMinimo;
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
        
        Nodo menorHijo = nodo;
        if (hijoIzquierdo != null && hijoIzquierdo.valor < menorHijo.valor) {
            menorHijo = hijoIzquierdo;
        }
        if (hijoDerecho != null && hijoDerecho.valor < menorHijo.valor) {
            menorHijo = hijoDerecho;
        }

        if (menorHijo != nodo) {
            intercambiarNodos(nodo, menorHijo);
            heapifyDown(menorHijo);
        }
    }
    
    private void intercambiarNodos(Nodo nodo1, Nodo nodo2) {
        int tempValor = nodo1.valor;
        nodo1.valor = nodo2.valor;
        nodo2.valor = tempValor;

        String tempNombre = nodo1.nombre;
        nodo1.nombre = nodo2.nombre;
        nodo2.nombre = tempNombre;
    }


    public int obtenerMin() {
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
            resultado.add(prefix + (isLeft ? "├── " : "└── ") +nodo.nombre+"("+ nodo.valor+")");

            // Generar texto para los nodos izquierdo y derecho
            generarTextoNodo(nodo.izquierdo, prefix + (isLeft ? "│   " : "    "), true, resultado);
            generarTextoNodo(nodo.derecho, prefix + (isLeft ? "│   " : "    "), false, resultado);
        }
    }
}