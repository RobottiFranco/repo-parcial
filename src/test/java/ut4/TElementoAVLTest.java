package ut4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ut4.TDA.TElementoAVL;


public class TElementoAVLTest {

    @Test
    void testObtenerBalance() {
        TElementoAVL raiz = new TElementoAVL(5, null);
        assertEquals(0, raiz.obtenerBalance());

        raiz.setHijoIzq(new TElementoAVL(2, null));
        assertEquals(-1, raiz.obtenerBalance());

        raiz.getHijoIzq().setHijoIzq(new TElementoAVL(1, null));
        assertEquals(-2, raiz.obtenerBalance());

    }

    @Test
    void testEstaBalanceado() {
        TElementoAVL raiz = new TElementoAVL(1, null);
        assertEquals(true, raiz.estaBalanceado());

        raiz.setHijoIzq(new TElementoAVL(2, null));
        assertEquals(true, raiz.estaBalanceado());

        raiz.getHijoIzq().setHijoIzq(new TElementoAVL(2, null));
        assertEquals(false, raiz.estaBalanceado());

        raiz.setHijoDer(new TElementoAVL(null, null));
        assertEquals(true, raiz.estaBalanceado());

        raiz.getHijoDer().setHijoDer(new TElementoAVL(2, null));
        assertEquals(true, raiz.estaBalanceado());

        raiz.getHijoIzq().getHijoIzq().setHijoIzq(new TElementoAVL(2, null));
        assertEquals(false, raiz.estaBalanceado());

        raiz.getHijoIzq().setHijoDer(new TElementoAVL(2, null));
        assertEquals(true, raiz.estaBalanceado());
    }

    @Test
    void testInsertarAVL() {
        TElementoAVL raiz = new TElementoAVL(5, null);
        raiz.insertarAVL(new TElementoAVL(4, null));
        assertEquals(4, raiz.getHijoIzq().getEtiqueta());
        raiz.insertarAVL(new TElementoAVL(3, null));
        assertEquals(null, raiz.getHijoIzq());
    }

}
