package ut7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import ut7.TDA.*;

public class FloydTest {

    @Test
    void test_Floyd_todo_conectado() {
        LinkedList<TVertice> vertices = new LinkedList<>();
        vertices.add(new TVertice<>(1));
        vertices.add(new TVertice<>(2));
        vertices.add(new TVertice<>(3));

        LinkedList<TArista> aristas = new LinkedList<>();
        aristas.add(new TArista(1, 1, 2));
        aristas.add(new TArista(1, 2, 8));
        aristas.add(new TArista(1, 3, 5));
        aristas.add(new TArista(2, 1, 3));
        aristas.add(new TArista(3, 2, 2));

        TGrafoDirigido g = new TGrafoDirigido(vertices, aristas);
        Double[][] floyd = g.floyd();

        assertEquals(0.0, floyd[0][0]);
        assertEquals(7.0, floyd[0][1]);
        assertEquals(5.0, floyd[0][2]);

        assertEquals(3.0, floyd[1][0]);
        assertEquals(0.0, floyd[1][1]);
        assertEquals(8.0, floyd[1][2]);

        assertEquals(5.0, floyd[2][0]);
        assertEquals(2.0, floyd[2][1]);
        assertEquals(0.0, floyd[2][2]);
    }

    @Test
    void test_Floyd_con_conexiones_parciales() {
        LinkedList<TVertice> vertices = new LinkedList<>();
        vertices.add(new TVertice<>(1));
        vertices.add(new TVertice<>(2));
        vertices.add(new TVertice<>(3));
        vertices.add(new TVertice<>(4));

        LinkedList<TArista> aristas = new LinkedList<>();
        aristas.add(new TArista(1, 2, 2));
        aristas.add(new TArista(1, 3, 6));
        aristas.add(new TArista(2, 3, 2));

        TGrafoDirigido g = new TGrafoDirigido(vertices, aristas);
        Double[][] floyd = g.floyd();

        assertEquals(0.0, floyd[0][0]);
        assertEquals(2.0, floyd[0][1]);
        assertEquals(4.0, floyd[0][2]);
        assertEquals(Double.MAX_VALUE, floyd[0][3]);

        assertEquals(Double.MAX_VALUE, floyd[1][0]);
        assertEquals(0.0, floyd[1][1]);
        assertEquals(2.0, floyd[1][2]);
        assertEquals(Double.MAX_VALUE, floyd[1][3]);

        assertEquals(Double.MAX_VALUE, floyd[2][0]);
        assertEquals(Double.MAX_VALUE, floyd[2][1]);
        assertEquals(0.0, floyd[2][2]);
        assertEquals(Double.MAX_VALUE, floyd[2][3]);

        assertEquals(Double.MAX_VALUE, floyd[3][0]);
        assertEquals(Double.MAX_VALUE, floyd[3][1]);
        assertEquals(Double.MAX_VALUE, floyd[3][2]);
        assertEquals(0.0, floyd[3][3]);
    }

    @Test
    void test_Floyd_sin_conexiones() {
        LinkedList<TVertice> vertices = new LinkedList<>();
        vertices.add(new TVertice<>(1));
        vertices.add(new TVertice<>(2));
        vertices.add(new TVertice<>(3));

        LinkedList<TArista> aristas = new LinkedList<>();

        TGrafoDirigido g = new TGrafoDirigido(vertices, aristas);
        Double[][] floyd = g.floyd();

        for (int i = 0; i < floyd.length; i++) {
            for (int j = 0; j < floyd.length; j++) {
                if (i == j) {
                    assertEquals(0.0, Double.valueOf(floyd[i][j]));
                } else {
                    assertEquals(Double.MAX_VALUE, floyd[i][j]);
                }
            }
        }
    }

}
