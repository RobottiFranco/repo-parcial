package ut6.TDA;

import java.util.Arrays;

public class THashCuadratico implements IHash {
    private Integer[] table;
    private int capacidadMaxima;
    private int size; // Número actual de elementos en la tabla
    private static final double FACTOR_DE_CARGA_MAXIMO = 0.7; // Umbral del factor de carga

    public THashCuadratico(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a cero.");
        }
        this.table = new Integer[cap];
        this.capacidadMaxima = cap;
        this.size = 0;
        Arrays.fill(this.table, null); // Inicializa todas las posiciones con null
    }

    @Override
    public int buscar(int unaClave) {
        int comparaciones = 0;
        int i = 0;
        int j;
        do {
            j = (this.funcionHashing(unaClave) + i * i) % capacidadMaxima; // Actualización para sondeo cuadrático
            comparaciones++;
            if (this.table[j] != null && this.table[j].equals(unaClave)) {
                return comparaciones;
            } else {
                i++;
            }
        } while (i < capacidadMaxima && this.table[j] != null);
        return -1; // Si no se encuentra la clave
    }

    @Override
    public int insertar(int unaClave) {
        // Verificar si necesitamos redimensionar antes de insertar
        if ((double) size / capacidadMaxima > FACTOR_DE_CARGA_MAXIMO) {
            redimensionar();
        }

        int comparaciones = 0;
        int i = 0;
        do {
            int j = (this.funcionHashing(unaClave) + i * i) % capacidadMaxima; // Usar sondeo cuadrático
            comparaciones++;
            if (this.table[j] == null) {
                this.table[j] = unaClave;
                size++; // Incrementar el número de elementos
                return comparaciones;
            } else {
                i++;
            }
        } while (i < capacidadMaxima);
        return comparaciones; // Si no hay espacio disponible
    }

    @Override
    public int funcionHashing(int unaClave) {
        return unaClave % capacidadMaxima;
    }

    // Método para redimensionar la tabla hash cuando el factor de carga es muy alto
    private void redimensionar() {
        int nuevaCapacidad = capacidadMaxima * 2;
        Integer[] nuevaTabla = new Integer[nuevaCapacidad];
        Arrays.fill(nuevaTabla, null);

        // Reinsertar todos los elementos en la nueva tabla
        for (int i = 0; i < capacidadMaxima; i++) {
            if (this.table[i] != null) {
                int unaClave = this.table[i];
                int j = funcionHashing(unaClave);
                int k = 0;
                while (nuevaTabla[(j + k * k) % nuevaCapacidad] != null) {
                    k++;
                }
                nuevaTabla[(j + k * k) % nuevaCapacidad] = unaClave;
            }
        }

        this.table = nuevaTabla;
        this.capacidadMaxima = nuevaCapacidad;
    }

    // Método para mostrar el contenido de la tabla (opcional, pero útil para
    // depuración)
    public void display() {
        for (int i = 0; i < capacidadMaxima; i++) {
            System.out.print("Index " + i + ": ");
            if (table[i] != null) {
                System.out.println(table[i]);
            } else {
                System.out.println("null");
            }
        }
    }

    public static void main(String[] args) {
        THashCuadratico hashTable = new THashCuadratico(10);
        hashTable.insertar(5);
        hashTable.insertar(15); // Colisión con el índice 5
        hashTable.insertar(25); // Colisión con el índice 5 y 6

        hashTable.display();

        System.out.println("Buscar 15: " + hashTable.buscar(15)); // Debe encontrarlo
        System.out.println("Buscar 20: " + hashTable.buscar(20)); // No debe encontrarlo
    }
}
