package ut7.TDA;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, TVertice> vertices; // lista de vertices del grafo.-

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
     * etiquetas sean inv�lidas, retorna falso.
     *
     */
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /* es por lista de predecesores, materia 1 y 2 preceden a materia 3 */
    public LinkedList<String> ordenParcial() {
        LinkedList<String> resultado = new LinkedList<>();
        Set<TVertice> visitados = new HashSet<>();
        for (TVertice vertice : vertices.values()) {
            if (!visitados.contains(vertice)) {
                vertice.ordenParcial(visitados, resultado);
            }
        }
        return resultado;
    }

    public void listarVerticeOrdenParcial(LinkedList<String> orden) {
        for (String tarea : orden) {
            System.out.println(tarea);
        }
    }


    /**
     * Metodo encargado de eliminar un vertice en el grafo. En caso de no
     * existir el v�rtice, retorna falso. En caso de que la etiqueta sea
     * inv�lida, retorna false.
     *
     */
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null && existeVertice(nombreVertice)) {
            for (TVertice vert : this.vertices.values()) {
                vert.eliminarAdyacencia(nombreVertice);
            }
            this.vertices.remove(nombreVertice);
            return true;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @return True si existe la adyacencia, false en caso contrario
     */
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
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
    private TVertice buscarVertice(Comparable unaEtiqueta) {
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
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
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

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    /**
     * @return the vertices
     */
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Collection<Comparable> bpf(TVertice vertice) {
        if (vertice == null) {
            throw new Error("Vertice debe ser no nulo");
        }
        List<Comparable> visitados = new LinkedList<>();
        vertice.bpf(visitados);
        return visitados;
    }

    @Override
/*     public Collection<Comparable> bpf(Comparable etiquetaOrigen) {
        TVertice origen = buscarVertice(etiquetaOrigen);
        if (origen == null) {
            return null;
        }
        return bpf(origen);
    } */
    public Collection<Comparable> bpf(Comparable etiquetaOrigen) {
        this.desvisitarVertices();

        TVertice origen = buscarVertice(etiquetaOrigen);
        if (origen == null) {
            return null;
        }
        Collection <Comparable> devolver = new LinkedList<Comparable>();
        origen.bpf(devolver);
        return devolver;
    }

    @Override
    /**
     * BPF del grafo, a partir del primer vértice, para todos los vertices
     */

/*     public Collection<Comparable> bpf() {

        this.desvisitarVertices();

        List<Comparable> recorridos = new ArrayList<>(this.vertices.size());

        for (TVertice tVertice : vertices.values()) {
            if (!tVertice.getVisitado()) {
                recorridos.addAll(bpf(tVertice));
            }
        }
        return recorridos;
    } */
    public Collection<Comparable> bpf() {

        this.desvisitarVertices();

        LinkedList<Comparable> recorridos = new LinkedList<Comparable>();

        for (TVertice tVertice : vertices.values()) {
            if (!tVertice.getVisitado()) {
                LinkedList<Comparable> recorrido = new LinkedList<Comparable>();
                tVertice.bpf(recorrido);
                recorridos.addAll(recorrido);
            }
        }
        return recorridos;
    }

    public void desvisitarVertices() {
        for (TVertice vertice : this.vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    /**
     * @param etiquetaOrigen
     * @param etiquetaDestino
     * @param comparadorCaminos
     * @return Un array con las claves de los vertices que componen el mejor
     *         camino, en caso de que exista
     */
    @Override
    public boolean tieneCiclo() {
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
            if (floyd[i][j] == Double.MAX_VALUE) {
                excentricidad = 0;
            } else {
                excentricidad = (floyd[i][j] > excentricidad) ? floyd[i][j] : excentricidad;
            }
        }
        return excentricidad;
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

    public List<Comparable> clasificacionTopologica(Comparable origen) {
        if (buscarVertice(origen) == null) {
            return null;
        }
        List<Comparable> result = new LinkedList<>();
        buscarVertice(origen).clasificacionTopologica(result);
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
}
