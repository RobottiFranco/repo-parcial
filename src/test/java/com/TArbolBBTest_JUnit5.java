package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class TArbolBBTest_JUnit5 {

    private TArbolBB<Integer> arbol;

    @BeforeEach
    void init() {
        arbol = new TArbolBB<>();
    }

    @Test
    void dadoArbolVacio_cuandoInsertarEntoncesArbolNoVacio() {
        // Dado
        assertTrue(arbol.esVacio());

        // Cuando
        boolean resultado = arbol.insertar(10, 10);

        // Entonces
        assertTrue(resultado);
        assertFalse(arbol.esVacio());
    }

    @Test
    void dadoArbolConElementos_cuandoInsertarElementoExistenteEntoncesFalso() {
        // Dado
        arbol.insertar(10, 10);
        assertFalse(arbol.esVacio());

        // Cuando
        boolean resultado = arbol.insertar(10, 10);

        // Entonces
        assertFalse(resultado);
    }

    @Test
    void dadoArbolVacio_cuandoBuscarEntoncesNull() {
        // Dado
        assertTrue(arbol.esVacio());

        // Cuando
        Integer resultado = arbol.buscar(10);

        // Entonces
        assertNull(resultado);
    }

    @Test
    void dadoArbolConElementos_cuandoBuscarElementoExistenteEntoncesElemento() {
        // Dado
        arbol.insertar(10, 10);

        // Cuando
        Integer resultado = arbol.buscar(10);

        // Entonces
        assertNotNull(resultado);
        assertEquals(Integer.valueOf(10), resultado);
    }

    @Test
    void dadoArbolConElementos_cuandoBuscarElementoNoExistenteEntoncesNull() {
        // Dado
        arbol.insertar(10, 10);

        // Cuando
        Integer resultado = arbol.buscar(20);

        // Entonces
        assertNull(resultado);
    }

    @Test
    void dadoArbolVacio_cuandoVaciarEntoncesFalso() {
        // Dado
        assertTrue(arbol.esVacio());

        // Cuando
        boolean resultado = arbol.vaciar();

        // Entonces
        assertFalse(resultado);
    }

    @Test
    void dadoArbolConElementos_cuandoVaciarEntoncesVerdadero() {
        // Dado
        arbol.insertar(10, 10);
        assertFalse(arbol.esVacio());

        // Cuando
        boolean resultado = arbol.vaciar();

        // Entonces
        assertTrue(resultado);
        assertTrue(arbol.esVacio());
    }

    @Test
    void dadoArbolVacio_cuandoInOrdenEntoncesListaVacia() {
        // Dado
        assertTrue(arbol.esVacio());

        // Cuando
        List<Integer> resultado = arbol.inOrden();

        // Entonces
        assertNull(resultado);
    }

    @Test
    void dadoArbolConElementos_cuandoInOrdenEntoncesListaOrdenada() {
        // Dado
        arbol.insertar(20, 20);
        arbol.insertar(10, 10);
        arbol.insertar(30, 30);

        // Cuando
        List<Integer> resultado = arbol.inOrden();

        // Entonces
        assertNotNull(resultado);
        assertArrayEquals(new Integer[]{10, 20, 30}, resultado.toArray(new Integer[0]));
    }

    @Test
    void dadoArbolConElementos_cuandoPreOrdenEntoncesListaPreOrden() {
        // Dado
        arbol.insertar(20, 20);
        arbol.insertar(10, 10);
        arbol.insertar(30, 30);

        // Cuando
        List<Integer> resultado = arbol.preOrden();

        // Entonces
        assertNotNull(resultado);
        assertArrayEquals(new Integer[]{20, 10, 30}, resultado.toArray(new Integer[0]));
    }

    @Test
    void dadoArbolConElementos_cuandoPostOrdenEntoncesListaPostOrden() {
        // Dado
        arbol.insertar(20, 20);
        arbol.insertar(10, 10);
        arbol.insertar(30, 30);

        // Cuando
        List<Integer> resultado = arbol.postOrden();

        // Entonces
        assertNotNull(resultado);
        assertArrayEquals(new Integer[]{10, 30, 20}, resultado.toArray(new Integer[0]));
    }

    @Test
    void dadoArbolVacio_cuandoEliminarEntoncesNoCambios() {
        // Dado
        assertTrue(arbol.esVacio());

        // Cuando
        arbol.eliminar(10);

        // Entonces
        assertTrue(arbol.esVacio());
    }

    @Test
    void dadoArbolConElementos_cuandoEliminarElementoNoExistenteEntoncesArbolInalterado() {
        // Dado
        arbol.insertar(10, 10);
        assertFalse(arbol.esVacio());

        // Cuando
        arbol.eliminar(20);

        // Entonces
        assertFalse(arbol.esVacio());
        assertNotNull(arbol.buscar(10));
    }

    @Test
    void dadoArbolConElementos_cuandoEliminarElementoExistenteEntoncesElementoEliminado() {
        // Dado
        arbol.insertar(10, 10);
        arbol.insertar(20, 20);
        arbol.insertar(30, 30);
        assertFalse(arbol.esVacio());

        // Cuando
        arbol.eliminar(20);

        // Entonces
        assertNull(arbol.buscar(20));
        assertNotNull(arbol.buscar(10));
        assertNotNull(arbol.buscar(30));
    }
}
