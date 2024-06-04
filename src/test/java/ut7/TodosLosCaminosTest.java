package ut7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ut7.TDA.*;
import java.util.List;
import java.util.LinkedList;


public class TodosLosCaminosTest {

    @Test
    public void test_todos_los_caminos() {

        List<TVertice> vertices = new LinkedList<>();
        vertices.add(new TVertice<>("A"));
        vertices.add(new TVertice<>("B"));
        vertices.add(new TVertice<>("C"));
        vertices.add(new TVertice<>("D"));

        List<TArista> aristas = new LinkedList<>();
        aristas.add(new TArista("A", "B", 1));
        aristas.add(new TArista("A", "C", 1));

        aristas.add(new TArista("B", "A", 1));
        aristas.add(new TArista("B", "C", 1));
        aristas.add(new TArista("B", "D", 1));

        aristas.add(new TArista("C", "B", 1));
        aristas.add(new TArista("C", "D", 1));

        TGrafoDirigido gd = new TGrafoDirigido(vertices, aristas);

        TCaminos AtoD = gd.todosLosCaminos("A", "D");
        List<TCamino> caminos = (LinkedList) AtoD.getCaminos();
        assertEquals(4, caminos.size());

        TCamino camino1 = caminos.get(0);

        TCamino camino2 = caminos.get(1);

        TCamino camino3 = caminos.get(2);

        TCamino camino4 = caminos.get(3);

        // Assert camino1
        assertEquals("A", camino1.getOrigen().getEtiqueta());
        assertEquals("B", ((LinkedList) camino1.getOtrosVertices()).get(0));
        assertEquals("C", ((LinkedList) camino1.getOtrosVertices()).get(1));
        assertEquals("D", ((LinkedList) camino1.getOtrosVertices()).get(2));

        // Assert camino2
        assertEquals("A", camino2.getOrigen().getEtiqueta());
        assertEquals("B", ((LinkedList) camino2.getOtrosVertices()).get(0));
        assertEquals("D", ((LinkedList) camino2.getOtrosVertices()).get(1));

        // Assert camino3
        assertEquals("A", camino3.getOrigen().getEtiqueta());
        assertEquals("C", ((LinkedList) camino3.getOtrosVertices()).get(0));
        assertEquals("B", ((LinkedList) camino3.getOtrosVertices()).get(1));
        assertEquals("D", ((LinkedList) camino3.getOtrosVertices()).get(2));

        // Assert camino4
        assertEquals("A", camino4.getOrigen().getEtiqueta());
        assertEquals("C", ((LinkedList) camino4.getOtrosVertices()).get(0));
        assertEquals("D", ((LinkedList) camino4.getOtrosVertices()).get(1));

    }
}
