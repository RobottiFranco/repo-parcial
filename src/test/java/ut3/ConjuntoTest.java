package ut3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ut3.TDA.*;


public class ConjuntoTest {

    Conjunto<Integer> conjunto1;
    Conjunto<Integer> conjunto2;

    @BeforeEach
    void setUp() {
        conjunto1 = new Conjunto<Integer>();
        conjunto2 = new Conjunto<Integer>();
    }

    @Test
    void testInterseccion() {
        for (int i = 1; i <= 10; i++) {
            conjunto1.insertar(i, i);
        }

        for (int i = 3; i <= 5; i++) {
            conjunto2.insertar(i, i);
        }

        IConjunto<Integer> interseccion = conjunto1.interseccion(conjunto2);

        assertEquals(3, interseccion.cantElementos());

        for (int i = 3; i <= 5; i++) {
            Nodo<Integer> result = interseccion.buscar(i);
            assertNotEquals(null, result);
            assertEquals(i, result.getEtiqueta());
            assertEquals(i, result.getDato());
        }
    }

    @Test
    void testInterseccion2() {
        for (int i = 1; i <= 10; i++) {
            conjunto1.insertar(i, i);
        }

        for (int i = 3; i <= 5; i++) {
            conjunto2.insertar(i, i);
        }

        IConjunto<Integer> interseccion = conjunto2.interseccion(conjunto1);

        assertEquals(3, interseccion.cantElementos());

        for (int i = 3; i <= 5; i++) {
            Nodo<Integer> result = interseccion.buscar(i);
            assertNotEquals(null, result);
            assertEquals(i, result.getEtiqueta());
            assertEquals(i, result.getDato());
        }
    }

    @Test
    void testInterseccion3() {
        for (int i = 1; i <= 10; i++) {
            conjunto1.insertar(i, i);
        }

        for (int i = 11; i <= 20; i++) {
            conjunto2.insertar(i, i);
        }

        IConjunto<Integer> interseccion = conjunto1.interseccion(conjunto2);

        assertEquals(true, interseccion.esVacia());
        assertEquals(0, interseccion.cantElementos());
    }

    @Test
    void testInterseccion4() {

        for (int i = 11; i <= 20; i++) {
            conjunto1.insertar(i, i);
        }

        IConjunto<Integer> interseccion = conjunto1.interseccion(conjunto2);

        assertEquals(true, interseccion.esVacia());
        assertEquals(0, interseccion.cantElementos());
    }

    @Test
    void testInterseccion5() {

        for (int i = 11; i <= 20; i++) {
            conjunto1.insertar(i, i);
        }

        IConjunto<Integer> interseccion = conjunto2.interseccion(conjunto1);

        assertEquals(true, interseccion.esVacia());
        assertEquals(0, interseccion.cantElementos());
    }

    @Test
    void testInterseccion6() {

        for (int i = 11; i <= 20; i++) {
            conjunto2.insertar(i, i);
        }

        IConjunto<Integer> interseccion = conjunto1.interseccion(conjunto2);

        assertEquals(true, interseccion.esVacia());
        assertEquals(0, interseccion.cantElementos());
    }

    @Test
    void testInterseccion7() {

        for (int i = 11; i <= 20; i++) {
            conjunto2.insertar(i, i);
        }

        IConjunto<Integer> interseccion = conjunto2.interseccion(conjunto1);

        assertEquals(true, interseccion.esVacia());
        assertEquals(0, interseccion.cantElementos());
    }

    @Test
    void testInterseccion8() {

        IConjunto<Integer> interseccion = conjunto2.interseccion(conjunto1);

        assertEquals(true, interseccion.esVacia());
        assertEquals(0, interseccion.cantElementos());
    }

    @Test
    void testUnion() {
        for (int i = 1; i <= 10; i++) {
            conjunto1.insertar(i, i);
        }

        for (int i = 11; i <= 20; i++) {
            conjunto2.insertar(i, i);
        }

        IConjunto<Integer> union = conjunto1.union(conjunto2);

        assertEquals(20, union.cantElementos());

        for (int i = 1; i <= 20; i++) {
            Nodo<Integer> result = union.buscar(i);
            assertNotEquals(null, result);
            assertEquals(i, result.getEtiqueta());
            assertEquals(i, result.getDato());
        }
    }

