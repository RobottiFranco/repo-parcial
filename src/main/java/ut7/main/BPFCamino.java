package ut7.main;

import java.util.Collection;

import ut7.TDA.TCamino;
import ut7.TDA.TCaminos;
import ut7.TDA.TGrafoDirigido;
import ut7.TDA.UtilGrafos;

public class BPFCamino {
    public static void main(String[] args) {
        /* ej1 */
        TGrafoDirigido VUELE_SEGURO = (TGrafoDirigido) UtilGrafos.cargarGrafo(
                "src\\main\\java\\ut7\\utils\\aeropuertos.txt", "src\\main\\java\\ut7\\utils\\conexiones.txt",
                false, TGrafoDirigido.class);
        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(VUELE_SEGURO.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, VUELE_SEGURO.getVertices(), "Matriz vuelos");

        Double[][] floyd = VUELE_SEGURO.floyd();
        UtilGrafos.imprimirMatrizMejorado(floyd, VUELE_SEGURO.getVertices(), "Matriz Floyd");

        /* ej2 */
        TCaminos caminoMontevideoCuritiva = VUELE_SEGURO.todosLosCaminos("Montevideo", "Curitiba");
        TCaminos caminoPorto_AlegreSantos = VUELE_SEGURO.todosLosCaminos("Porto_Alegre", "Santos");

        caminoMontevideoCuritiva.imprimirCaminosConsola();
        caminoPorto_AlegreSantos.imprimirCaminosConsola();

        /* ej3 */

        System.out.println("\nbpf desde origen:\n");
        Collection<Comparable> bpf = VUELE_SEGURO.bpf();
        for (Comparable tVertice : bpf) {
            System.out.println(tVertice);
        }

        System.out.println("\nbpf desde etiqueta:\n");
        bpf = VUELE_SEGURO.bpf("Montevideo");
        for (Comparable tVertice : bpf) {
            System.out.println(tVertice);
        }

        TCamino x = caminoPorto_AlegreSantos.getCaminoCritico();
        System.out.println(x.getCostoTotal());
        x.imprimirEtiquetasConsola();
    }
}
