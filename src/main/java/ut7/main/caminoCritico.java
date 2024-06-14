package ut7.main;

import ut7.TDA.TCamino;
import ut7.TDA.TCaminos;
import ut7.TDA.TGrafoDirigido;
import ut7.TDA.UtilGrafos;

public class caminoCritico {
    public static void main(String[] args) {
        TGrafoDirigido VUELE_SEGURO = (TGrafoDirigido) UtilGrafos.cargarGrafo(
                "src\\main\\java\\ut7\\utils\\aeropuertos.txt", "src\\main\\java\\ut7\\utils\\conexiones.txt",
                false, TGrafoDirigido.class);
        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(VUELE_SEGURO.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, VUELE_SEGURO.getVertices(), "Matriz vuelos");

        Double[][] floyd = VUELE_SEGURO.floyd();
        UtilGrafos.imprimirMatrizMejorado(floyd, VUELE_SEGURO.getVertices(), "Matriz Floyd");

        TCaminos caminoMontevideoCuritiva = VUELE_SEGURO.todosLosCaminos("Montevideo", "Curitiba");
        TCaminos caminoPorto_AlegreSantos = VUELE_SEGURO.todosLosCaminos("Porto_Alegre", "Santos");

        TCamino x = caminoPorto_AlegreSantos.getCaminoCritico();
        x.imprimirEtiquetasConsola();
        System.out.println(x.getCostoTotal());

        x = caminoMontevideoCuritiva.getCaminoCritico();
        x.imprimirEtiquetasConsola();
        System.out.println(x.getCostoTotal());
    }

}
