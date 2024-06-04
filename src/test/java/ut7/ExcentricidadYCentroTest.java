package ut7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ut7.TDA.*;

import java.util.LinkedList;
import java.util.List;


public class ExcentricidadYCentroTest {

    public TGrafoDirigido g;

    public void setUp() {
        List<TVertice> vertices = new LinkedList<>();
        vertices.add(new TVertice<>("A"));
        vertices.add(new TVertice<>("B"));
        vertices.add(new TVertice<>("C"));
        vertices.add(new TVertice<>("D"));
        vertices.add(new TVertice<>("E"));

        List<TArista> aristas = new LinkedList<>();
        aristas.add(new TArista("A", "B", 1));

        aristas.add(new TArista("B", "C", 2));

        aristas.add(new TArista("C", "D", 2));
        aristas.add(new TArista("C", "E", 4));

        aristas.add(new TArista("D", "B", 1));
        aristas.add(new TArista("D", "C", 3));

        aristas.add(new TArista("E", "D", 5));

        g = new TGrafoDirigido(vertices, aristas);
    }

    @Test
    public void test_excentricidad_de_cada_vertice() {
        setUp();
        assertEquals(Double.MAX_VALUE, g.obtenerExcentricidad("A"));
        assertEquals(6.0, g.obtenerExcentricidad("B"));
        assertEquals(8.0, g.obtenerExcentricidad("C"));
        assertEquals(5.0, g.obtenerExcentricidad("D"));
        assertEquals(7.0, g.obtenerExcentricidad("E"));
    }

    @Test
    public void test_centro_del_grafo() {
        setUp();
        assertEquals("D", g.centroDelGrafo());
    }

}
