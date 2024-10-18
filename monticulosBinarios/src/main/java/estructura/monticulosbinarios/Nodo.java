/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructura.monticulosbinarios;

/**
 *
 * @author USER
 */
public class Nodo {
    int valor;
    Nodo izquierdo, derecho, padre;
    int indice;
    
    public Nodo(int valor, int indice) {
        this.valor = valor;
        this.indice = indice;
        izquierdo = derecho = padre = null;
    }
}
