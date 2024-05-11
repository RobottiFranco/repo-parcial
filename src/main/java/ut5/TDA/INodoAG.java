package ut5.TDA;

public interface INodoAG<T> {
    public TNodoAG<T> getHijoIzquierdo();

    public TNodoAG<T> getHermanoDerecho();

    public Comparable getEtiqueta();

    public T getDato();

    public void setHijoIzquierdo(TNodoAG<T> nodo);

    public void setHermanoDerecho(TNodoAG<T> nodo);

    public void setEtiqueta(Comparable unaEtiqueta);

    public void setDato(T dato);

    public boolean insertar(Comparable unaEtiqueta, Comparable etiquetaPadre);

    public boolean insertar(Comparable unaEtiqueta, T dato, Comparable etiquetaPadre);

    public void eliminar(T elemento);

    public boolean buscar(Comparable unaEtiqueta);

    public int altura();

    public int nivel(T elemento);

    public int cantidadNodos();

    public int cantidadHojas();

    public String listarIndentadoPorNiveles();
}
