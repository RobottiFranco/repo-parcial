package ut3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ut3.TDA.Nodo;
import ut3.TDA.Pila;

public class PilaTest {

    Pila<Integer> pila;

    @BeforeEach
    void setUp() {
        pila = new Pila<>();
    }

    @Test
    void testPush() {
        for (int i = 1; i <= 10; i++) {
            pila.push(new Nodo<Integer>(i, i));
        }

        assertEquals(10, pila.getPrimero().getDato().intValue());
    }

    @Test
    void testPeek() {
        for (int i = 1; i <= 10; i++) {
            pila.push(new Nodo<Integer>(i, i));
        }

        Nodo<Integer> actual = pila.pop();
        int i = 10;

        while (actual != null) {
            assertEquals(i, actual.getDato().intValue());
            actual = pila.pop();
            i--;
        }

    }

    @Test
    void testPeekVacia() {
        assertEquals(null, pila.peek());
    }

    @Test
    void testPop() {
        for (int i = 1; i <= 10; i++) {
            pila.push(new Nodo<Integer>(i, i));
        }
        assertEquals(10, pila.peek().getDato().intValue());
    }

    @Test
    void testPopVacia() {
        assertEquals(null, pila.pop());
    }

}
