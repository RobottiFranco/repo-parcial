package ut3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ut3.TDA.Lista;
import ut3.TDA.Nodo;
import ut3.TDA.Pila;
import ut3.utils.ManejadorArchivosGenerico;
public class ControlCorchetesTest {

    Pila<Character> pila;
    Lista<Character> listaCaracteres;
    Nodo<Character> nodo;
    char c;

    @BeforeEach
    void setUp() {
        pila = new Pila<>();
        listaCaracteres = new Lista<>();
    }

    @Test
    void testControlCorchetes1() {

        String input = "{{}}";

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            nodo = new Nodo<Character>(c, Character.valueOf(c));
            listaCaracteres.insertar(nodo);
        }

        assertEquals(true, pila.controlCorchetes(listaCaracteres));
    }

    @Test
    void testControlCorchetes2() {

        String input = "{{}}}";

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            nodo = new Nodo<Character>(c, c);
            listaCaracteres.insertar(nodo);
        }

        assertEquals(false, pila.controlCorchetes(listaCaracteres));
    }

    @Test
    void testControlCorchetes3() {

        String input = "{{{}}";

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            nodo = new Nodo<Character>(c, Character.valueOf(c));
            listaCaracteres.insertar(nodo);
        }

        assertEquals(false, pila.controlCorchetes(listaCaracteres));
    }

    @Test
    void testControlCorchetes4() {

        String input = " {{ }} ";

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            nodo = new Nodo<Character>(c, Character.valueOf(c));
            listaCaracteres.insertar(nodo);
        }

        assertEquals(true, pila.controlCorchetes(listaCaracteres));
    }

    @Test
    void testControlCorchetes5() {

        String input = " {{ aaa }} ";

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            nodo = new Nodo<Character>(c, Character.valueOf(c));
            listaCaracteres.insertar(nodo);
        }

        assertEquals(true, pila.controlCorchetes(listaCaracteres));
    }

    @Test
    void testControlCorchetesArchivoEntrada() {

        String[] entrada = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\uy\\edu\\ucu\\aed\\entradas.txt");
        char c;
        int numeroLinea = 1;

        for (String linea : entrada) {

            for (int i = 0; i < linea.length(); i++) {
                c = linea.charAt(i);
                listaCaracteres.insertar(c, c);
            }

            switch (numeroLinea) {
                case 1: case 5: case 7: case 8: case 9:
                    assertEquals(true, pila.controlCorchetes(listaCaracteres));
                    break;
                case 2: case 3: case 4: case 6: case 10:
                    assertEquals(false, pila.controlCorchetes(listaCaracteres));
                    break;
            }
            numeroLinea++;
            setUp();
        }
    }
}
