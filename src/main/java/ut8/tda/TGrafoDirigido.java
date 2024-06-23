package ut8.tda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ut8.utils.UtilGrafos;

public class TGrafoDirigido implements IGrafoDirigido {

    private final Map<Comparable, TVertice> vertices; // lista de vertices del grafo.-

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     * @param nomVerticeOrigen
     * @param nomVerticeDestino
     * @return
     */
    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de eliminar un vertice en el grafo. En caso de no
     * existir el v�rtice, retorna falso. En caso de que la etiqueta sea
     * inv�lida, retorna false.
     *
     * @param nombreVertice
     * @return
     */
    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            getVertices().remove(nombreVertice);
            return getVertices().containsKey(nombreVertice);
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @param etiquetaOrigen
     * @param etiquetaDestino
     * @return True si existe la adyacencia, false en caso contrario
     */
    @Override
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    public TAdyacencia existeAristaConCosto(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino);
        }
        return null;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     *         contrario
     */
    @Override
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    protected TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Matodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @param arista
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            tempbool = (vertOrigen != null) && (vertDestino != null);
            if (tempbool) {
                // getLasAristas().add(arista);
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }

        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse v�rtices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    @Override
    public void desvisitarVertices() {
        for (TVertice vertice : this.vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    /**
     * @return the vertices
     */
    @Override
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Collection<TVertice> bpf(TVertice vertice) {
        if (vertice == null) {
            throw new Error("Vertice debe ser no nulo");
        }
        List<TVertice> visitados = new LinkedList<>();
        vertice.bpf(visitados);
        return visitados;
    }

    @Override
    public Collection<TVertice> bpf() {
        this.desvisitarVertices();

        List<TVertice> recorridos = new ArrayList<>(this.vertices.size());

        for (TVertice tVertice : vertices.values()) {
            if (!tVertice.getVisitado()) {
                tVertice.bpf(recorridos);
            }
        }
        return recorridos;
    }

    @Override
    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        TVertice origen = buscarVertice(etiquetaOrigen);
        if (origen == null) {
            return null;
        }
        return bpf(origen);
    }

    @Override
    public Comparable centroDelGrafo() {
        Comparable centro = null;
        Comparable min = Double.MAX_VALUE;

        for (Comparable etiqueta : vertices.keySet()) {
            Comparable exc = obtenerExcentricidad(etiqueta);
            if (exc.compareTo(min) < 0) {
                min = exc;
                centro = etiqueta;
            }
        }
        return centro;
    }

    @Override
    public Double[][] floyd() {
        Double[][] C = UtilGrafos.obtenerMatrizCostos(this.vertices);
        Double[][] floyd = new Double[C.length][C.length];

        for (int i = 0; i < floyd.length; i++) {
            for (int j = 0; j < floyd.length; j++) {
                floyd[i][j] = C[i][j];
                // this.predecesoresFloyd[i][j] = null;
            }
            floyd[i][i] = 0.0;
        }

        for (int k = 0; k < floyd.length; k++) {
            for (int i = 0; i < floyd.length; i++) {
                if (i == k || floyd[i][k] == Double.MAX_VALUE) {
                    continue;
                }
                for (int j = 0; j < floyd.length; j++) {
                    if (floyd[i][k] + floyd[k][j] < floyd[i][j]) {
                        floyd[i][j] = floyd[i][k] + floyd[k][j];
                        // this.predecesoresFloyd[i][j] = Double.valueOf(k);
                    }
                }
            }
        }
        return floyd;
    }

    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        if (existeVertice(etiquetaVertice) == false) {
            return -1.0;
        }

        Double[][] floyd = floyd();

        int j = new ArrayList<>(vertices.keySet()).indexOf(etiquetaVertice);
        double excentricidad = 0.0;
        for (int i = 0; i < floyd.length; i++) {
            excentricidad = (floyd[i][j] > excentricidad) ? floyd[i][j] : excentricidad;
        }
        return excentricidad;
    }

    @Override
    public boolean[][] warshall() {
        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(getVertices());
        boolean[][] matrizWarshall = new boolean[matrizCostos.length][matrizCostos.length];
        for (int i = 0; i < matrizCostos.length; i++) {
            for (int j = 0; j < matrizCostos.length; j++) {
                matrizWarshall[i][j] = false;

                if (i != j && matrizCostos[i][j] != Double.MAX_VALUE) {
                    matrizWarshall[i][j] = true;
                }
            }
        }
        for (int k = 0; k < matrizWarshall.length; k++) {
            for (int i = 0; i < matrizWarshall.length; i++) {
                for (int j = 0; j < matrizWarshall.length; j++) {
                    if ((i != k) && (k != j) && (i != j)) {
                        if (!matrizWarshall[i][j]) {
                            matrizWarshall[i][j] = matrizWarshall[i][k] && matrizWarshall[k][j];
                        }
                    }
                }
            }
        }
        return matrizWarshall;
    }

    public HashMap<Comparable, Double> dijkstra(Comparable origen) {

        Double[][] C = UtilGrafos.obtenerMatrizCostos(vertices);
        HashMap<Comparable, Double> result = new HashMap<>(C.length);
        ArrayList<Comparable> VmenosS = new ArrayList<>(vertices.keySet());
        int i = VmenosS.indexOf(origen);

        for (int j = 0; j < C.length; j++) {
            result.put(VmenosS.get(j), C[i][j]);
        }

        VmenosS.remove(origen);

        for (int j = 0; j < this.vertices.size() - 1; j++) {
            double min = Double.MAX_VALUE;
            Comparable winner = null;
            for (Comparable etiqueta : VmenosS) {
                if (result.get(etiqueta) <= min) {
                    min = result.get(etiqueta);
                    winner = etiqueta;
                }
            }
            VmenosS.remove(winner);
            for (Comparable etiqueta : VmenosS) {
                if (min + buscarVertice(winner).obtenerCostoAdyacencia(buscarVertice(etiqueta)) < result
                        .get(etiqueta)) {
                    result.put(etiqueta, min + buscarVertice(winner).obtenerCostoAdyacencia(buscarVertice(etiqueta)));
                }
            }
        }
        return result;
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        if (buscarVertice(etiquetaOrigen) != null && buscarVertice(etiquetaDestino) != null) {
            desvisitarVertices();
            TVertice origen = buscarVertice(etiquetaOrigen);
            TCamino camino = new TCamino(origen);
            TCaminos caminos = new TCaminos();
            return origen.todosLosCaminos(etiquetaDestino, camino, caminos);
        }
        return null;
    }

    @Override
    public boolean tieneCiclo(Comparable etiquetaOrigen) {
        TVertice v = buscarVertice(etiquetaOrigen);
        if (v == null) {
            return false;
        }
        return v.tieneCiclo(new LinkedList<>());
    }

    @Override
    public boolean tieneCiclo(TCamino camino) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    @Override
    public boolean tieneCiclo() {
        desvisitarVertices();
        LinkedList<Comparable> camino = new LinkedList<>();
        for (TVertice tVertice : vertices.values()) {
            if (!tVertice.getVisitado()) {
                if (tVertice.tieneCiclo(camino)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Collection<TVertice> bea() {
        List<TVertice> result = new LinkedList<>();
        for (TVertice v : vertices.values()) {
            if (!v.getVisitado()) {
                v.bea(result);
            }
        }
        return result;
    }

    public List<Comparable> clasificacionTopologica(Comparable origen) {
        TVertice vertOrigen = buscarVertice(origen);
        if (vertOrigen == null) {
            return null;
        }
        List<Comparable> result = new LinkedList<>();
        vertOrigen.clasificacionTopologica(result);
        Collections.reverse(result);
        return result;
    }

    public void clasificarArcos(Comparable origen, List<TArista> arcosArbol, List<TArista> arcosRetroceso,
            List<TArista> arcosAvance, List<TArista> arcosCruzados) {
        TVertice vertOrigen = buscarVertice(origen);
        if (vertOrigen == null) {
            return;
        }
        desvisitarVertices();
        asignaNumBpf(origen);
        desvisitarVertices();
        cantDescendientes(origen);
        desvisitarVertices();
        vertOrigen.clasificarArcos(arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados);
        for (TVertice vertice : this.vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.clasificarArcos(arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados);
            }
        }
    }

    public void asignaNumBpf(Comparable origen) {
        TVertice v = buscarVertice(origen);
        if (v == null) {
            return;
        }
        int num = v.asignaNumBpf(1);
        for (TVertice vertice : this.vertices.values()) {
            if (!vertice.getVisitado()) {
                num = vertice.asignaNumBpf(num);
            }
        }
    }

    public void cantDescendientes(Comparable origen) {
        TVertice v = buscarVertice(origen);
        if (v == null) {
            return;
        }
        v.cantDescendientes();
        for (TVertice vertice : this.vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.cantDescendientes();
            }
        }
    }

    public TCamino caminoMasCorto(Comparable origen, Comparable destino) {
        if (origen != null && destino != null) {
            TVertice vOrigen = buscarVertice(origen);
            if (vOrigen == null) {
                return null;
            }
            TCamino caminoPrevio = new TCamino(vOrigen);
            TCamino result = caminoPrevio.copiar();
            double[] min = {Double.MAX_VALUE};
            return vOrigen.caminoMasCorto(destino, caminoPrevio, result, new double[1], min);
        }
        return null;
    }
}
