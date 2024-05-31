package ut6.main;

import ut6.TDA.THashLineal;
import ut6.utils.ManejadorArchivosGenerico;

public class Main {
    public static void main(String[] args) {
        // Crear una tabla de tipo THash e insertar las claves del archivo "claves_insertar.txt"
        String[] file = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\ut6\\utils\\claves_insertar.txt");
        THashLineal hash = new THashLineal(file.length + 250);
        int[] insertar = new int[file.length];
        int i = 0;
        for (String string : file) {
            insertar[i] = hash.insertar(Integer.parseInt(string));
            i++;
        }

        // Escribir los resultados en el archivo "promedioInsertar.txt"
        String[] resultados = new String[insertar.length + 1];
        int prom = 0;
        for (int j = 0; j < insertar.length; j++) {
            resultados[j] = String.valueOf(insertar[j]);
            prom += insertar[j];
        }
        resultados[insertar.length] = "promedio: " + (prom / insertar.length);

        ManejadorArchivosGenerico.escribirArchivo("promedioInsertar.txt", resultados);

        // Buscar en la tabla creada anteriormente las claves indicadas en el archivo "claves_buscar.txt"
        file = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\ut6\\utils\\claves_buscar.txt");
        int[] buscar = new int[file.length];
        i = 0;
        for (String string : file) {
            buscar[i] = hash.buscar(Integer.parseInt(string));
            i++;
        }

        // Escribir los resultados en el archivo "promedioBuscar.txt"
        resultados = new String[buscar.length + 2];
        int promExitos = 0;
        int promFracasos = 0;
        int numeroExitos = 0;

        for (int j = 0; j < buscar.length; j++) {
            resultados[j] = String.valueOf(buscar[j]);
            if (!resultados[j].equals("-1")) {
                promExitos += buscar[j];
                numeroExitos++;
            } else {
                promFracasos++;
            }
        }
        resultados[buscar.length] = "promedioExitos: " + (promExitos / numeroExitos);
        resultados[buscar.length + 1] = "Fracasos: " + (promFracasos);

        ManejadorArchivosGenerico.escribirArchivo("promedioBuscar.txt", resultados);
    }
}
