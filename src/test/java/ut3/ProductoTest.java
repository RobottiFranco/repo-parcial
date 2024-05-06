package ut3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ut3.TDA.Producto;


public class ProductoTest {

    Producto p;

    @BeforeEach
    void setUo() {
        p = new Producto(1, "Peras", 20, 30);
    }

    @Test
    void testRestarMenosStockDelExistente() {
        p.restarCantidadStock(10);
        assertEquals(20, p.getStock());
    }

    @Test
    void testRestarTodoElStockDelExistente() {
        p.restarCantidadStock(30);
        assertEquals(0, p.getStock());
    }

    @Test
    void testRestarMasStockDelExistente() {
        p.restarCantidadStock(40);
        assertEquals(0, p.getStock());
    }
}
