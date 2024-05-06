package ut3.TDA;

public class Lista<T> implements ILista<T> {

    private Nodo<T> primero;

    public Lista() {
        primero = null;
    }

    private String imprimirAlReves(Nodo<T> nodo) {

        if (nodo != null) {

            imprimirAlReves(nodo.getSiguiente());

            if (nodo.equals(this.primero)) {
                return nodo.getEtiqueta().toString();
            } else {
                return nodo.getEtiqueta().toString() + " ";
            }
        }
        return "";
    }

    public String imprimirAlReves() {
        return imprimirAlReves(this.primero);
    }

    private String imprimirAlReves(Nodo<T> nodo, String separador) {

        if (nodo != null) {

            imprimirAlReves(nodo.getSiguiente(), separador);

            if (nodo.equals(this.primero)) {
                return nodo.getEtiqueta().toString();
            } else {
                return nodo.getEtiqueta().toString() + separador;
            }
        }
        return "";
    }

    public String imprimirAlReves(String separador) {
        return imprimirAlReves(this.primero, separador);
    }

    @Override
    public void insertar(Nodo<T> nodo) {

        if (nodo == null) {
            throw new IllegalArgumentException("El nodo a insertar es nulo");
        }

        if (this.primero == null) {
            this.primero = nodo;
            return;
        }

        Nodo<T> actual = this.primero;

        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        actual.setSiguiente(nodo);
    }

    public void insertar(Comparable etiqueta, T dato) {
        Nodo<T> nodo = new Nodo<>(etiqueta, dato);
        this.insertar(nodo);
    }

    public void insertarAlPrincipio(Nodo<T> nodo) {

        if (nodo == null) {
            throw new IllegalArgumentException("El nodo a insertar es nulo");
        }

        nodo.setSiguiente(this.primero);
        this.primero = nodo;
    }

    public void insertarAlPrincipio(Comparable etiqueta, T dato) {
        Nodo<T> nodo = new Nodo<T>(etiqueta, dato);
        this.insertarAlPrincipio(nodo);
    }

    @Override
    public Nodo<T> buscar(Comparable clave) {

        Nodo<T> actual = this.primero;

        while (actual != null) {
            if (actual.getEtiqueta().compareTo(clave) == 0) {
                return actual;
            }
            actual = actual.getSiguiente();
        }

        return null;
    }

    @Override
    public boolean eliminar(Comparable clave) {

        if (this.primero == null) {
            System.out.println("Nada que eliminar. La lista está vacía.");
            return false;
        } else if (this.primero.getEtiqueta().compareTo(clave) == 0) {
            this.primero = this.primero.getSiguiente();
            return true;
        }

        Nodo<T> actual = this.primero;

        while (actual.getSiguiente() != null) {

            if (actual.getSiguiente().getEtiqueta().compareTo(clave) == 0) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public Nodo<T> eliminarYDevolver(Comparable clave) {

        if (this.primero == null) {
            return null;
        }

        Nodo<T> actual = this.primero;

        if (this.primero.compareTo(clave) == 0) {
            this.primero = this.primero.getSiguiente();
            actual.setSiguiente(null);
            return actual;
        }

        Nodo<T> siguiente = actual.getSiguiente();

        while (siguiente != null) {
            if (siguiente.compareTo(clave) == 0) {
                actual.setSiguiente(siguiente.getSiguiente());
                siguiente.setSiguiente(null);
                return siguiente;
            }
            actual = actual.getSiguiente();
            siguiente = siguiente.getSiguiente();
        }
        return null;
    }

    @Override
    public String imprimir() {

        if (esVacia())
            return "";

        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = this.primero;

        while (actual.getSiguiente() != null) {
            sb.append(actual.getEtiqueta() + " ");
            actual = actual.getSiguiente();
        }
        sb.append(actual.getEtiqueta());

        return sb.toString();
    }

    @Override
    public String imprimir(String separador) {

        if (esVacia())
            return "";

        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = this.primero;

        while (actual.getSiguiente() != null) {
            sb.append(actual.getEtiqueta() + separador);
            actual = actual.getSiguiente();
        }
        sb.append(actual.getEtiqueta());

        return sb.toString();
    }

    @Override
    public int cantElementos() {

        int result = 0;
        Nodo<T> actual = this.primero;

        while (actual != null) {
            actual = actual.getSiguiente();
            result++;
        }

        return result;
    }

    @Override
    public boolean esVacia() {
        return this.primero == null;
    }

    @Override
    public void setPrimero(Nodo<T> unNodo) {
        this.primero = unNodo;
    }

    @Override
    public Nodo<T> getPrimero() {
        return this.primero;
    }

}
