package ut5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ut5.TDA.TArbolTrie;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TArbolTrieTest_JUnit5 {

    private TArbolTrie trie;

    @BeforeEach
    void inicializar() {
        trie = new TArbolTrie();
    }

    // Casos de prueba para el método insertar
    @Test
    void dadoTrieVacio_cuandoInsertarPalabra_entoncesPalabraEsAgregada() {
        // Dado
        final String palabra = "test";

        // Cuando
        trie.insertar(palabra);

        // Entonces
        int comparaciones = trie.buscar(palabra);
        assertTrue(comparaciones > 0, "La palabra debe estar presente en el trie.");
    }

    @Test
    void dadoTrieConPalabras_cuandoInsertarPalabraExistente_entoncesLaPalabraNoSeDuplica() {
        // Dado
        final String palabra = "test";
        trie.insertar(palabra);

        // Cuando
        trie.insertar(palabra);

        // Entonces
        List<String> palabrasPredichas = trie.predecir(palabra.substring(0, 1));
        long conteoPalabras = palabrasPredichas.stream().filter(p -> p.equals(palabra)).count();
        assertEquals(1, conteoPalabras, "La palabra no debe duplicarse en el trie.");
    }

    // Casos de prueba para el método buscar
    @Test
    void dadoTrieConPalabras_cuandoBuscarPalabraExistente_entoncesDevuelveComparacionesPositivas() {
        // Dado
        final String palabra = "test";
        trie.insertar(palabra);

        // Cuando
        int comparaciones = trie.buscar(palabra);

        // Entonces
        assertTrue(comparaciones > 0, "Debe devolver un número de comparaciones mayor a cero para una palabra existente.");
    }

    @Test
    void dadoTrieConPalabras_cuandoBuscarPalabraNoExistente_entoncesDevuelveUno() {
        // Dado
        final String palabraExistente = "test";
        final String palabraNoExistente = "noexiste";
        trie.insertar(palabraExistente);

        // Cuando
        int comparaciones = trie.buscar(palabraNoExistente);

        // Entonces
        assertEquals(1, comparaciones, "Debe devolver cero comparaciones para una palabra no existente.");
    }

    // Casos de prueba para el método predecir
    @Test
    void dadoTrieConPalabras_cuandoPredecirConPrefijoExistente_entoncesDevuelvePalabrasConEsePrefijo() {
        // Dado
        final String[] palabras = {"test", "temp", "trie", "ejemplo"};
        for (String palabra : palabras) {
            trie.insertar(palabra);
        }
        final String prefijo = "te";

        // Cuando
        List<String> palabrasConPrefijo = trie.predecir(prefijo);

        // Entonces
        assertTrue(palabrasConPrefijo.contains("test"), "Debe incluir palabras con el prefijo dado.");
        assertTrue(palabrasConPrefijo.contains("temp"), "Debe incluir palabras con el prefijo dado.");
        assertFalse(palabrasConPrefijo.contains("trie"), "No debe incluir palabras sin el prefijo dado.");
    }

    @Test
    void dadoTrieVacio_cuandoPredecirConCualquierPrefijo_entoncesDevuelveListaVacia() {
        // Dado
        final String prefijo = "cualquier";

        // Cuando
        List<String> palabrasConPrefijo = trie.predecir(prefijo);

        // Entonces
        assertTrue(palabrasConPrefijo.isEmpty(), "Debe devolver una lista vacía si el trie está vacío.");
    }
}
