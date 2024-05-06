package ut3.TDA;

public class Conjunto<T> extends Lista<T> implements IConjunto<T> {

    @Override
    public IConjunto<T> union(IConjunto<T> otroConjunto) {

        Nodo<T> actual = this.getPrimero();
        Nodo<T> nodoAInsertar;
        Conjunto<T> union = new Conjunto<T>();

        while (actual != null) {
            union.insertar(actual.clonar());
            actual = actual.getSiguiente();
        }

        actual = otroConjunto.getPrimero();

        while (actual != null) {

            nodoAInsertar = union.buscar(actual.getEtiqueta());

            if (nodoAInsertar == null) {
                union.insertar(actual.clonar());
            }
            actual = actual.getSiguiente();
        }
        return union;
    }

    @Override
    public IConjunto<T> interseccion(IConjunto<T> otroConjunto) {

        Nodo<T> actual = this.getPrimero();
        Nodo<T> nodoAInsertar;
        Conjunto<T> interseccion = new Conjunto<T>();

        while (actual != null) {

            nodoAInsertar = otroConjunto.buscar(actual.getEtiqueta());

            if (nodoAInsertar != null) {
                interseccion.insertar(nodoAInsertar.clonar());
            }
            actual = actual.getSiguiente();
        }

        return interseccion;
    }

    public Conjunto<T> diferenciaSimetrica(IConjunto<T> otroConjunto) {

        Conjunto difSimetrica = new Conjunto<>();

        Nodo actual = this.getPrimero();

        while (actual != null) {

            Nodo aux = otroConjunto.buscar(actual.getEtiqueta());

            if (aux == null) {
                difSimetrica.insertar(actual.clonar());
            }

            actual = actual.getSiguiente();
        }

        actual = otroConjunto.getPrimero();

        while (actual != null) {
            Nodo aux = this.buscar(actual.getEtiqueta());

            if (aux == null) {
                difSimetrica.insertar(actual.clonar());
            }
            actual = actual.getSiguiente();
        }

        return difSimetrica;
    }

    public Conjunto<T> diferenciaSimetricaOtro(IConjunto<T> otroConjunto) {

        Conjunto resultado = new Conjunto<>();

        Nodo actualP = this.getPrimero();
        Nodo actualC = otroConjunto.getPrimero();

        while (actualP != null) {
            actualC = otroConjunto.getPrimero();
            while (actualC != null) {

                if (actualC.getEtiqueta().compareTo(actualP.getEtiqueta()) != 0) {

                    resultado.insertar(actualP);
                    this.eliminar(actualP.getEtiqueta());

                } else {

                    this.eliminar(actualP.getEtiqueta());
                    otroConjunto.eliminar(actualC.getEtiqueta());

                }
                resultado.insertar(actualC);
                actualC = actualC.getSiguiente();
            }
            actualP = actualP.getSiguiente();
        }

        return resultado;
    }

    public static void main(String[] args) {

    }

}
