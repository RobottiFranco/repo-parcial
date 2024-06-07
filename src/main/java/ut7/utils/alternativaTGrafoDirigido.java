package ut7.utils;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import ut7.TDA.*;

public class alternativaTGrafoDirigido  implements IGrafoDirigido {

    private Double[][] floyd;
    private Double[][] predecesoresFloyd;
    private boolean[][] warshall;
    private Double[][] predecesoresWarshall;

    private Map<Comparable, TVertice> vertices; // vertices del grafo.-

    public alternativaTGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
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
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
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
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Metodo encargado de insertar una arista en el grafo (con un cierto
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
     * No pueden ingresarse vertices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a ingresar.
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

    /**
     * @return the vertices
     */
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Comparable centroDelGrafo() {
        ArrayList<Comparable> etiquetas = new ArrayList<>(vertices.keySet());
        Comparable centro = null;
        Comparable min = Double.MAX_VALUE;

        for (Comparable etiqueta : etiquetas) {
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
        if (this.floyd != null) {
            return this.floyd;
        }

        Double[][] C = UtilGrafos.obtenerMatrizCostos(this.vertices);
        this.floyd = new Double[C.length][C.length];
        this.predecesoresFloyd = new Double[C.length][C.length];

        for (int i = 0; i < this.floyd.length; i++) {
            for (int j = 0; j < this.floyd.length; j++) {
                this.floyd[i][j] = C[i][j];
                this.predecesoresFloyd[i][j] = null;
            }
            this.floyd[i][i] = 0.0;
        }

        for (int k = 0; k < this.floyd.length; k++) {
            for (int i = 0; i < this.floyd.length; i++) {
                if (i == k || this.floyd[i][k] == Double.MAX_VALUE) {
                    continue;
                }
                for (int j = 0; j < this.floyd.length; j++) {
                    if (this.floyd[i][k] + this.floyd[k][j] < this.floyd[i][j]) {
                        this.floyd[i][j] = this.floyd[i][k] + this.floyd[k][j];
                        this.predecesoresFloyd[i][j] = Double.valueOf(k);
                    }
                }
            }
        }
        return this.floyd;
    }

    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        if (existeVertice(etiquetaVertice) == false) {
            return -1.0;
        }
        if (this.floyd == null) {
            floyd();
        }
        ArrayList<Comparable> etiquetas = new ArrayList<>(vertices.keySet());
        int j = etiquetas.indexOf(etiquetaVertice);
        double excentricidad = 0.0;
        for (int i = 0; i < floyd.length; i++) {
            excentricidad = (this.floyd[i][j] > excentricidad) ? this.floyd[i][j] : excentricidad;
        }
        return excentricidad;
    }

    @Override
    public boolean[][] warshall() {
        if (this.warshall != null) {
            return this.warshall;
        }
        Double[][] C = UtilGrafos.obtenerMatrizCostos(vertices);
        this.predecesoresWarshall = new Double[C.length][C.length];

        this.warshall = new boolean[C.length][C.length];
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C.length; j++) {
                if (C[i][j] != Double.MAX_VALUE) {
                    this.warshall[i][j] = true;
                    this.predecesoresWarshall[i][j] = null;

                }
            }
        }
        for (int k = 0; k < C.length; k++) {
            for (int i = 0; i < C.length; i++) {
                if (this.warshall[i][k] == false) {
                    continue;
                }
                for (int j = 0; j < C.length; j++) {
                    if (this.warshall[i][j] == false) {
                        this.warshall[i][j] = this.warshall[i][k] && this.warshall[k][j];
                        this.predecesoresWarshall[i][j] = Double.valueOf(k);

                    }
                }
            }
        }
        return this.warshall;
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null && existeVertice(nombreVertice)) {
            for (Comparable vertice : this.vertices.keySet()) {
                if (vertice.compareTo(nombreVertice) != 0) {
                    buscarVertice(vertice).eliminarAdyacencia(nombreVertice);
                }
            }
            this.vertices.remove(nombreVertice);
            return true;
        }
        return false;
    }

    public Double[][] getPredecesoresFloyd() {
        return predecesoresFloyd;
    }

    public Double[][] getPredecesoresWarshall() {
        return predecesoresWarshall;
    }

    public void desvisitarVertices() {
        for (TVertice v : vertices.values()) {
            v.setVisitado(false);
        }
    }

    @Override
    public Collection<Comparable> bpf() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bpf'");
    }

    @Override
    public Collection<Comparable> bpf(TVertice vertice) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bpf'");
    }

    @Override
    public Collection<Comparable> bpf(Comparable etiquetaOrigen) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bpf'");
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'todosLosCaminos'");
    }

    @Override
    public boolean tieneCiclo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tieneCiclo'");
    }

}
