package ut3.TDA;


public interface ICola<T> {
    public void encolar(T objeto);

    public T desencolar();

    public boolean esVacia();

    public boolean estaLlena();

    public int cantElementos();

    public String imprimir(String separador);
}
