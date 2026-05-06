package avltree;

/**
 * Representa un nodo del Árbol Binario de Búsqueda Balanceado (AVL).
 * Almacena el valor, la altura del nodo y referencias a hijos izquierdo y derecho.
 */
public class Nodo {

    int valor;
    int altura;
    Nodo izq;
    Nodo der;

    public Nodo(int valor) {
        this.valor  = valor;
        this.altura = 1;
        this.izq    = null;
        this.der    = null;
    }
}
