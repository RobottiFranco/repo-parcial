package ut4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ut4.TDA.Lista;
import ut4.TDA.TArbolBB;
import ut4.TDA.TElementoAB;


public class TArbolBBTest {

    TArbolBB arbol;

    @BeforeEach
    void setUp() {
        arbol = new TArbolBB<>();
    }

    void insertarEnArbol() {
        arbol.insertar(new TElementoAB(12, 12));
        arbol.insertar(new TElementoAB(25, 25));
        arbol.insertar(new TElementoAB(14, 14));
        arbol.insertar(new TElementoAB(1, 1));
        arbol.insertar(new TElementoAB(33, 33));
        arbol.insertar(new TElementoAB(88, 88));
        arbol.insertar(new TElementoAB(45, 45));
        arbol.insertar(new TElementoAB(2, 2));
        arbol.insertar(new TElementoAB(7, 7));
        arbol.insertar(new TElementoAB(66, 66));
        arbol.insertar(new TElementoAB(5, 5));
        arbol.insertar(new TElementoAB(99, 99));
    }

    @Test
    void testEliminar() {
        insertarEnArbol();
        arbol.eliminar(99);
        arbol.eliminar(15); // No esta en el arbol
        arbol.eliminar(2);
        arbol.eliminar(12);
        arbol.eliminar(77); // No esta en el arbol
        arbol.eliminar(33);

        assertEquals("1 5 7 14 25 45 66 88", arbol.inOrden());
        assertEquals("7 1 5 25 14 88 45 66", arbol.preOrden());
        assertEquals("5 1 14 66 45 88 25 7", arbol.postOrden());

    }

    @Test
    void testEliminarDeArbolVacio() {
        assertEquals(true, arbol.esVacio());
        arbol.eliminar(10);
        assertEquals(true, arbol.esVacio());
    }

    @Test
    void testEliminarRaiz() {
        insertarEnArbol();
        assertEquals(12, arbol.getRaiz().getEtiqueta());
        arbol.eliminar(12);
        assertEquals(7, arbol.getRaiz().getEtiqueta());
    }

    @Test
    void testEliminar25() {
        insertarEnArbol();
        assertEquals(25, arbol.getRaiz().getHijoDer().getEtiqueta());

        arbol.eliminar(25);

        assertEquals(14, arbol.getRaiz().getHijoDer().getEtiqueta());
    }

    @Test
    void testInOrden() {
        insertarEnArbol();
        assertEquals("1 2 5 7 12 14 25 33 45 66 88 99", arbol.inOrden());
    }

    @Test
    void testPreOrden() {
        insertarEnArbol();
        assertEquals("12 1 2 7 5 25 14 33 88 45 66 99", arbol.preOrden());
    }

    @Test
    void testPostOrden() {
        insertarEnArbol();
        assertEquals("5 7 2 1 14 66 45 99 88 33 25 12", arbol.postOrden());
    }

    @Test
    void testRecorridosArbolVacio() {
        assertEquals("", arbol.inOrden());
        assertEquals("", arbol.preOrden());
        assertEquals("", arbol.postOrden());
    }

    @Test
    void testInOrdenLista() {
        insertarEnArbol();
        Lista lista = arbol.inorden();
        assertEquals("1-2-5-7-12-14-25-33-45-66-88-99", lista.imprimir("-"));
    }

    @Test
    void testObtenerAltura() {
        insertarEnArbol();
        assertEquals(5, arbol.obtenerAltura());
    }

    @Test
    void testObtenerAlturaArbolVacio() {
        assertEquals(-1, arbol.obtenerAltura());
    }

    @Test
    void testObtenerTamanio() {
        insertarEnArbol();
        assertEquals(12, arbol.obtenerTamanio());
    }

    @Test
    void testObtenerTamanioArbolVacio() {
        assertEquals(0, arbol.obtenerTamanio());
    }

    @Test
    void testObtenerNivel() {
        insertarEnArbol();
        assertEquals(0, arbol.obtenerNivel(12));

        assertEquals(1, arbol.obtenerNivel(1));
        assertEquals(1, arbol.obtenerNivel(25));

        assertEquals(2, arbol.obtenerNivel(2));
        assertEquals(2, arbol.obtenerNivel(14));
        assertEquals(2, arbol.obtenerNivel(33));

        assertEquals(3, arbol.obtenerNivel(7));
        assertEquals(3, arbol.obtenerNivel(88));

        assertEquals(4, arbol.obtenerNivel(5));
        assertEquals(4, arbol.obtenerNivel(45));
        assertEquals(4, arbol.obtenerNivel(99));

        assertEquals(5, arbol.obtenerNivel(66));

        assertEquals(-1, arbol.obtenerNivel(10));
    }

    @Test
    void testCantidadHojas() {
        insertarEnArbol();
        assertEquals(4, arbol.obtenerCantidadHojas());
    }



}
