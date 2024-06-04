package ut7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ut7.TDA.*;

public class WarshallTest {
        @Test
    void test_Warshall_todo_conectado() {
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
        boolean[][] warshall = g.warshall();
        for (int i = 0; i < warshall.length; i++) {
            for (int j = 0; j < warshall.length; j++) {
                if (i == j) {
                    assertEquals(false, warshall[i][j]);
                } else {
                    assertEquals(true, warshall[i][j]);
                }
            }
        }
    }

    @Test
    void test_Warshall_con_conexiones_parciales() {
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
        boolean[][] warshall = g.warshall();

        assertEquals(false, warshall[0][0]);
        assertEquals(true, warshall[0][1]);
        assertEquals(true, warshall[0][2]);
        assertEquals(false, warshall[0][3]);

        assertEquals(false, warshall[1][0]);
        assertEquals(false, warshall[1][1]);
        assertEquals(true, warshall[1][2]);
        assertEquals(false, warshall[1][3]);

        assertEquals(false, warshall[2][0]);
        assertEquals(false, warshall[2][1]);
        assertEquals(false, warshall[2][2]);
        assertEquals(false, warshall[2][3]);

        assertEquals(false, warshall[3][0]);
        assertEquals(false, warshall[3][1]);
        assertEquals(false, warshall[3][2]);
        assertEquals(false, warshall[3][3]);
    }

    @Test
    void test_Warshall_sin_conexiones() {
        LinkedList<TVertice> vertices = new LinkedList<>();
        vertices.add(new TVertice<>(1));
        vertices.add(new TVertice<>(2));
        vertices.add(new TVertice<>(3));
        vertices.add(new TVertice<>(4));

        LinkedList<TArista> aristas = new LinkedList<>();

        TGrafoDirigido g = new TGrafoDirigido(vertices, aristas);
        boolean[][] warshall = g.warshall();

        for (int i = 0; i < warshall.length; i++) {
            for (int j = 0; j < warshall.length; j++) {
                assertEquals(false, warshall[i][j]);
            }
        }
    }
}
