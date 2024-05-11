package com;

@SuppressWarnings("rawtypes")
/**
 * Clase que representa un nodo de un árbol AVL, extendiendo la funcionalidad de un nodo de árbol binario
 * de búsqueda con el manejo de la altura para el balanceo del árbol.
 *
 * @param <T> Tipo de los datos que almacena el nodo.
 */
public class TElementoAVL<T> extends TElementoAB<T> {
    private int altura; // Altura del nodo dentro del árbol AVL.

    /**
     * Constructor para crear un nodo AVL con una etiqueta y datos asociados.
     * La altura se inicializa en 1, que es la altura de un nodo hoja en un árbol AVL.
     *
     * @param unaEtiqueta Etiqueta que identifica al nodo.
     * @param unosDatos   Datos del nodo.
     */
    public TElementoAVL(Comparable unaEtiqueta, T unosDatos) {
        super(unaEtiqueta, unosDatos);
        this.altura = 1; // Nodo hoja.
    }

    /**
     * Constructor para crear un nodo AVL a partir de otro nodo de árbol binario de búsqueda.
     * Se copian la etiqueta y los datos del nodo proporcionado.
     *
     * @param nodo Nodo de origen para la creación del nodo AVL.
     */
    public TElementoAVL(TElementoAB<T> nodo) {
        this(nodo.getEtiqueta(), nodo.getDatos());
    }

    /**
     * Obtiene la altura del nodo.
     *
     * @return Altura actual del nodo.
     */
    public int getAltura() {
        return this.altura;
    }

    /**
     * Establece la altura del nodo.
     *
     * @param altura Nueva altura del nodo.
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * Obtiene el hijo izquierdo del nodo, asegurándose de que es una instancia de TElementoAVL.
     *
     * @return Hijo izquierdo del nodo como TElementoAVL.
     */
    @Override
    public TElementoAVL<T> getHijoIzq() {
        return (TElementoAVL<T>)super.getHijoIzq();
    }

    /**
     * Obtiene el hijo derecho del nodo, asegurándose de que es una instancia de TElementoAVL.
     *
     * @return Hijo derecho del nodo como TElementoAVL.
     */
    @Override
    public TElementoAVL<T> getHijoDer() {
        return (TElementoAVL<T>)super.getHijoDer();
    }
}
