package ut8.main;

import java.util.Collection;
import java.util.LinkedList;

import ut8.tda.TCaminos;
import ut8.tda.TGrafoNoDirigido;
import ut8.tda.TVertice;
import ut8.utils.UtilGrafos;

public class Main {
    public static void main(String[] args) {
        //EJERCICIO 1 ------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //CARGAR GRAFO
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo("verticesBEA.txt", "aristasBEA.txt",
                false, TGrafoNoDirigido.class);

        //MATRIZ DE ADYACENCIAS
        Double[][] matrizAdyacencias = UtilGrafos.obtenerMatrizCostos(gnd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matrizAdyacencias, gnd.getVertices(), "Matriz Adyacencias");

        //BPF DEL GRAFO
        System.out.println("********************Búsqueda en Profundidad********************");
        Collection<TVertice> recorrido = gnd.bpf();
        System.out.print("bpf del grafo: ");
        for (TVertice v : recorrido){
            System.out.print(v.getEtiqueta() + " ");
        }
        System.out.println("");
        System.out.println("***************************************************************");

        //TODOS LOS CAMINOS
        System.out.println("********************Caminos desde 'a' a 'd'********************");
        TCaminos caminos = gnd.todosLosCaminos("a", "d");
        caminos.imprimirCaminosConsola();
        System.out.println("***************************************************************");

        //MIRAR LA IMAGEN grafoSPD1 a la misma altura que la carpeta PD1 PARA LA VERIFICACIÓN MANUAL
        //CARGAR GRAFO
        TGrafoNoDirigido gndej1g1 = (TGrafoNoDirigido) UtilGrafos.cargarGrafo("ej1p5g1_vertices.txt","ej1p5g1_aristas.txt",
                false, TGrafoNoDirigido.class);

        //BPF GRAFO EJ1 G1
        System.out.println("********************Búsqueda en Profundidad********************");
        Collection<TVertice> recorrido1 = gndej1g1.bpf();
        for (TVertice v : recorrido1){
            System.out.print(v.getEtiqueta() + " ");
        }
        System.out.println("");
        System.out.println("***************************************************************");

        //CARGAR GRAFO
        TGrafoNoDirigido gndej1g2 = (TGrafoNoDirigido) UtilGrafos.cargarGrafo("ej1p5g2_vertices.txt","ej1p5g2_aristas.txt",
                false, TGrafoNoDirigido.class);

        //BPF GRAFO EJ1 G2
        System.out.println("********************Búsqueda en Profundidad********************");
        Collection<TVertice> recorrido2 = gndej1g2.bpf();
        System.out.print("bpf del grafo: ");
        for (TVertice v : recorrido2){
            System.out.print(v.getEtiqueta() + " ");
        }
        System.out.println("");
        System.out.println("***************************************************************");

        //EJERCICIO 2 ------------------------------------------------------------------------------------------------------------------------------------------------------------------
        TGrafoNoDirigido prim1 = gndej1g1.Prim();
        //AAM
        Double[][] matrizAdyacenciasPrim1 = UtilGrafos.obtenerMatrizCostos(prim1.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matrizAdyacenciasPrim1, prim1.getVertices(), "AAM Prim");

        TGrafoNoDirigido prim2 = gndej1g2.Prim();
        //AAM
        Double[][] matrizAdyacenciasPrim2 = UtilGrafos.obtenerMatrizCostos(prim2.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matrizAdyacenciasPrim2, prim2.getVertices(), "AAM Prim");

        //ARROJA LOS RESULTADOS ESPERADOS, LOS GRAFOS SON LOS MISMOS USADOS EN EL EJERCICIO 1, mirar la imagen grafosPD1

        //EJERCICIO 3 ------------------------------------------------------------------------------------------------------------------------------------------------------------------
        TGrafoNoDirigido gndej3 = (TGrafoNoDirigido) UtilGrafos.cargarGrafo("ej3_vertices.txt", "ej3_aristas.txt",
                false, TGrafoNoDirigido.class);

        //BEA DEL GRAFO
        System.out.println("********************Búsqueda en Amplitud********************");
        Collection<TVertice> bea = gndej3.bea("a");
        for (TVertice v : bea){
            System.out.print(v.getEtiqueta() + " ");
        }
        System.out.println("");
        System.out.println("***************************************************************");

        //ARROJA LOS RESULTADOS ESPERADOS, SE PUEDE VERIFICAR EN LA IMAGEN grafoBEAPD1 que se encuentra a la misma altura que la carpeta PD1
    }
}
