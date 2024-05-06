package ut3.TDA;

public class ColaCircular<T> implements ICola<T> {

    private T[] cola;

    private int comienzo;

    private int fin;

    private int cantElementos;

    private int capacidad;

    public ColaCircular(int capacidad){
        this.capacidad = capacidad;
        cola = (T[]) new Object[capacidad];
        comienzo = 0;
        fin = -1;
        cantElementos = 0;
    }

    private void redimensionar(int nuevoTamano){
        if (nuevoTamano > this.capacidad){
            T[] nuevaCola = (T[]) new Object[nuevoTamano];
            for (int i = 0; i<cola.length; i++) {
                nuevaCola[i] = cola [i];
            }
            this.cola = nuevaCola;
        }
    }
    @Override
    public void encolar(T objeto) {
        if (this.estaLlena()){
            this.redimensionar(capacidad*2);
        }
        fin = (fin+1) % capacidad;
        cola[fin]=objeto;
        cantElementos++;
    }

    @Override
    public T desencolar() {
        if(this.esVacia()){
            System.out.println("Cola vacía, no se pueden extraer elementos");
            return null;
        }
        T objeto = cola[comienzo];
        comienzo = (comienzo +1)%capacidad;
        cantElementos--;
        return objeto;
    }

    @Override
    public boolean esVacia() {
        return cantElementos == 0;
    }

    @Override
    public boolean estaLlena() {
        return cantElementos != 0;
    }

    @Override
    public int cantElementos() {
        return cantElementos;
    }

    @Override
    public String imprimir(String separador) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i<cantElementos; i++){
            int indice = (comienzo + i)% capacidad;
            stringBuilder.append(cola[indice].toString() + separador);
        }
        return stringBuilder.toString();
    }

}
