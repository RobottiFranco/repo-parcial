package ut8.tda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {
    protected TAristas lasAristas = new TAristas();

    /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);

    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Prim() {
        List<TArista> TListaArista = new ArrayList<TArista>();
        List<TVertice> UListaVertice = new ArrayList<TVertice>();

        List<TVertice> VListaVertice = new ArrayList<TVertice>(this.getVertices().values());

        TVertice primero = VListaVertice.get(0);
        UListaVertice.add(primero);
        VListaVertice.remove(primero);

        while (VListaVertice.size() > 0) {

            TArista newArista = encontrarArista(UListaVertice, VListaVertice);

            if (newArista != null) {
                TListaArista.add(newArista);
                UListaVertice.add(this.getVertices().get(newArista.etiquetaDestino));
                VListaVertice.remove(this.getVertices().get(newArista.etiquetaDestino));
            }
        }

        TGrafoNoDirigido AAM = new TGrafoNoDirigido(UListaVertice, TListaArista);
        return AAM;
    }

    /**
     * Encuentra la arista de costo mínimo entre los dos conjuntos de vertices
     */
    private TArista encontrarArista(List<TVertice> uListaVertice, List<TVertice> vListaVertice) {

        double costoMin = Double.MAX_VALUE;
        TArista arista = new TArista(null, null, costoMin);

        for (TVertice tVerticeU : uListaVertice) {
            for (TVertice tVerticeV : vListaVertice) {
                TArista newArista = new TArista(tVerticeU.getEtiqueta(), tVerticeV.getEtiqueta(), 0);
                TAdyacencia aux = this.existeAristaConCosto(newArista.getEtiquetaOrigen(),
                        newArista.getEtiquetaDestino());
                if (aux != null) {
                    newArista.setCosto(aux.getCosto());
                    if (newArista.getCosto() < costoMin) {
                        costoMin = newArista.getCosto();
                        arista = newArista;
                    }
                }
            }
        }
        if (arista.getEtiquetaDestino() == null && arista.getEtiquetaOrigen() == null) {
            return null;
        } else {
            return arista;
        }
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        TAristas aristas = new TAristas();
        aristas.addAll(this.lasAristas);
        List<TArista> F = new LinkedList<>();
        Map<Comparable, Integer> componentes = new HashMap<>();
        int i = 1;
        for (Comparable etiqueta : getVertices().keySet()) {
            componentes.put(etiqueta, i);
            i++;
        }
        while (F.size() < getVertices().size() - 1) {
            TArista arista = aristas.buscarAristaMinima();
            aristas.remove(arista);
            int compOrigen = componentes.get(arista.getEtiquetaOrigen());
            int compDestino = componentes.get(arista.getEtiquetaDestino());

            if (compOrigen != compDestino) {
                F.add(arista);
                for (Comparable c : componentes.keySet()) {
                    componentes.replace(c, compOrigen, compDestino);
                }
            }
        }
        return new TGrafoNoDirigido(getVertices().values(), F);
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        TVertice v = buscarVertice(etiquetaOrigen);
        if (v == null) {
            return null;
        }
        return bea(v);
    }

    public Collection<TVertice> bea(TVertice vertice) {
        if (vertice == null) {
            return null;
        }
        List<TVertice> result = new LinkedList<>();
        vertice.bea(result);
        return result;
    }

    @Override
    public boolean esConexo() {
        desvisitarVertices();
        List<TVertice> result = new LinkedList<>();
        int i = 0;
        for (TVertice v : getVertices().values()) {
            if (!v.getVisitado()) {
                v.bea(result);
                i++;
                if (i > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean conectados(TVertice origen, TVertice destino) {
        if (buscarVertice(origen.getEtiqueta()) != null && buscarVertice(destino.getEtiqueta()) != null) {
            desvisitarVertices();
            origen.conectadoCon(destino);
        }
        return false;
    }

    public List<TVertice> puntosArt(TVertice origen) {
        List<TVertice> resultado = new LinkedList<>();
        int[] cont = new int[1];
        origen.puntosArt(cont, resultado, origen.getEtiqueta());
        return resultado;
    }

    public List<TVertice> menosSaltos(Comparable origen, Comparable destino) {
        if(origen != null && destino != null) {
            TVertice vertOrigen = buscarVertice(origen);
            TVertice vertDestino = buscarVertice(destino);
            if (vertOrigen != null && vertDestino != null) {
                desvisitarVertices();
                return vertOrigen.menosSaltos(destino);
            }
        }
        return null;
    }
}
