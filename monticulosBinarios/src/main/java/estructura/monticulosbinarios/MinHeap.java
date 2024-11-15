package estructura.monticulosbinarios;

import java.util.ArrayList;

public class MinHeap {
    private ArrayList<Nodo> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return (2 * i) + 1; }
    private int rightChild(int i) { return (2 * i) + 2; }
    

    // Inserta un nuevo nodo en el min-heap y ajusta su posición hacia arriba
    public void insertarNodo(int valor) {
        Nodo nuevoNodo = new Nodo(valor);
        heap.add(nuevoNodo);
        int current = heap.size() - 1;
        
        while (current > 0 && heap.get(parent(current)).valor > heap.get(current).valor) {
            intercambiar(parent(current), current);
            current = parent(current);
        }
    }

    // Extrae el nodo con el valor más bajo (la raíz) y ajusta el heap
    public Nodo extraerMin() {
        if (heap.isEmpty()) throw new IllegalStateException("El montículo está vacío");

        Nodo min = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        minHeapify(0);
        return min;
    }
    
    public void eliminarNodo(int valor) {
        int index = -1;

        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).valor == valor) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Nodo no encontrado.");
            return;
        }

        heap.set(index, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        minHeapify(index);
    }

    // Ajusta la posición del nodo en el índice 'i' hacia abajo
    private void minHeapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest = i;

        if (left < heap.size() && heap.get(left).valor < heap.get(smallest).valor) {
            smallest = left;
        }
        if (right < heap.size() && heap.get(right).valor < heap.get(smallest).valor) {
            smallest = right;
        }

        if (smallest != i) {
            intercambiar(i, smallest);
            minHeapify(smallest);
        }
    }

    // Intercambia dos nodos
    private void intercambiar(int i, int j) {
        Nodo temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Imprime el contenido actual del montículo
    public String printHeap() {
        StringBuilder monticulo = new StringBuilder();
        for (Nodo nodo : heap) {
            monticulo.append(nodo.valor).append(" ");
        }
        return monticulo.toString().trim();
    }
}
