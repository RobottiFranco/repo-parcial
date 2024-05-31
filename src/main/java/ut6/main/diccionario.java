package ut6.main;

import java.util.HashMap;
import java.util.Map;

import ut6.utils.ManejadorArchivosGenerico;

public final class diccionario {

    public static void main(String[] args) {

        HashMap<String, Integer> hashMap = new HashMap<>();
        String[] file = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\ut6\\utils\\libro.txt");
        String[] separador;
        for (String string : file) {
            separador = string.split(" ");
            for (String string2 : separador) {
                // Eliminar signos de puntuación
                string2 = string2.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
                if (hashMap.containsKey(string2)) {
                    hashMap.put(string2, hashMap.get(string2) + 1);
                } else {
                    hashMap.put(string2, 1);
                }
            }
        }
        hashMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
