package ut3.TDA;

import ut3.utils.ManejadorArchivosGenerico;

public class Pila<T> extends Lista<T> implements IPila<T> {

    // private Nodo<T> primero;

    public Pila() {
        super();
    }

    @Override
    public Nodo<T> peek() {
        return this.getPrimero();
    }

    @Override
    public Nodo<T> pop() {

        if (this.esVacia()) {
            return null;
        }

        Nodo<T> result = this.getPrimero();

        this.setPrimero(this.getPrimero().getSiguiente());

        result.setSiguiente(null);

        return result;
    }

    @Override
    public void push(Nodo<T> nodo) {
        this.insertarAlPrincipio(nodo);
    }

    public boolean controlCorchetes(ILista<T> lista) {

        Nodo<T> actual = lista.getPrimero();

        while (actual != null) {

            if (actual.getDato().equals('{')) {

                this.push(new Nodo<T>(null, null));

            } else if (actual.getDato().equals('}')) {

                if (this.esVacia()) return false;

                this.pop();
            }

            actual = actual.getSiguiente();
        }

        return this.esVacia();
    }

    public static void main(String[] args) {

        String[] entrada = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\uy\\edu\\ucu\\aed\\entradas.txt");
        Pila<Character> pila = new Pila<>();
        Lista<Character> listaCaracteres = new Lista<>();
        char c;
        int numeroLinea = 1;

        for (String linea : entrada) {

            for (int i = 0; i < linea.length(); i++) {
                c = linea.charAt(i);
                listaCaracteres.insertar(c, c);
            }

            System.out.println(numeroLinea + " - " + pila.controlCorchetes(listaCaracteres));

            numeroLinea++;

            pila.setPrimero(null);
            listaCaracteres.setPrimero(null);
        }
    }

}
