package ut7.main;

import static java.lang.System.in;
import static java.lang.System.out;

import java.util.Collection;

import ut7.TDA.TCamino;
import ut7.TDA.TCaminos;
import ut7.TDA.TGrafoDirigido;
import ut7.TDA.UtilGrafos;

public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src\\main\\java\\ut7\\utils\\aeropuertos_2.txt",
                "src\\main\\java\\ut7\\utils\\conexiones_2.txt",
                false, TGrafoDirigido.class);

        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        gd.desvisitarVertices();

        if (gd.tieneCiclo()) {
            System.out.println("tiene ciclos");
        } else {
            System.out.println("no tiene ciclos");
        }

        //// calcular todos los caminos, buscar el camino crítico, etc etc
        TCaminos florASantos = gd.todosLosCaminos("Florianopolis", "Santos");
        System.out.println("Caminos de Florianopolis a Santos");
        florASantos.imprimirCaminosConsola();

        TCamino caminoCritico = florASantos.getCaminoCritico();
        System.out.println("Camino critico");
        caminoCritico.imprimirEtiquetasConsola();
        System.out.print("Costo total: ");
        System.out.println(caminoCritico.getCostoTotal());
        System.out.println();

        System.out.println("Holguras");
        florASantos.listarHolgurasConsola();
    }
}
