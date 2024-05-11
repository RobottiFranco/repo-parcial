package com;

@SuppressWarnings({"unchecked", "rawtypes"})
/**
 * Clase que representa un árbol AVL, que es un tipo especial de árbol binario de búsqueda
 * autobilanceado. Para cada nodo, la diferencia de alturas entre sus subárboles izquierdo y
 * derecho está como máximo en 1, lo que garantiza que la operación de búsqueda sea eficiente.
 *
 * @param <T> Tipo de dato que almacena el árbol.
 */
public class TArbolAVL<T> extends TArbolBB<T> {

    /**
     * Inserta un elemento en el árbol AVL. Si el elemento ya existe, no se inserta.
     *
     * @param etiqueta Etiqueta del elemento a insertar.
     * @param unDato   Dato del elemento a insertar.
     * @return Verdadero si se insertó el elemento con éxito, falso en caso contrario.
     */
    @Override
    public boolean insertar(Comparable etiqueta, T unDato) {
        TElementoAVL<T> nodo = new TElementoAVL<>(etiqueta, unDato);
        raiz = insertar(this.getRaiz(), nodo);
        return true; // Se asume que siempre se inserta el nodo (no se manejan duplicados)
    }

    /**
     * Obtiene el nodo raíz del árbol AVL.
     *
     * @return Nodo raíz del árbol AVL.
     */
    @Override
    public TElementoAVL<T> getRaiz() {
        return (TElementoAVL<T>) raiz;
    }

    /**
     * Método privado para insertar recursivamente un nodo en el árbol AVL.
     *
     * @param raiz             Nodo raíz del subárbol en el que se insertará el elemento.
     * @param elementoAInsertar Elemento a insertar.
     * @return Nodo raíz actualizado del subárbol después de insertar el elemento.
     */
    private TElementoAVL<T> insertar(TElementoAVL<T> raiz, TElementoAVL<T> elementoAInsertar) {
        // Insertar el nodo como en un árbol binario de búsqueda normal
        if (raiz == null) {
            return elementoAInsertar;
        }

        Comparable etiqueta = elementoAInsertar.getEtiqueta();
        if (etiqueta.compareTo(raiz.getEtiqueta()) < 0) {
            raiz.setHijoIzq(insertar(raiz.getHijoIzq(), elementoAInsertar));
        } else if (etiqueta.compareTo(raiz.getEtiqueta()) > 0) {
            raiz.setHijoDer(insertar(raiz.getHijoDer(), elementoAInsertar));
        } else {
            // El elemento ya existe en el árbol, no se permiten duplicados
            return raiz;
        }

        // Actualizar la altura del nodo actual
        raiz.setAltura(1 + Math.max(obtenerAltura(raiz.getHijoIzq()), obtenerAltura(raiz.getHijoDer())));

        // Balancear el árbol y devolver el nuevo nodo raíz
        return balancear(raiz);
    }

    /**
     * Método privado para obtener la altura de un nodo AVL.
     *
     * @param nodo Nodo del cual se obtendrá la altura.
     * @return Altura del nodo. Si el nodo es nulo, retorna 0.
     */
    private int obtenerAltura(TElementoAVL<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.getAltura();
    }

    /**
     * Método privado para obtener el balance de un nodo AVL, que es la diferencia
     * entre la altura de su subárbol izquierdo y la altura de su subárbol derecho.
     *
     * @param nodo Nodo del cual se obtendrá el balance.
     * @return Factor de balance del nodo.
     */
    private int obtenerBalance(TElementoAVL<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        return obtenerAltura(nodo.getHijoIzq()) - obtenerAltura(nodo.getHijoDer());
    }

    /**
     * Método privado para realizar una rotación hacia la izquierda.
     *
     * @param y Nodo sobre el cual se realizará la rotación.
     * @return Nuevo nodo raíz resultante de la rotación.
     */
    private TElementoAVL<T> rotarIzquierda(TElementoAVL<T> y) {
        TElementoAVL<T> x = y.getHijoDer();
        TElementoAVL<T> temp = x.getHijoIzq();

        // Realizar la rotación
        x.setHijoIzq(y);
        y.setHijoDer(temp);

        // Actualizar alturas
        y.setAltura(1 + Math.max(obtenerAltura(y.getHijoIzq()), obtenerAltura(y.getHijoDer())));
        x.setAltura(1 + Math.max(obtenerAltura(x.getHijoIzq()), obtenerAltura(x.getHijoDer())));

        return x;
    }

    /**
     * Método privado para realizar una rotación hacia la derecha.
     *
     * @param x Nodo sobre el cual se realizará la rotación.
     * @return Nuevo nodo raíz resultante de la rotación.
     */
    private TElementoAVL<T> rotarDerecha(TElementoAVL<T> x) {
        TElementoAVL<T> y = x.getHijoIzq();
        TElementoAVL<T> temp = y.getHijoDer();

        // Realizar la rotación
        y.setHijoDer(x);
        x.setHijoIzq(temp);

        // Actualizar alturas
        x.setAltura(1 + Math.max(obtenerAltura(x.getHijoIzq()), obtenerAltura(x.getHijoDer())));
        y.setAltura(1 + Math.max(obtenerAltura(y.getHijoIzq()), obtenerAltura(y.getHijoDer())));

        return y;
    }

    /**
     * Método privado para balancear el árbol AVL tras la inserción o eliminación de un nodo.
     * Realiza las rotaciones necesarias para mantener el balance del árbol.
     *
     * @param nodo Nodo a balancear.
     * @return Nodo raíz balanceado.
     */
    private TElementoAVL<T> balancear(TElementoAVL<T> nodo) {
        int balance = obtenerBalance(nodo);

        // Caso izquierda-izquierda
        if (balance > 1 && obtenerBalance(nodo.getHijoIzq()) >= 0) {
            return rotarDerecha(nodo);
        }

        // Caso derecha-derecha
        if (balance < -1 && obtenerBalance(nodo.getHijoDer()) <= 0) {
            return rotarIzquierda(nodo);
        }

        // Caso izquierda-derecha
        if (balance > 1 && obtenerBalance(nodo.getHijoIzq()) < 0) {
            nodo.setHijoIzq(rotarIzquierda(nodo.getHijoIzq()));
            return rotarDerecha(nodo);
        }

        // Caso derecha-izquierda
        if (balance < -1 && obtenerBalance(nodo.getHijoDer()) > 0) {
            nodo.setHijoDer(rotarDerecha(nodo.getHijoDer()));
            return rotarIzquierda(nodo);
        }

        return nodo; // Devolver el nodo balanceado
    }
}
