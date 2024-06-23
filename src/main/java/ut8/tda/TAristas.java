package ut8.tda;

import java.util.Collection;

import java.util.LinkedList;

import javax.crypto.interfaces.DHPublicKey;

public class TAristas extends LinkedList<TArista> {

    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = "-";
    //private Collection<TArista> aristas  = new LinkedList<TArista>();

    /**
     * Busca dentro de la lista de aristas una arista que conecte a etOrigen con
     * etDestino
     *
     * @param etOrigen
     * @param etDestino
     * @return
     */
    public TArista buscar(Comparable etOrigen, Comparable etDestino) {
        TArista tempArista;
        for (TArista laa : this) {
            if ((laa.getEtiquetaOrigen().equals(etOrigen)) && laa.getEtiquetaDestino().equals(etDestino)) {
                return laa;
            }
        }

        return null;
    }

    /**
     * Busca la arista de menor costo que conecte a cualquier nodo de VerticesU
     * con cualquier otro de VerticesV y cuyo costo sea el minimo
     *
     * @param VerticesU - Lista de vertices U
     * @param VerticesV - Lista de vertices V
     * @return
     */
    public TArista buscarMin(Collection<Comparable> VerticesU, Collection<Comparable> VerticesV) {
        TArista tempArista;
        TArista tAMin = null;
        Double costoMin = Double.POSITIVE_INFINITY;

        for (Comparable u : VerticesU) {
            for (Comparable v : VerticesV) {
                tempArista = buscar(u, v);
                if (tempArista != null) {
                    if (tempArista.getCosto() < costoMin) {
                        costoMin = tempArista.getCosto();
                        tAMin = tempArista;
                    }
                }
            }
        }
        //---------COMPLETAR ALGORITMO--------
        // para todo u en Vertices U
        // para todo v en Vertices V
        // tA =buscar (u, v)
        // si tA <> null y tA.costo < costoMin entonces
        // tAMin = tA y costoMin = tA.costo
        // fin para todo v
        // fin para todo u
        // devolver tAMin
        return tAMin;
    }

    public String imprimirEtiquetas() {
        if (this.isEmpty()) {
            return null;
        }
        StringBuilder salida = new StringBuilder();
        //TODO: Completar codigo que imprime todas las aristas de la lista en el siguiente formato:
        //ORIGEN - DESTINO - COSTO
        return salida.toString();
    }

    void insertarAmbosSentidos(Collection<TArista> aristas) {
        TArista tempArista;
        for (TArista ta : aristas) {
            this.add(ta);
            this.add(ta.aristaInversa());
        }
    }

    public TArista buscarAristaMinima() {
        double min = Double.MAX_VALUE;
        TArista result = null;
        for (TArista a : this) {
            if (a.getCosto() < min) {
                min = a.getCosto();
                result = a;
            }
        }
        return result;
    }

    public double getCostoTotal() {
        double result = 0.0;
        for (TArista a : this) {
            result += a.getCosto();
        }
        return result/2.0;
    }

}
