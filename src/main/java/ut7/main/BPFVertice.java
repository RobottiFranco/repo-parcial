package ut7.main;

import java.util.Collection;

import ut7.TDA.*;

public class BPFVertice {
    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src\\main\\java\\ut7\\utils\\aeropuertos_1.txt",
                "src\\main\\java\\ut7\\utils\\conexiones_1.txt",
                false, TGrafoDirigido.class);

        Collection<TVertice> recorrido = gd.bpfVertices();
/*         System.out.println(recorrido);
 */
        for (TVertice tVertice : recorrido) {
            System.out.println(tVertice.getEtiqueta());
        }


        System.out.println("");
        // imprimir etiquetas del bpf de todo el grafo....
        Collection<TVertice> recorrido_Asuncion = gd.bpfVertices("Asuncion");
/*         System.out.println(recorrido);
 */
        for (TVertice tVertice : recorrido_Asuncion) {
            System.out.println(tVertice.getEtiqueta());
        }
        // imprimir etiquetas del bpf desde Asunción....

    }
}
