package ut4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ut4.TDA.TArbolAVL;
import ut4.TDA.TElementoAVL;

public class TArbolAVLTest {

    TArbolAVL arbol;

    @BeforeEach
    void setUp() {
        arbol = new TArbolAVL<>();
    }

    @Test
    void testInsertarLL() {

        arbol.insertarAVL(new TElementoAVL<>(5, null));
        assertEquals(5, arbol.getRaiz().getEtiqueta());
        assertEquals(true, arbol.estaBalanceado());

        arbol.insertarAVL(new TElementoAVL<>(4, null));
        assertEquals(5, arbol.getRaiz().getEtiqueta());
        assertEquals(true, arbol.estaBalanceado());

        arbol.insertarAVL(new TElementoAVL<>(2, null));
        assertEquals(4, arbol.getRaiz().getEtiqueta());
        assertEquals(true, arbol.estaBalanceado());

        arbol.insertarAVL(new TElementoAVL<>(6, null));
        arbol.insertarAVL(new TElementoAVL<>(3, null));
        arbol.insertarAVL(new TElementoAVL<>(1, null));
        arbol.insertarAVL(new TElementoAVL<>(0, null));
        arbol.insertarAVL(new TElementoAVL<>(-1, null));
        assertEquals(4, arbol.getRaiz().getEtiqueta());
        assertEquals(2, arbol.getRaiz().getHijoIzq().getEtiqueta());
        assertEquals(0, arbol.getRaiz().getHijoIzq().getHijoIzq().getEtiqueta());
        assertEquals(true, arbol.estaBalanceado());
    }

    @Test
    void testInsertarLR() {
        arbol.insertarAVL(new TElementoAVL<>(8, null));
        arbol.insertarAVL(new TElementoAVL<>(4, null));
        arbol.insertarAVL(new TElementoAVL<>(6, null));

        assertEquals(6, arbol.getRaiz().getEtiqueta());
        assertEquals(4, arbol.getRaiz().getHijoIzq().getEtiqueta());
        assertEquals(8, arbol.getRaiz().getHijoDer().getEtiqueta());
        assertEquals(true, arbol.estaBalanceado());

        arbol.insertarAVL(new TElementoAVL<>(3, null));
        arbol.insertarAVL(new TElementoAVL<>(5, null));
        arbol.insertarAVL(new TElementoAVL<>(7, null));
        arbol.insertarAVL(new TElementoAVL<>(1, null));
        arbol.insertarAVL(new TElementoAVL<>(2, null));

        /*
         * assertEquals(null, 0, 0, 0);
         */ }

    @Test
    void testEliminarRaiz() {
        arbol.insertarAVL(new TElementoAVL<>(5, null));
        arbol.insertarAVL(new TElementoAVL<>(3, null));
        arbol.insertarAVL(new TElementoAVL<>(6, null));
        arbol.insertarAVL(new TElementoAVL<>(2, null));
        arbol.insertarAVL(new TElementoAVL<>(4, null));
        arbol.insertarAVL(new TElementoAVL<>(7, null));
        arbol.insertarAVL(new TElementoAVL<>(1, null));

        arbol.eliminar(5);

        assertEquals(true, arbol.estaBalanceado());
        assertEquals(4, arbol.getRaiz().getEtiqueta());

        assertEquals(2, arbol.getRaiz().getHijoIzq().getEtiqueta());
        assertEquals(6, arbol.getRaiz().getHijoDer().getEtiqueta());

        assertEquals(1, arbol.getRaiz().getHijoIzq().getHijoIzq().getEtiqueta());
        assertEquals(3, arbol.getRaiz().getHijoIzq().getHijoDer().getEtiqueta());
        assertEquals(null, arbol.getRaiz().getHijoDer().getHijoIzq());
        assertEquals(7, arbol.getRaiz().getHijoDer().getHijoDer().getEtiqueta());

        assertEquals(null, arbol.getRaiz().getHijoIzq().getHijoIzq().getHijoIzq());
        assertEquals(null, arbol.getRaiz().getHijoIzq().getHijoIzq().getHijoDer());
        assertEquals(null, arbol.getRaiz().getHijoIzq().getHijoDer().getHijoIzq());
        assertEquals(null, arbol.getRaiz().getHijoIzq().getHijoDer().getHijoDer());
        assertEquals(null, arbol.getRaiz().getHijoDer().getHijoDer().getHijoIzq());
        assertEquals(null, arbol.getRaiz().getHijoDer().getHijoDer().getHijoDer());
    }

