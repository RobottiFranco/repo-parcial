package ut3;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ut3.TDA.*;


public class AlmacenTest {

    Almacen almacen;
    Producto p1;
    Producto p2;
    Producto p3;
    Producto p4;
    Producto p5;

    void insertarProductos() {
        almacen.insertarProducto(p1);
        almacen.insertarProducto(p2);
        almacen.insertarProducto(p3);
        almacen.insertarProducto(p4);
        almacen.insertarProducto(p5);
    }

    @BeforeEach
    void setup() {
        almacen = new Almacen("Almacen Pepito", "Avenida Italia", "098765432");
        p1 = new Producto(1, "Manzanas", 20, 74);
        p2 = new Producto(2, "Peras", 23, 26);
        p3 = new Producto(3, "Milanesas", 84, 53);
        p4 = new Producto(4, "Aspiradora", 250, 10);
        p5 = new Producto(5, "Computadora", 1000, 50);
    }

    @Test
    void testInsertarProducto() {
        almacen.insertarProducto(p1);
        almacen.insertarProducto(p2);
        almacen.insertarProducto(p3);
        almacen.insertarProducto(p4);
        almacen.insertarProducto(p5);

        assertEquals(5, almacen.cantidadProductos());

        String[] expectedNames = { "Manzanas", "Peras", "Milanesas", "Aspiradora", "Computadora" };
        int[] expectedPrices = { 20, 23, 84, 250, 1000 };
        int[] expectedStocks = { 74, 26, 53, 10, 50 };

        Nodo<IProducto> actual = almacen.getListaProductos().getPrimero();
        int i = 0;

        while (actual != null) {
            assertEquals(i + 1, actual.getEtiqueta());
            assertEquals(i + 1, actual.getDato().getCodProducto());
            assertEquals(expectedNames[i], actual.getDato().getNombre());
            assertEquals(expectedPrices[i], actual.getDato().getPrecio());
            assertEquals(expectedStocks[i], actual.getDato().getStock());
            actual = actual.getSiguiente();
            i++;
        }
    }

    @Test
    void testObtenerValorStock1() {
        insertarProductos();

        assertEquals(59030, almacen.obtenerValorStock());
        assertEquals(5, almacen.cantidadProductos());
    }

    @Test
    void testObtenerValorStock2() {

        almacen.insertarProducto(p1);

        Producto p = new Producto(1, "Manzanas", 20, 36);
        almacen.insertarProducto(p);

        assertEquals(2200, almacen.obtenerValorStock());
        assertEquals(1, almacen.cantidadProductos());
    }

    @Test
    void testObtenerValorStock3() {
        insertarProductos();

        Producto p = new Producto(1, "Manzanas", 20, 36);
        almacen.insertarProducto(p);

        p = new Producto(4, "Aspiradora", 250, 125);
        almacen.insertarProducto(p);

        assertEquals(91000, almacen.obtenerValorStock());
        assertEquals(5, almacen.cantidadProductos());
    }

    @Test
    void testAgregarStockProductoExistente() {
        insertarProductos();

        assertEquals(true, almacen.agregarStock(1, 20));
        assertEquals(94, p1.getStock());
    }

    @Test
    void testAgregarStockProductoInexistente() {
        insertarProductos();

        assertEquals(false, almacen.agregarStock(10, 20));
        assertEquals(5, almacen.cantidadProductos());
    }

    @Test
    void testEliminarProductoExistente() {
        insertarProductos();

        assertEquals(true, almacen.eliminarProducto(3));
        assertEquals(4, almacen.cantidadProductos());

        Nodo<IProducto> actual = almacen.getListaProductos().getPrimero();

        while (actual != null) {
            assertNotEquals(3, actual.getDato().getCodProducto());
            actual = actual.getSiguiente();
        }
    }

    @Test
    void testEliminarProductoInexistente() {
        insertarProductos();

        assertEquals(false, almacen.eliminarProducto(6));
        assertEquals(5, almacen.cantidadProductos());

        Nodo<IProducto> actual = almacen.getListaProductos().getPrimero();

        while (actual != null) {
            assertNotEquals(6, actual.getDato().getCodProducto());
            actual = actual.getSiguiente();
        }
    }

    @Test
    void testEliminarProductoDeListaVacia() {
        assertEquals(false, almacen.eliminarProducto(1));

    }

    @Test
    void testRestarMenosStockDelExistente() {
        insertarProductos();

        assertEquals(40, almacen.restarStock(5, 10));
        assertEquals(40, almacen.buscarPorCodigo(5).getStock());
    }

    @Test
    void testRestarMasStockDelExistente() {
        insertarProductos();

        assertEquals(0, almacen.restarStock(5, 60));
        assertEquals(0, almacen.buscarPorCodigo(5).getStock());
    }

    @Test
    void testRestarStockProductoInexistente() {
        insertarProductos();

        assertThrows(IllegalArgumentException.class,
                () -> almacen.restarStock(6, 10),
                "El producto no está en la lista");
    }

    @Test
    void buscarProductoExistente() {
        insertarProductos();

        IProducto p = almacen.buscarPorCodigo(3);

        assertEquals(3, p.getCodProducto());
        assertEquals("Milanesas", p.getNombre());
        assertEquals(84, p.getPrecio());
        assertEquals(53, p.getStock());
    }

    @Test
    void buscarProductoInexistente() {
        insertarProductos();

        assertThrows(IllegalArgumentException.class,
                () -> almacen.buscarPorCodigo(6),
                "El producto no está en la lista");
    }

    @Test
    void buscarPorDescripcionExistente() {
        insertarProductos();

        IProducto p = almacen.buscarPorDescripcion("Milanesas");

        assertEquals(3, p.getCodProducto());
        assertEquals("Milanesas", p.getNombre());
        assertEquals(84, p.getPrecio());
        assertEquals(53, p.getStock());
    }

    @Test
    void buscarPorDescripcionInexistente() {
        insertarProductos();

        IProducto p = almacen.buscarPorDescripcion("Producto inexistente");

        assertEquals(null, p);
    }

    @Test
    void testListarOrdenadoPorNombre() {

        insertarProductos();

        assertEquals(5, almacen.cantidadProductos());

        String[] expectedNames = { "Manzanas", "Peras", "Milanesas", "Aspiradora", "Computadora" };
        int[] expectedPrices = { 20, 23, 84, 250, 1000 };
        int[] expectedStocks = { 74, 26, 53, 10, 50 };
        Nodo<IProducto> actual = almacen.getListaProductos().getPrimero();
        int i = 0;

        while (actual != null) {
            assertEquals(i + 1, actual.getEtiqueta());
            assertEquals(i + 1, actual.getDato().getCodProducto());
            assertEquals(expectedNames[i], actual.getDato().getNombre());
            assertEquals(expectedPrices[i], actual.getDato().getPrecio());
            assertEquals(expectedStocks[i], actual.getDato().getStock());
            actual = actual.getSiguiente();
            i++;
        }

        almacen.listarOrdenadoPorNombre();

        assertEquals(5, almacen.cantidadProductos());
        actual = almacen.getListaProductos().getPrimero();
        i = 0;

        while (actual != null) {
            assertEquals(i + 1, actual.getEtiqueta());
            assertEquals(i + 1, actual.getDato().getCodProducto());
            assertEquals(expectedNames[i], actual.getDato().getNombre());
            assertEquals(expectedPrices[i], actual.getDato().getPrecio());
            assertEquals(expectedStocks[i], actual.getDato().getStock());
            actual = actual.getSiguiente();
            i++;
        }
    }
}
