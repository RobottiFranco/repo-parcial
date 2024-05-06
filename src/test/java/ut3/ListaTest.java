package ut3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ut3.TDA.Lista;
import ut3.TDA.Nodo;

public class ListaTest {

    Lista<Integer> lista;

    @BeforeEach
    void setUp() {
        lista = new Lista<>();
    }

    @Test
    void testInsertarNodo() {

        Nodo<Integer> actual;

        for (int i = 1; i <= 10; i++) {
            actual = new Nodo<Integer>(i, i);
            lista.insertar(actual);
        }

        actual = lista.getPrimero();
        int cantidadNodos = 0;

        while (actual != null) {
            actual = actual.getSiguiente();
            cantidadNodos++;
        }

        assertEquals(10, cantidadNodos);

    }

    @Test
    void testInsertarDato() {

        for (int i = 1; i <= 10; i++) {
            lista.insertar(i, i);
        }

        Nodo<Integer> actual = lista.getPrimero();
        int cantidadNodos = 0;

        while (actual != null) {
            actual = actual.getSiguiente();
            cantidadNodos++;
        }

        assertEquals(10, cantidadNodos);
    }

    @Test
    void testBuscarExistente() {

        for (int i = 1; i <= 10; i++) {
            lista.insertar(i, i);
        }

        Nodo<Integer> nodo = lista.buscar(5);

        assertNotEquals(null, nodo);
        assertEquals(5, nodo.getDato());
        assertEquals(6, nodo.getSiguiente().getDato());
    }

    @Test
    void testBuscarNulo() {

        for (int i = 1; i <= 10; i++) {
            lista.insertar(i, i);
        }

        Nodo<Integer> nodo = lista.buscar(5);

        assertNotEquals(null, nodo);
        assertEquals(5, nodo.getDato());
        assertEquals(6, nodo.getSiguiente().getDato());
    }

    @Test
    void testCantElementos() {
        for (int i = 1; i <= 10; i++) {
            lista.insertar(i, i);
        }
        assertEquals(10, lista.cantElementos());
    }

    @Test
    void testCantElementosVacia() {
        assertEquals(0, lista.cantElementos());
    }

    @Test
    void testEliminarUnNodo() {

        for (int i = 1; i <= 5; i++) {
            lista.insertar(i, i);
        }

        assertEquals(true, lista.eliminar(3));
        Nodo<Integer> actual = lista.getPrimero();

        while (actual != null) {
            assertNotEquals(3, actual.getDato().intValue());
            actual = actual.getSiguiente();
        }
        assertEquals(4, lista.cantElementos());
    }

    @Test
    void testEliminarPrimero() {

        for (int i = 1; i <= 5; i++) {
            lista.insertar(i, i);
        }

        assertEquals(true, lista.eliminar(1));

        assertNotEquals(1, lista.getPrimero().getDato());
        assertEquals(2, lista.getPrimero().getDato());
    }

    @Test
    void testEliminarVariosNodos() {

        for (int i = 1; i <= 10; i++) {
            lista.insertar(i, i);
        }

        assertEquals(true, lista.eliminar(3));
        assertEquals(true, lista.eliminar(4));
        assertEquals(true, lista.eliminar(5));

        assertEquals(true, lista.eliminar(10));
        assertEquals(true, lista.eliminar(9));
        assertEquals(true, lista.eliminar(8));

        Nodo<Integer> actual = lista.getPrimero();

        while (actual != null) {
            assertNotEquals(3, actual.getDato().intValue());
            assertNotEquals(4, actual.getDato().intValue());
            assertNotEquals(5, actual.getDato().intValue());

            assertNotEquals(8, actual.getDato().intValue());
            assertNotEquals(9, actual.getDato().intValue());
            assertNotEquals(10, actual.getDato().intValue());

            actual = actual.getSiguiente();
        }
        assertEquals(4, lista.cantElementos());
    }

    @Test
    void testEliminarNodoInexistente() {

        for (int i = 1; i <= 10; i++) {
            lista.insertar(i, i);
        }

        assertEquals(false, lista.eliminar(11));

        Nodo<Integer> actual = lista.getPrimero();

        while (actual != null) {
            assertNotEquals(11, actual.getDato().intValue());
            actual = actual.getSiguiente();
        }
        assertEquals(10, lista.cantElementos());

    }

    @Test
    void testEliminarDeListaVacia() {
        assertEquals(false, lista.eliminar(1));
    }

    @Test
    void testEsVacia() {
        for (int i = 1; i <= 5; i++) {
            lista.insertar(i, i);
        }
        assertEquals(false, lista.esVacia());
    }

    @Test
    void testNoEsVacia() {
        assertEquals(true, lista.esVacia());
    }

    @Test
    void testGetPrimero() {
        for (int i = 1; i <= 5; i++) {
            lista.insertar(i, i);
        }
        assertNotEquals(null, lista.getPrimero());
        assertEquals(1, lista.getPrimero().getDato());
    }

    @Test
    void testGetPrimeroVacia() {
        assertEquals(null, lista.getPrimero());
    }

    @Test
    void testSetPrimero() {

        Nodo<Integer> actual;
        Nodo<Integer> nodo = new Nodo<Integer>(null, null);

        for (int i = 1; i <= 5; i++) {
            actual = new Nodo<Integer>(i, i);
            lista.insertar(actual);

            if (i == 3) {
                nodo = actual;
            }
        }
        lista.setPrimero(nodo);
        assertEquals(3, lista.getPrimero().getDato());
        assertEquals(3, lista.cantElementos());
    }

    @Test
    void testSetPrimeroNulo() {
        for (int i = 1; i <= 5; i++) {
            lista.insertar(i, i);
        }
        lista.setPrimero(null);
        assertEquals(null, lista.getPrimero());
        assertEquals(0, lista.cantElementos());
    }

    @Test
    void testInsertarAlPrincipio() {

        Nodo<Integer> nodo;

        for (int i = 1; i <= 5; i++) {
            nodo = new Nodo<Integer>(i, i);
            lista.insertarAlPrincipio(nodo);
        }

        assertEquals(5, lista.getPrimero().getDato());
    }

    @Test
    void testInsertarNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> lista.insertar(null),
                "El nodo a insertar es nulo");
    }

    @Test
    void testImprimir() {
        for (int i = 1; i <= 5; i++) {
            lista.insertar(i, i);
        }
        String result = lista.imprimir();
        String expected = "1 2 3 4 5";
        assertEquals(expected, result);
    }

    @Test
    void testImprimirConSeparador() {
        for (int i = 1; i <= 5; i++) {
            lista.insertar(i, i);
        }
        String result = lista.imprimir(" -> ");
        String expected = "1 -> 2 -> 3 -> 4 -> 5";
        assertEquals(expected, result);
    }

}
