package ut7.main;

import ut7.TDA.TGrafoDirigido;
import ut7.TDA.UtilGrafos;

public class floydWarshall {
    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src\\main\\java\\ut7\\utils\\aeropuertos_1.txt",
                "src\\main\\java\\ut7\\utils\\conexiones_1.txt",
                false, TGrafoDirigido.class);

        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");
        Double[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");
        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : "
                    + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }
        System.out.println();
        System.out.println("Centro del grafo: " + gd.centroDelGrafo());

        boolean[][] matrizWarshall = gd.warshall();
        UtilGrafos.imprimirMatrizMejorado(matrizWarshall, gd.getVertices(), "Matriz luego de warshall", true);
    }
}
