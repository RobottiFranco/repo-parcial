package ut5.TDA;

public interface IArbolG<T> {
    public TNodoAG<T> getRaiz();

    public void setRaiz(TNodoAG<T> raiz);

    public boolean insertar(Comparable unaEtiqueta, Comparable etiquetaPadre);

    public boolean insertar(Comparable unaEtiqueta, T dato, Comparable etiquetaPadre);

    public void eliminar(T elemento);

    public boolean buscar(Comparable unaEtiqueta);

    public void vaciar();

    public boolean esVacio();

    public int altura();

    public int nivel(T elemento);

    public int cantidadNodos();

    public int cantidadHojas();

    public String listarIndentadoPorNiveles();
}
