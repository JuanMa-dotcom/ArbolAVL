package avltree;

import java.util.ArrayList;
import java.util.List;

/**
 * Recorridos del Árbol AVL.
 * Todos los métodos son estáticos: reciben el nodo raíz y retornan
 * una lista con los valores en el orden correspondiente.
 */
public class Recorridos {

    // ─────────────────────────────────────────────
    // Metodos públicos (imprimen y retornan la lista)
  

    /** Izquierda → Raíz → Derecha  (retorna valores ordenados ascendentemente) */
    public static List<Integer> inorden(Nodo raiz) {
        List<Integer> resultado = new ArrayList<>();
        inordenRec(raiz, resultado);
        return resultado;
    }

    /** Raíz → Izquierda → Derecha */
    public static List<Integer> preorden(Nodo raiz) {
        List<Integer> resultado = new ArrayList<>();
        preordenRec(raiz, resultado);
        return resultado;
    }

    /** Izquierda → Derecha → Raíz */
    public static List<Integer> postorden(Nodo raiz) {
        List<Integer> resultado = new ArrayList<>();
        postordenRec(raiz, resultado);
        return resultado;
    }

    // ─────────────────────────────────────────────
    // Implementaciones recursivas
    // ─────────────────────────────────────────────

    private static void inordenRec(Nodo nodo, List<Integer> lista) {
        if (nodo == null) return;
        inordenRec(nodo.izq, lista);
        lista.add(nodo.valor);
        inordenRec(nodo.der, lista);
    }

    private static void preordenRec(Nodo nodo, List<Integer> lista) {
        if (nodo == null) return;
        lista.add(nodo.valor);
        preordenRec(nodo.izq, lista);
        preordenRec(nodo.der, lista);
    }

    private static void postordenRec(Nodo nodo, List<Integer> lista) {
        if (nodo == null) return;
        postordenRec(nodo.izq, lista);
        postordenRec(nodo.der, lista);
        lista.add(nodo.valor);
    }

    /** Formatea una lista como cadena legible: [ 10, 20, 30 ] */
    public static String listaAString(List<Integer> lista) {
        if (lista.isEmpty()) return "[ vacio ]";
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i < lista.size(); i++) {
            sb.append(lista.get(i));
            if (i < lista.size() - 1) sb.append(", ");
        }
        sb.append(" ]");
        return sb.toString();
    }
}
