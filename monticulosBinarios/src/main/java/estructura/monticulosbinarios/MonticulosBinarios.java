package estructura.monticulosbinarios;

import javax.swing.JOptionPane;

/**
 * Santiago Guevara (192203)
 * Leonardo Geney (192210)
 * Keiner Ballesteros (192197)
 */
public class MonticulosBinarios {
    
    
    private Nodo raiz;
    private int size;
    
    String line = "";
    
    public MonticulosBinarios() {
        raiz = null;
        size = 0;
    }
    
    // Método para insertar un nuevo valor en el montículo
    public void insertar(int valor) {
        size++;  // Incrementamos el tamaño para asignar el próximo índice
        Nodo nuevo = new Nodo(valor, size);

        if (raiz == null) {
            raiz = nuevo;
        } else {
            Nodo parent = encontrarPadre(nuevo.indice);
            nuevo.padre = parent;

            if (parent.izquierdo == null) {
                parent.izquierdo = nuevo;
            } else {
                parent.derecho = nuevo;
            }

            heapifyUp(nuevo);
        }
    }

    // Método para eliminar el valor máximo (la raíz)
    public int eliminarMaximo() {
        if (raiz == null) {
            throw new IllegalStateException("El montículo está vacío");
        }

        int maximo = raiz.valor;

        if (size == 1) {
            raiz = null;  // Eliminar el único nodo
        } else {
            Nodo ultimo = encontrarUltimoNodo(size);

            // Reemplazar el valor de la raíz con el último nodo
            raiz.valor = ultimo.valor;

            // Eliminar el último nodo del árbol
            if (ultimo.padre.izquierdo == ultimo) {
                ultimo.padre.izquierdo = null;
            } else {
                ultimo.padre.derecho = null;
            }

            heapifyDown(raiz);
        }

        size--;
        return maximo;
    }

    // Encontrar el padre del nodo que se va a insertar
    public Nodo encontrarPadre(int index) {
        return encontrarNodo((int) Math.floor(index / 2));
    }

    // Encontrar el último nodo en el árbol (posición size)
    public Nodo encontrarUltimoNodo(int index) {
        return encontrarNodo(index);
    }

    // Método para encontrar un nodo según su índice
    public Nodo encontrarNodo(int index) {
        return buscarNodo(raiz, index);
    }

    // Buscar un nodo recursivamente según su índice
    public Nodo buscarNodo(Nodo actual, int index) {
        if (actual == null || actual.indice == index) {
            return actual;
        }

        Nodo encontrado = buscarNodo(actual.izquierdo, index);
        if (encontrado == null) {
            encontrado = buscarNodo(actual.derecho, index);
        }
        return encontrado;
    }

    // Ajustar hacia arriba (heapifyUp)
    public void heapifyUp(Nodo nodo) {
        while (nodo.padre != null && nodo.valor > nodo.padre.valor) {
            int temp = nodo.valor;
            nodo.valor = nodo.padre.valor;
            nodo.padre.valor = temp;

            nodo = nodo.padre;
        }
    }

    // Ajustar hacia abajo (heapifyDown)
    public void heapifyDown(Nodo nodo) {
        while (nodo.izquierdo != null) {
            Nodo mayorHijo = nodo.izquierdo;
            if (nodo.derecho != null && nodo.derecho.valor > nodo.izquierdo.valor) {
                mayorHijo = nodo.derecho;
            }

            if (nodo.valor >= mayorHijo.valor) {
                break;
            }

            int temp = nodo.valor;
            nodo.valor = mayorHijo.valor;
            mayorHijo.valor = temp;

            nodo = mayorHijo;
        }
    }

    // Imprimir el montículo en orden
    public void imprimirHeap(Nodo nodo) {
        
        if (nodo != null) {
            imprimirHeap(nodo.izquierdo);
            line += nodo.valor + " ";
            imprimirHeap(nodo.derecho);
        }
        
    }

    public void imprimir() {
        imprimirHeap(raiz);
        JOptionPane.showMessageDialog(null,line);
        line = "";
    }

}
