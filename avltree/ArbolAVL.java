package avltree;

/**
 * Arbol Binario de Búsqueda Balanceado (AVL).
 * Mantiene el arbol equilibrado automaticamente despues de cada
 * insercion o eliminacion mediante rotaciones simples y dobles.
 */
public class ArbolAVL {

    private Nodo raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    
    // Metodos publicos 
  

    public Nodo getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    /** Inserta un valor y rebalancea el arbol. */
    public void insertar(int valor) {
        raiz = insertar(raiz, valor);
    }

    /** Elimina un valor y rebalancea el arbol. */
    public void borrar(int valor) {
        raiz = borrar(raiz, valor);
    }

    /** Busca un valor; retorna true si existe. */
    public boolean buscar(int valor) {
        return buscar(raiz, valor);
    }

   
    // Nodo insercion
  

    private Nodo insertar(Nodo nodo, int valor) {
        // 1. Inserción normal de BST
        if (nodo == null) return new Nodo(valor);

        if (valor < nodo.valor)
            nodo.izq = insertar(nodo.izq, valor);
        else if (valor > nodo.valor)
            nodo.der = insertar(nodo.der, valor);
        else
            return nodo; // duplicados no permitidos

        // 2. Actualizar altura del ancestro
        actualizarAltura(nodo);

        // 3. Rebalancear si es necesario
        return rebalancear(nodo, valor);
    }

    
    // Nodo eliminacion
   

    private Nodo borrar(Nodo nodo, int valor) {
        if (nodo == null) return null;

        if (valor < nodo.valor)
            nodo.izq = borrar(nodo.izq, valor);
        else if (valor > nodo.valor)
            nodo.der = borrar(nodo.der, valor);
        else {
            // Nodo encontrado
            if (nodo.izq == null || nodo.der == null) {
                // Caso 1 o 2: sin hijos o un solo hijo
                nodo = (nodo.izq != null) ? nodo.izq : nodo.der;
            } else {
                // Caso 3: dos hijos → sucesor inorden (mínimo del subárbol derecho)
                Nodo sucesor = minimoNodo(nodo.der);
                nodo.valor   = sucesor.valor;
                nodo.der     = borrar(nodo.der, sucesor.valor);
            }
        }

        if (nodo == null) return null;

        actualizarAltura(nodo);
        return rebalancearBorrado(nodo);
    }

  
    // Nodo busqueda
    

    private boolean buscar(Nodo nodo, int valor) {
        if (nodo == null) return false;
        if (valor == nodo.valor) return true;
        if (valor < nodo.valor) return buscar(nodo.izq, valor);
        return buscar(nodo.der, valor);
    }

   
    // Nodo balanceo
   

    private int getAltura(Nodo nodo) {
        return (nodo == null) ? 0 : nodo.altura;
    }

    private void actualizarAltura(Nodo nodo) {
        nodo.altura = 1 + Math.max(getAltura(nodo.izq), getAltura(nodo.der));
    }

    public int factorBalanceo(Nodo nodo) {
        return (nodo == null) ? 0 : getAltura(nodo.izq) - getAltura(nodo.der);
    }

    /**
     * Rebalanceo para inserción.
     * Detecta los 4 casos: LL, RR, LR, RL.
     */
    private Nodo rebalancear(Nodo nodo, int valorInsertado) {
        int fb = factorBalanceo(nodo);

        // Caso LL (desbalance izquierdo, nodo insertado a la izquierda)
        if (fb > 1 && valorInsertado < nodo.izq.valor)
            return rotarDerecha(nodo);

        // Caso RR (desbalance derecho, nodo insertado a la derecha)
        if (fb < -1 && valorInsertado > nodo.der.valor)
            return rotarIzquierda(nodo);

        // Caso LR (desbalance izquierdo, nodo insertado a la derecha del hijo izq)
        if (fb > 1 && valorInsertado > nodo.izq.valor) {
            nodo.izq = rotarIzquierda(nodo.izq);
            return rotarDerecha(nodo);
        }

        // Caso RL (desbalance derecho, nodo insertado a la izquierda del hijo der)
        if (fb < -1 && valorInsertado < nodo.der.valor) {
            nodo.der = rotarDerecha(nodo.der);
            return rotarIzquierda(nodo);
        }

        return nodo; // ya está balanceado
    }

    /**
     * Rebalanceo para eliminacion.
     * Usa el factor de balanceo de los hijos en lugar del valor eliminado.
     */
    private Nodo rebalancearBorrado(Nodo nodo) {
        int fb = factorBalanceo(nodo);

        // Caso LL
        if (fb > 1 && factorBalanceo(nodo.izq) >= 0)
            return rotarDerecha(nodo);

        // Caso LR
        if (fb > 1 && factorBalanceo(nodo.izq) < 0) {
            nodo.izq = rotarIzquierda(nodo.izq);
            return rotarDerecha(nodo);
        }

        // Caso RR
        if (fb < -1 && factorBalanceo(nodo.der) <= 0)
            return rotarIzquierda(nodo);

        // Caso RL
        if (fb < -1 && factorBalanceo(nodo.der) > 0) {
            nodo.der = rotarDerecha(nodo.der);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    
    // Nodos de rotaciones
    

    /**
     * Rotación simple a la derecha (caso LL).
     *
     *       y                x
     *      / \              / \
     *     x   T3   →      T1   y
     *    / \                  / \
     *   T1  T2               T2  T3
     */
    private Nodo rotarDerecha(Nodo y) {
        Nodo x  = y.izq;
        Nodo T2 = x.der;

        x.der = y;
        y.izq = T2;

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    /**
     * Rotación simple a la izquierda (caso RR).
     *
     *     x                  y
     *    / \                / \
     *   T1   y    →        x   T3
     *       / \           / \
     *      T2  T3        T1  T2
     */
    private Nodo rotarIzquierda(Nodo x) {
        Nodo y  = x.der;
        Nodo T2 = y.izq;

        y.izq = x;
        x.der = T2;

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    
    // Nodo utilidades internas
  

    /** Retorna el nodo con el valor mínimo del subarbol. */
    private Nodo minimoNodo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.izq != null)
            actual = actual.izq;
        return actual;
    }
}
