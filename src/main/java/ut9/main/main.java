package ut9.main;

import ut9.tda.TClasificador;
import ut9.utils.GeneradorDatosGenericos;

public class main {
    public static void main(String[] args) {
        int[] vectorAleatorio = GeneradorDatosGenericos.generarDatosAleatorios();
        int[] vectorAscendente = GeneradorDatosGenericos.generarDatosAscendentes();
        int[] vectorDescendente = GeneradorDatosGenericos.generarDatosDescendentes();

        for (int i = 1; i <= 4; i++) { // Hace cada una de las ordenaciones
            TClasificador c = new TClasificador();
            int[] ordenadoAleatorio = c.clasificar(vectorAleatorio, i);
            int[] ordeandoAscendente = c.clasificar(vectorAscendente, i);
            int[] ordeandoDescendente = c.clasificar(vectorDescendente, i);
            System.out.println(i);
            c.imprimirVector(ordenadoAleatorio);
            System.out.println();
            c.imprimirVector(ordeandoAscendente);
            System.out.println();
            c.imprimirVector(ordeandoDescendente);
            System.out.println();
            System.out.println();
        }


    }
}
