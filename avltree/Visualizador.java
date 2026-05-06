package avltree;

/**
 * Visualizador del Árbol AVL.
 *
 * Devuelve el árbol rotado 90° usando caracteres Unicode en formato String
 * para que pueda ser mostrado fácilmente en interfaces gráficas (como un JTextArea).
 *
 * ┌── rama derecha (aparece arriba)
 * └── rama izquierda (aparece abajo)
 * │   línea vertical de continuación
 */
public class Visualizador {

 
    // Metodos publicos (devuelven String para la interfaz grafica)
  

    public static String obtenerArbolComoTexto(Nodo raiz, ArbolAVL arbol) {
        if (raiz == null) {
            return "\n  (árbol vacío)\n";
        }
        return "\n" + dibujar(raiz, arbol, "", false, false) + "\n";
    }

    public static String obtenerEstadisticas(Nodo raiz, ArbolAVL arbol) {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------\n");
        sb.append(String.format("-  Altura total  : %-10d -%n", raiz != null ? raiz.altura : 0));
        sb.append(String.format("-  Total nodos   : %-10d -%n", contarNodos(raiz)));
        sb.append(String.format("-  FB raiz       : %-10d -%n", raiz != null ? arbol.factorBalanceo(raiz) : 0));
        sb.append("--------------------------\n");
        return sb.toString();
    }

    
    // Algoritmo de dibujo (recursivo)
    

    /**
     * Genera el árbol como texto usando el patrón de "rotación 90°".
     * Orden de procesamiento: hijo derecho → nodo actual → hijo izquierdo.
     * Como la consola o JTextArea imprime de arriba hacia abajo, eso coloca el hijo
     * derecho arriba y el izquierdo abajo, igual que un árbol real.
     */
    private static String dibujar(Nodo nodo, ArbolAVL arbol,
                                   String prefijo, boolean esIzq, boolean esDer) {
        if (nodo == null) return "";

        StringBuilder sb = new StringBuilder();
        int fb = arbol.factorBalanceo(nodo);

        // ── Subárbol derecho (se imprimirá arriba) ──
        if (nodo.der != null) {
            String nuevoPrefijo = prefijo + (esDer ? "    " : esIzq ? "|   " : "");
            sb.append(dibujar(nodo.der, arbol, nuevoPrefijo, false, true));
            sb.append("\n");
        }

        // Nodo actual
        sb.append(prefijo);
        if (esIzq)       sb.append("\\-- ");
        else if (esDer)  sb.append("/-- ");
        else             sb.append("--- ");        // raíz

        sb.append(nodo.valor);
        sb.append(" (h:").append(nodo.altura);
        sb.append(", fb:").append(fb).append(")");

        // Indicador de desbalance (no debería verse en un AVL correcto)
        if (Math.abs(fb) > 1) sb.append(" DESBALANCEADO");

        // Subárbol izquierdo (se imprimirá abajo) 
        if (nodo.izq != null) {
            sb.append("\n");
            String nuevoPrefijo = prefijo + (esIzq ? "    " : esDer ? "|   " : "");
            sb.append(dibujar(nodo.izq, arbol, nuevoPrefijo, true, false));
        }

        return sb.toString();
    }

    // Utilidades internas
  

    private static int contarNodos(Nodo nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodos(nodo.izq) + contarNodos(nodo.der);
    }
}