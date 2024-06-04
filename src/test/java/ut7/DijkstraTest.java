package ut7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ut7.TDA.*;

import java.util.HashMap;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;

public class DijkstraTest {

    @Test
    void test_dijkstra() {
        TVertice v1 = new TVertice("v1");
        TVertice v2 = new TVertice("v2");
        TVertice v3 = new TVertice("v3");
        TVertice v4 = new TVertice("v4");
        TVertice v5 = new TVertice("v5");
        LinkedList<TVertice> vertices = new LinkedList<>();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);

        TArista a12 = new TArista("v1", "v2", 10);
        TArista a14 = new TArista("v1", "v4", 30);
        TArista a15 = new TArista("v1", "v5", 100);
        TArista a23 = new TArista("v2", "v3", 50);
        TArista a35 = new TArista("v3", "v5", 10);
        TArista a43 = new TArista("v4", "v3", 20);
        TArista a45 = new TArista("v4", "v5", 60);
        LinkedList<TArista> aristas = new LinkedList<>();
        aristas.add(a12);
        aristas.add(a14);
        aristas.add(a15);
        aristas.add(a23);
        aristas.add(a35);
        aristas.add(a43);
        aristas.add(a45);

        TGrafoDirigido g = new TGrafoDirigido(vertices, aristas);

        HashMap<Comparable, Double> djisktra = g.dijkstra("v1");

        assertEquals(0, djisktra.get("v1"));
        assertEquals(10, djisktra.get("v2"));
        assertEquals(50, djisktra.get("v3"));
        assertEquals(30, djisktra.get("v4"));
        assertEquals(60, djisktra.get("v5"));
    }

    @Test
    void test_ut7_ta1_aeropuertos_dijkstra() {
        TVertice art = new TVertice<>("Artigas");
        TVertice can = new TVertice<>("Canelones");
        TVertice col = new TVertice<>("Colonia");
        TVertice dur = new TVertice<>("Durazno");
        TVertice flo = new TVertice<>("Florida");
        TVertice mon = new TVertice<>("Montevideo");
        TVertice pun = new TVertice<>("Punta del Este");
        TVertice roc = new TVertice<>("Rocha");
        LinkedList<TVertice> vertices = new LinkedList<>();
        vertices.add(art);
        vertices.add(can);
        vertices.add(col);
        vertices.add(dur);
        vertices.add(flo);
        vertices.add(mon);
        vertices.add(pun);
        vertices.add(roc);

        TArista art_roc = new TArista("Artigas", "Rocha",400);
        TArista can_art = new TArista("Canelones", "Artigas", 500);
        TArista can_col = new TArista("Canelones", "Colonia", 200);
        TArista art_dur = new TArista("Canelones", "Durazno", 170);
        TArista can_pun = new TArista("Canelones", "Punta del Este", 90);
        TArista col_mon = new TArista("Colonia", "Montevideo", 180);
        TArista flo_dur = new TArista("Florida", "Durazno", 60);
        TArista mon_art = new TArista("Montevideo", "Artigas",700);
        TArista mon_can = new TArista("Montevideo", "Canelones",30);
        TArista mon_pun = new TArista("Montevideo", "Punta del Este", 130);
        TArista pun_roc = new TArista("Punta del Este", "Rocha",90);
        TArista roc_mon = new TArista("Rocha", "Montevideo",270);
        LinkedList<TArista> aristas = new LinkedList<>();
        aristas.add(art_roc);
        aristas.add(can_art);
        aristas.add(can_col);
        aristas.add(art_dur);
        aristas.add(can_pun);
        aristas.add(col_mon);
        aristas.add(flo_dur);
        aristas.add(mon_art);
        aristas.add(mon_can);
        aristas.add(mon_pun);
        aristas.add(pun_roc);
        aristas.add(roc_mon);

        TGrafoDirigido g = new TGrafoDirigido(vertices, aristas);

        HashMap<Comparable, Double> djikstra = g.dijkstra("Montevideo");

        assertEquals(530, djikstra.get("Artigas"));
        assertEquals(30, djikstra.get("Canelones"));
        assertEquals(230, djikstra.get("Colonia"));
        assertEquals(200, djikstra.get("Durazno"));
        assertEquals(Double.MAX_VALUE, djikstra.get("Florida"));
        assertEquals(0, djikstra.get("Montevideo"));
        assertEquals(120, djikstra.get("Punta del Este"));
        assertEquals(210, djikstra.get("Rocha"));
    }
}
