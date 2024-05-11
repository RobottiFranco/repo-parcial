package ut5.main;

import java.util.LinkedList;

import ut4.utils.ManejadorArchivosGenerico;
import ut5.TDA.TArbolTrie;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TArbolTrie trie = new TArbolTrie();

        String[] palabrasclave = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\ut5\\utils\\palabras1.txt");
        for (String p : palabrasclave) {
            trie.insertar(p);
        }
        /*
         * trie.imprimir();
         */

        palabrasclave = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\ut5\\utils\\palabras.txt");
        for (String p : palabrasclave) {
            System.out.println(trie.buscar(p));
        }
        /*
         * trie.imprimir();
         */
    }
}
