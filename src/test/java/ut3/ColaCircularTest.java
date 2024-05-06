package ut3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ut3.TDA.ColaCircular;
import ut3.TDA.ICola;

import org.junit.jupiter.api.BeforeEach;

public class ColaCircularTest {

    ICola<String> cola;
    @BeforeEach
    void SetUp(){
        cola = new ColaCircular<String>(5);
    }

    @Test
    void testEncolar(){
        assertEquals(cola.cantElementos(), 0);
        cola.encolar("a");
        assertEquals(cola.cantElementos(), 1);
    }

    @Test
    void testDesencolar(){
        cola.encolar("a");
        assertEquals(cola.desencolar(), "a");
        assertEquals(cola.cantElementos(), 0);
    }

    @Test
    void testEncolarSobreCapacidad(){
        for (int i=0; i<6; i++){
            cola.encolar("a");
        }
        assertEquals(cola.cantElementos(), 6);
    }

    @Test
    void testEncolamientoYDesencolamientoOrdenado(){
        for (int i = 0; i<5; i++){
            cola.encolar(i + "");
        }
        for(int i=0; i<5; i++){
            assertEquals(cola.desencolar(), i+"");
        }
    }

}
