# Árbol Binario de Búsqueda Balanceado (AVL)

Práctica de Estructura de Datos — 4to semestre  
Implementación de un Árbol AVL en Java con menú interactivo y visualización gráfica en consola.

---

## ¿Qué es un Árbol AVL?

Un Árbol AVL es un Árbol Binario de Búsqueda que se mantiene **balanceado automáticamente** después de cada inserción o eliminación. El balanceo se logra mediante **rotaciones** que garantizan que la diferencia de altura entre el subárbol izquierdo y derecho de cualquier nodo (factor de balanceo) nunca sea mayor a 1.

Esto asegura que las operaciones de búsqueda, inserción y eliminación siempre sean eficientes: **O(log n)**.

---

## Estructura del proyecto

```
avltree/
├── Nodo.java          → Modelo de datos: valor, altura, hijos izq/der
├── ArbolAVL.java      → Lógica del árbol: insertar, borrar, rotaciones, balanceo
├── Recorridos.java    → Recorridos: inorden, preorden, postorden
├── Visualizador.java  → Motor de representación gráfica del proyecto
├── 
└── 
```

Cada clase tiene una responsabilidad única (principio de separación de responsabilidades).

---

## Funcionalidades

- Crear el nodo raíz del árbol
- Insertar nuevos nodos aplicando las reglas del AVL
- Borrar un nodo y rebalancear automáticamente
- Balanceo automático mediante los 4 tipos de rotación:
  - LL — Rotación simple a la derecha
  - RR — Rotación simple a la izquierda
  - LR — Rotación doble izquierda-derecha
  - RL — Rotación doble derecha-izquierda
- Recorrido Inorden (Izq → Raíz → Der)
- Recorrido Preorden (Raíz → Izq → Der)
- Recorrido Postorden (Izq → Der → Raíz)
- Visualización gráfica del árbol en consola ⭐
- Estadísticas del árbol (altura, total de nodos, factor de balanceo de la raíz)
- Búsqueda de valores
- Árbol de ejemplo precargado

---

## Visualización en consola ⭐

El árbol se dibuja rotado 90° usando caracteres Unicode. El hijo derecho aparece arriba y el izquierdo abajo, igual que un árbol real visto de frente. Cada nodo muestra su altura y factor de balanceo.

```
        ┌── 60 (h:1, fb:0)
    ┌── 50 (h:2, fb:0)
    │   └── 40 (h:1, fb:0)
─── 30 (h:4, fb:1)
    │       ┌── 25 (h:1, fb:0)
    │   ┌── 20 (h:2, fb:-1)
    └── 15 (h:3, fb:0)
        └── 10 (h:2, fb:1)
            └── 5 (h:1, fb:0)
```

- `h` = altura del nodo
- `fb` = factor de balanceo (izquierda − derecha), siempre entre -1 y 1 en un AVL correcto

---

## Cómo compilar y ejecutar

### Requisitos
- Java 8 o superior
- NetBeans, IntelliJ, Eclipse o cualquier terminal con `javac`

### Desde terminal

```bash
# Compilar
javac -encoding UTF-8 -d out avltree/*.java

# Ejecutar
java -cp out avltree.Main
```

### Desde NetBeans
1. `File` → `New Project` → `Java Application`
2. Clic derecho en `Source Packages` → `New` → `Java Package` → nombre: `avltree`
3. Copiar los 6 archivos `.java` en la carpeta `avltree` desde el Explorador de Windows
4. Clic derecho en `Main.java` → `Run File`

---

---

## Ejemplo de recorridos

Para el árbol de ejemplo con valores `5, 10, 15, 20, 25, 30, 40, 50, 60`:

| Recorrido | Resultado |
|-----------|-----------|
| Inorden   | `[ 5, 10, 15, 20, 25, 30, 40, 50, 60 ]` |
| Preorden  | `[ 30, 15, 10, 5, 20, 25, 50, 40, 60 ]` |
| Postorden | `[ 5, 10, 25, 20, 15, 40, 60, 50, 30 ]` |

El inorden de un AVL **siempre regresa los valores ordenados ascendentemente**.

---

## División del equipo

| Integrante | Clases |
|------------|--------|
| Integrante A | `Nodo.java`, `ArbolAVL.java`, `Menu.java` |
| Integrante B | `Visualizador.java`, `Recorridos.java`, `Main.java` |

---

## Conceptos clave

**Factor de balanceo (fb):** diferencia entre la altura del subárbol izquierdo y el derecho. En un AVL siempre es -1, 0 o 1.

**Rotación simple (LL / RR):** se aplica cuando el desbalance ocurre en el mismo lado dos niveles seguidos.

**Rotación doble (LR / RL):** se aplica cuando el desbalance ocurre en lados alternos; requiere dos rotaciones consecutivas.

**Sucesor inorden:** al borrar un nodo con dos hijos, se reemplaza con el valor más pequeño del subárbol derecho para mantener el orden del BST.
