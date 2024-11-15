package estructura.monticulosbinarios;

import java.util.ArrayList;

public class MaxHeap {
    private ArrayList<Nodo> heap;

    public MaxHeap() {
        this.heap = new ArrayList<>();
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return (2 * i) + 1; }
    private int rightChild(int i) { return (2 * i) + 2; }
    

    // Inserta un nuevo nodo en el max-heap y ajusta su posición hacia arriba
    public void insertarNodo(int valor) {
        Nodo nuevoNodo = new Nodo(valor);
        heap.add(nuevoNodo);
        int current = heap.size() - 1;
        
        while (current > 0 && heap.get(parent(current)).valor < heap.get(current).valor) {
            intercambiar(parent(current), current);
            current = parent(current);
        }
    }

    

    // Extrae el nodo con el valor más alto (la raíz) y ajusta el heap
    public Nodo extraerMax() {
        if (heap.isEmpty()) throw new IllegalStateException("El montículo está vacío");

        Nodo max = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        maxHeapify(0);
        return max;
    }
    
    public void eliminarNodo(int valor) {
        int index = -1;

        // Buscar el nodo con el valor a eliminar
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

        // Reemplazar el nodo a eliminar con el último nodo
        heap.set(index, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        // Restaurar la propiedad del heap
        maxHeapify(index);
    }

    // Ajusta la posición del nodo
    private void maxHeapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int largest = i;

        if (left < heap.size() && heap.get(left).valor > heap.get(largest).valor) {
            largest = left;
        }
        if (right < heap.size() && heap.get(right).valor > heap.get(largest).valor) {
            largest = right;
        }

        if (largest != i) {
            intercambiar(i, largest);
            maxHeapify(largest);
        }
    }

    // Intercambia dos nodos
    private void intercambiar(int i, int j) {
        Nodo temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Imprime el contenido actual del monticulo
    public String printHeap() {
        StringBuilder monticulo = new StringBuilder();
        for (Nodo nodo : heap) {
            monticulo.append(nodo.valor).append(" ");
        }
        return monticulo.toString().trim();
    }

}