    @Test
    void testEliminarRaizCaso2() {
        arbol.insertarAVL(new TElementoAVL<>(3, null));
        arbol.insertarAVL(new TElementoAVL<>(1, null));
        arbol.insertarAVL(new TElementoAVL<>(5, null));
        arbol.insertarAVL(new TElementoAVL<>(2, null));
        arbol.insertarAVL(new TElementoAVL<>(4, null));
        arbol.insertarAVL(new TElementoAVL<>(6, null));
        arbol.insertarAVL(new TElementoAVL<>(7, null));

        arbol.eliminar(3);

        assertEquals(true, arbol.estaBalanceado());
        assertEquals(5, arbol.getRaiz().getEtiqueta());

        assertEquals(2, arbol.getRaiz().getHijoIzq().getEtiqueta());
        assertEquals(6, arbol.getRaiz().getHijoDer().getEtiqueta());

        assertEquals(1, arbol.getRaiz().getHijoIzq().getHijoIzq().getEtiqueta());
        assertEquals(4, arbol.getRaiz().getHijoIzq().getHijoDer().getEtiqueta());
        assertEquals(null, arbol.getRaiz().getHijoDer().getHijoIzq());
        assertEquals(7, arbol.getRaiz().getHijoDer().getHijoDer().getEtiqueta());

        assertEquals(null, arbol.getRaiz().getHijoIzq().getHijoIzq().getHijoIzq());
        assertEquals(null, arbol.getRaiz().getHijoIzq().getHijoIzq().getHijoDer());
        assertEquals(null, arbol.getRaiz().getHijoIzq().getHijoDer().getHijoIzq());
        assertEquals(null, arbol.getRaiz().getHijoIzq().getHijoDer().getHijoDer());
        assertEquals(null, arbol.getRaiz().getHijoDer().getHijoDer().getHijoIzq());
        assertEquals(null, arbol.getRaiz().getHijoDer().getHijoDer().getHijoDer());
    }

    @Test
    void testEliminarRR() {
        arbol.insertarAVL(new TElementoAVL<>(5, null));
        arbol.insertarAVL(new TElementoAVL<>(4, null));
        arbol.insertarAVL(new TElementoAVL<>(6, null));
        arbol.insertarAVL(new TElementoAVL<>(7, null));

        arbol.eliminar(4);
        assertEquals(true, arbol.estaBalanceado());
        assertEquals(6, arbol.getRaiz().getEtiqueta());
        assertEquals(5, arbol.getRaiz().getHijoIzq().getEtiqueta());
        assertEquals(7, arbol.getRaiz().getHijoDer().getEtiqueta());
    }

    @Test
    void testEliminarLL() {
        arbol.insertarAVL(new TElementoAVL<>(5, null));
        arbol.insertarAVL(new TElementoAVL<>(4, null));
        arbol.insertarAVL(new TElementoAVL<>(6, null));
        arbol.insertarAVL(new TElementoAVL<>(3, null));

        arbol.eliminar(6);
        assertEquals(true, arbol.estaBalanceado());
        assertEquals(4, arbol.getRaiz().getEtiqueta());
        assertEquals(3, arbol.getRaiz().getHijoIzq().getEtiqueta());
        assertEquals(5, arbol.getRaiz().getHijoDer().getEtiqueta());
    }

}
