package ut7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ut7.TDA.*;


public class BpfTest {

    public TGrafoDirigido g;

    public void setUp() {
        List<TVertice> vertices = new LinkedList<>();
        vertices.add(new TVertice<>("A"));
        vertices.add(new TVertice<>("B"));
        vertices.add(new TVertice<>("C"));
        vertices.add(new TVertice<>("D"));
        vertices.add(new TVertice<>("E"));
        vertices.add(new TVertice<>("F"));
        vertices.add(new TVertice<>("G"));

        List<TArista> aristas = new LinkedList<>();
        aristas.add(new TArista("A", "B", 1));

        aristas.add(new TArista("B", "C", 1));
        aristas.add(new TArista("B", "D", 1));

        aristas.add(new TArista("C", "A", 1));

        aristas.add(new TArista("D", "A", 1));
        aristas.add(new TArista("D", "C", 1));

        aristas.add(new TArista("E", "F", 1));
        aristas.add(new TArista("E", "G", 1));

        aristas.add(new TArista("F", "B", 1));

        aristas.add(new TArista("G", "D", 1));
        aristas.add(new TArista("G", "F", 1));

        g = new TGrafoDirigido(vertices, aristas);
    }

    @Test
    public void bpf_de_todos_los_vertices_con_dos_componentes_conexos() {
        setUp();
        List<TVertice> bpf = (ArrayList) g.bpf();

        assertEquals("A", bpf.get(0).getEtiqueta());
        assertEquals("B", bpf.get(1).getEtiqueta());
        assertEquals("C", bpf.get(2).getEtiqueta());
        assertEquals("D", bpf.get(3).getEtiqueta());
        assertEquals("E", bpf.get(4).getEtiqueta());
        assertEquals("F", bpf.get(5).getEtiqueta());
        assertEquals("G", bpf.get(6).getEtiqueta());
        assertEquals(7, bpf.size());
    }

    @Test
    public void bpf_desde_etiqueta_existente_en_un_componente_conexo() {
        setUp();
        List<TVertice> bpf = (LinkedList) g.bpf("A");

        assertEquals("A", bpf.get(0).getEtiqueta());
        assertEquals("B", bpf.get(1).getEtiqueta());
        assertEquals("C", bpf.get(2).getEtiqueta());
        assertEquals("D", bpf.get(3).getEtiqueta());
        assertEquals(4, bpf.size());
    }

    @Test
    public void bpf_desde_etiqueta_inexistente() {
        setUp();
        List<TVertice> bpf = (LinkedList) g.bpf("Z");
        assertEquals(null, bpf);
    }
}
