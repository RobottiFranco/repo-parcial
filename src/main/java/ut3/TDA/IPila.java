package ut3.TDA;

public interface IPila<T> {
    public void push(Nodo<T> nodo);

    public Nodo<T> pop();

    public Nodo<T> peek();
}
