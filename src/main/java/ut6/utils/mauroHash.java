package ut6.utils;

import ut6.TDA.IHash;

public class mauroHash implements IHash {

    Integer[] arreglo;
    private int carga;
    public int capacidad;

    public mauroHash() {
        this.arreglo = new Integer[3];
        this.capacidad = 3;
    }

    @Override
    public int buscar(int unaClave) {
        int ubicacion = funcionHashing(unaClave);
        int i = 0;
        if (unaClave == 2722) {
            System.out.println("hola");

        }
        while (true) {
            i++;
            if (arreglo[ubicacion] == null) {
                return -1;
            }
            if (arreglo[ubicacion] == unaClave) {
                return i;
            }
            ubicacion = (ubicacion + 2 * i - 1) % capacidad;
        }
    }

    @Override
    public int insertar(int unaClave) {
        if (unaClave == 2722) {
            System.out.println("hola");

        }
        carga++;
        if (carga >= capacidad / 2) {
            reHash();
        }
        int ubicacion = funcionHashing(unaClave);
        int i = 0;
        while (true) {
            i++;
            if (arreglo[ubicacion] == null) {
                arreglo[ubicacion] = unaClave;
                return i;
            }
            ubicacion = (ubicacion + 2 * i - 1) % capacidad;// ubicacion = (ubicacion + 2*i - 1)%capacidad;
        }
    }

    @Override
    public int funcionHashing(int unaClave) {
        return unaClave % arreglo.length;
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int nextPrime(int n) {
        n++;
        if (n % 2 == 0) {
            n++;
        }
        for (; !isPrime(n); n += 2) {

        }
        return n;
    }

    private void reHash() {
        int nuevaCapacidad = nextPrime(this.capacidad * 2);
        Integer[] nuevoArreglo = new Integer[nuevaCapacidad];
        this.capacidad = nuevaCapacidad;
        Integer[] arreglo = this.arreglo;
        this.arreglo = nuevoArreglo;
        for (Integer i : arreglo) {
            if (i != null) {
                insertar(i);
            }
        }
    }

}
