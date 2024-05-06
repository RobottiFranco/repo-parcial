package ut3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ut3.TDA.Lista;
import ut3.TDA.Nodo;

public class EliminarYDevolverTest {

    Lista<Integer> lista;

    @BeforeEach
    void setUp() {
        lista = new Lista<>();
    }

    void insertar5Nodos() {

        Nodo<Integer> actual;

        for (int i = 1; i <= 5; i++) {
            actual = new Nodo<Integer>(i, i);
            lista.insertar(actual);
        }
    }

    @Test
    void testEliminarYDevolverPrimero() {
        insertar5Nodos();

        Nodo<Integer> eliminado = lista.eliminarYDevolver(1);

        assertEquals(1, eliminado.getEtiqueta());
        assertEquals(null, eliminado.getSiguiente());
        assertEquals(4, lista.cantElementos());

        assertEquals(2, lista.getPrimero().getEtiqueta());
        assertEquals(2, lista.getPrimero().getDato());

        Nodo<Integer> actual = lista.getPrimero();

        while (actual != null) {
            assertNotEquals(1, actual.getEtiqueta());
            assertNotEquals(1, actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    @Test
    void testEliminarYDevolverUltimo() {
        insertar5Nodos();

        Nodo<Integer> eliminado = lista.eliminarYDevolver(5);

        assertEquals(5, eliminado.getEtiqueta());
        assertEquals(null, eliminado.getSiguiente());
        assertEquals(4, lista.cantElementos());

        Nodo<Integer> actual = lista.getPrimero();

        while (actual != null) {
            assertNotEquals(5, actual.getEtiqueta());
            assertNotEquals(5, actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    @Test
    void testEliminarYDevolverIntermedio() {
        insertar5Nodos();

        Nodo<Integer> eliminado = lista.eliminarYDevolver(3);

        assertEquals(3, eliminado.getEtiqueta());
        assertEquals(null, eliminado.getSiguiente());
        assertEquals(4, lista.cantElementos());

        Nodo<Integer> actual = lista.getPrimero();

        while (actual != null) {
            assertNotEquals(3, actual.getEtiqueta());
            assertNotEquals(3, actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    @Test
    void testEliminarYDevolverInexistente() {
        insertar5Nodos();

        Nodo<Integer> eliminado = lista.eliminarYDevolver(6);

        assertEquals(null, eliminado);
        assertEquals(5, lista.cantElementos());

        Nodo<Integer> actual = lista.getPrimero();
        int clave = 1;

        while (actual != null) {
            assertEquals(clave, actual.getEtiqueta());
            assertEquals(clave, actual.getDato());
            actual = actual.getSiguiente();
            clave++;
        }
    }

    @Test
    void testEliminarYDevolverDeListaVacia() {

        assertEquals(0, lista.cantElementos());

        Nodo<Integer> eliminado = lista.eliminarYDevolver(6);
        assertEquals(null, eliminado);

        assertEquals(0, lista.cantElementos());
    }
}
