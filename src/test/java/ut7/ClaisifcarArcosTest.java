package ut7;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ut7.TDA.*;
import java.util.List;


import java.util.LinkedList;

public class ClaisifcarArcosTest {

    @Test
    public void test_clasificar_arcos() {
        List<TVertice> vertices = new LinkedList<>();
        vertices.add(new TVertice("A"));
        vertices.add(new TVertice("B"));
        vertices.add(new TVertice("C"));
        vertices.add(new TVertice("D"));
        vertices.add(new TVertice("E"));
        vertices.add(new TVertice("F"));
        vertices.add(new TVertice("G"));

        List<TArista> aristas = new LinkedList<>();
        aristas.add(new TArista("A", "B", 0));
        aristas.add(new TArista("B", "C", 0));
        aristas.add(new TArista("B", "D", 0));
        aristas.add(new TArista("C", "A", 0));
        aristas.add(new TArista("D", "C", 0));
        aristas.add(new TArista("D", "A", 0));

        aristas.add(new TArista("E", "F", 0));
        aristas.add(new TArista("E", "G", 0));
        aristas.add(new TArista("F", "B", 0));
        aristas.add(new TArista("G", "D", 0));
        aristas.add(new TArista("G", "F", 0));

        TGrafoDirigido gd = new TGrafoDirigido(vertices, aristas);
        List<TArista> arcosArbol = new LinkedList<>();
        List<TArista> arcosAvance = new LinkedList<>();
        List<TArista> arcosRetroceso = new LinkedList<>();
        List<TArista> arcosCruzados = new LinkedList<>();

        gd.clasificarArcos("A", arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados);
        assertEquals(5, arcosArbol.size());
        assertEquals(2, arcosRetroceso.size());
        assertEquals(0, arcosAvance.size());
        assertEquals(4, arcosCruzados.size());
    }
}
