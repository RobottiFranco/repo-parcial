package ut3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ut3.TDA.Nodo;


public class NodoTest {

    Nodo<Integer> nodo1;
    Nodo<Integer> nodo2;

    @BeforeEach
    void setUp() {
        nodo1 = new Nodo<Integer>(1, 1);
        nodo2 = new Nodo<Integer>(2, 2);
    }

    @Test
    void testClonar() {
        nodo1.setSiguiente(nodo2);
        Nodo<Integer> clon = nodo1.clonar();
        assertEquals(1, clon.getDato());
        assertEquals(1, clon.getEtiqueta());
        assertEquals(null, clon.getSiguiente());
    }

    @Test
    void testCompareToDistintos() {
        int result = nodo1.compareTo(nodo2.getEtiqueta());
        assertEquals(true, result < 0);
    }

    @Test
    void testCompareToIguales() {
        Nodo<Integer> otroNodo1 = new Nodo<Integer>(1, 1);
        int result = nodo1.compareTo(otroNodo1.getEtiqueta());
        assertEquals(true, result == 0);
    }

    @Test
    void testEqualsDistintos() {
        boolean result = nodo1.equals(nodo2);
        assertEquals(false, result);
    }

    @Test
    void testEqualsIguales() {
        Nodo<Integer> otroNodo1 = new Nodo<Integer>(1, 1);
        boolean result = nodo1.equals(otroNodo1);
        assertEquals(true, result);
    }

    @Test
    void testGetDato() {
        assertEquals(1, nodo1.getDato());
        assertEquals(2, nodo2.getDato());
    }

    @Test
    void testGetEtiqueta() {
        assertEquals(1, nodo1.getEtiqueta());
        assertEquals(2, nodo2.getEtiqueta());
    }

    @Test
    void testGetSiguienteNoNulo() {
        nodo1.setSiguiente(nodo2);
        assertEquals(2, nodo1.getSiguiente().getEtiqueta());
        assertEquals(2, nodo1.getSiguiente().getDato());
    }

    @Test
    void testGetSiguienteNulo() {
        assertEquals(null, nodo1.getSiguiente());
    }

    @Test
    void testSetDato() {
        nodo1.setDato(2);
        assertEquals(2, nodo1.getDato());
    }

    @Test
    void testSetSiguiente() {
        assertEquals(null, nodo1.getSiguiente());
        nodo1.setSiguiente(nodo2);
        assertEquals(2, nodo1.getSiguiente().getDato());
    }
}
