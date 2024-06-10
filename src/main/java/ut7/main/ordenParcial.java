package ut7.main;

import java.io.*;
import java.util.*;
import ut7.TDA.*;

/* Tarea1 debe preceder a Tarea2 y Tarea3.
Tarea2 debe preceder a Tarea3 y Tarea5.
Tarea3 debe preceder a Tarea4.
Tarea5 debe preceder a Tarea6.
Tarea4 y Tarea6 son predecesoras de Fin. */

public class Main {
    public static void main(String[] args) {
        TGrafoDirigido grafo = (TGrafoDirigido) UtilGrafos.cargarGrafo("src\\main\\java\\ut7\\utils\\tareas.txt", "src\\main\\java\\ut7\\utils\\precedencias.txt", false,
                TGrafoDirigido.class);

        LinkedList<String> ordenParcial = grafo.ordenParcial();
        String[] array = new String[ordenParcial.size()];
        for (int i = 0; i < ordenParcial.size(); i++) {
            array[i] = ordenParcial.get(i);
        }
        grafo.listarVerticeOrdenParcial(ordenParcial);

        ManejadorArchivosGenerico.escribirArchivo("orden.txt", array);
    }
}