    @Test
    void testUnion2() {
        for (int i = 1; i <= 10; i++) {
            conjunto1.insertar(i, i);
        }

        for (int i = 2; i <= 9; i++) {
            conjunto2.insertar(i, i);
        }

        IConjunto<Integer> union = conjunto1.union(conjunto2);

        assertEquals(10, union.cantElementos());

        for (int i = 1; i <= 10; i++) {
            Nodo<Integer> result = union.buscar(i);
            assertNotEquals(null, result);
            assertEquals(i, result.getEtiqueta());
            assertEquals(i, result.getDato());
        }
    }

    @Test
    void testUnion3() {

        IConjunto<Integer> union = conjunto1.union(conjunto2);

        assertEquals(0, union.cantElementos());
        assertEquals(true, union.esVacia());

    }

    @Test
    void testDiferenciaSimetrica() {

        for (int i = 1; i <= 10; i++) {
            conjunto1.insertar(i, null);
        }

        for (int i = 6; i <= 15; i++) {
            conjunto2.insertar(i, null);
        }

        Conjunto difSimetrica = conjunto1.diferenciaSimetrica(conjunto2);

        assertEquals(10, difSimetrica.cantElementos());

        for (int i = 1; i <= 5; i++) {
            assertNotEquals(null, difSimetrica.buscar(i));
        }

        for (int i = 6; i <= 10; i++) {
            assertEquals(null, difSimetrica.buscar(i));
        }

        for (int i = 11; i <= 15; i++) {
            assertNotEquals(null, difSimetrica.buscar(i));
        }
    }

    @Test
    void testDiferenciaSimetrica2() {

        for (int i = 1; i <= 10; i++) {
            conjunto1.insertar(i, null);
        }

        for (int i = 11; i <= 20; i++) {
            conjunto2.insertar(i, null);
        }

        Conjunto difSimetrica = conjunto1.diferenciaSimetrica(conjunto2);

        assertEquals(20, difSimetrica.cantElementos());

        for (int i = 1; i <= 20; i++) {
            assertNotEquals(null, difSimetrica.buscar(i));
        }
    }

    @Test
    void testDiferenciaSumetrica3() {
        Conjunto difSimetrica = conjunto1.diferenciaSimetrica(conjunto2);
        assertEquals(0, difSimetrica.cantElementos());
    }

    @Test
    void testDiferenciaSimetricaOtro() {

        for (int i = 1; i <= 10; i++) {
            conjunto1.insertar(i, null);
        }

        for (int i = 6; i <= 15; i++) {
            conjunto2.insertar(i, null);
        }

        Conjunto difSimetrica = conjunto1.diferenciaSimetricaOtro(conjunto2);

        assertEquals(10, difSimetrica.cantElementos());

        for (int i = 1; i <= 5; i++) {
            assertNotEquals(null, difSimetrica.buscar(i));
        }

        for (int i = 6; i <= 10; i++) {
            assertEquals(null, difSimetrica.buscar(i));
        }

        for (int i = 11; i <= 15; i++) {
            assertNotEquals(null, difSimetrica.buscar(i));
        }
    }

    @Test
    void testDiferenciaSimetricaOtro3() {

        for (int i = 1; i <= 10; i++) {
            conjunto1.insertar(i, null);
        }

        for (int i = 11; i <= 20; i++) {
            conjunto2.insertar(i, null);
        }

        Conjunto difSimetrica = conjunto1.diferenciaSimetricaOtro(conjunto2);

        assertEquals(20, difSimetrica.cantElementos());

        for (int i = 1; i <= 20; i++) {
            assertNotEquals(null, difSimetrica.buscar(i));
        }
    }

    @Test
    void testDiferenciaSumetricaOtro3() {
        Conjunto difSimetrica = conjunto1.diferenciaSimetricaOtro(conjunto2);
        assertEquals(0, difSimetrica.cantElementos());
    }
}
