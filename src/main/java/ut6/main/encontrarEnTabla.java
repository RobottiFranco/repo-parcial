package ut6.main;

import ut6.TDA.THashLineal;
import ut6.utils.ManejadorArchivosGenerico;

public class encontrarEnTabla {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Crear una tabla de tipo THash e insertar las claves del archivo
        // "claves_insertar.txt"
        String[] inserciones = ManejadorArchivosGenerico
                .leerArchivo("src\\main\\java\\ut6\\utils\\insertar.txt");
        THashLineal tabla = new THashLineal(inserciones.length);
        for (String clave : inserciones) {
            tabla.insertar(Integer.parseInt(clave));
        }

        // Buscar en la tabla creada anteriormente las claves indicadas en el archivo
        // "claves_buscar.txt"
        String[] busquedas = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\ut6\\utils\\claves_buscar.txt");
        for (String clave : busquedas) {
            int intentos = tabla.buscar(Integer.parseInt(clave));
            if (intentos == -1) {
                System.out.println("La clave " + clave + " no se encuentra en la tabla");
            } else {
                System.out.println("La clave " + clave + " se encuentra en la tabla y se encontro tras: " + intentos
                        + " intentos");
            }
        }

    }

}
