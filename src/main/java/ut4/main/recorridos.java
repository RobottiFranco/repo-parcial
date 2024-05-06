package ut4.main;

import ut4.TDA.TArbolBB;
import ut4.TDA.TElementoAB;
import ut4.utils.ManejadorArchivosGenerico;

public class recorridos {
    public static void main(String[] args) {
        TArbolBB<TElementoAB> arbol = new TArbolBB<TElementoAB>();

        String[] archivo = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\ut4\\utils\\claves1.txt");
        TElementoAB elementoAB;
        for (String string : archivo) {
            elementoAB = new TElementoAB<>(Integer.parseInt(string), string);
            arbol.insertar(elementoAB);
        }

        System.out.println("el elemento 4734 pertenece al arbol:");
        System.out.println(arbol.buscar(4734) != null);

        /* ejercicio 1 y 2 */
        archivo = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\ut4\\utils\\clavesPrueba.txt");
        TArbolBB<TElementoAB> arbol2 = new TArbolBB<TElementoAB>();

        for (String string : archivo) {
            elementoAB = new TElementoAB<>(Integer.parseInt(string), string);
            arbol2.insertar(elementoAB);
        }

        System.out.println("preOrden:");
        System.out.println(arbol2.preOrden());

        System.out.println("inorden:");
        System.out.println(arbol2.inOrden());

        System.out.println("postOrden:");
        System.out.println(arbol2.postOrden());

        ManejadorArchivosGenerico.escribirArchivo("inorden.txt", arbol2.inOrden().split(";"));
        ManejadorArchivosGenerico.escribirArchivo("postorden.txt", arbol2.postOrden().split(";"));
        ManejadorArchivosGenerico.escribirArchivo("preorden.txt", arbol2.preOrden().split(";"));

        /* ejercicio 3 */
        archivo = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\ut4\\utils\\claves3.txt");
        TArbolBB<TElementoAB> arbol3 = new TArbolBB<TElementoAB>();

        for (String string : archivo) {
            elementoAB = new TElementoAB<>(Integer.parseInt(string), string);
            arbol3.insertar(elementoAB);
        }

        System.out.println("el elemento 10635 pertenece al arbol:");
        System.out.println(arbol.buscar(10635) != null);

        System.out.println("el elemento 4567 pertenece al arbol:");
        System.out.println(arbol.buscar(4567) != null);

        System.out.println("el elemento 12 pertenece al arbol:");
        System.out.println(arbol.buscar(12) != null);

        System.out.println("el elemento 8978 pertenece al arbol:");
        System.out.println(arbol.buscar(8978) != null);
    }
}
