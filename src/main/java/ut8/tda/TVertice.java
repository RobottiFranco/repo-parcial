package ut8.tda;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TVertice<T> implements IVertice {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;
    private int numBpf;
    private int numBajo;
    private int cantDesc;
    private TVertice predecesor;

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
        numBpf = -1;
        numBajo  = -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o ||
                this.etiqueta.compareTo(((TVertice) o).etiqueta) == 0) {
            return true;
        }
        return false;
    }

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public T getDatos() {
        return datos;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    @Override
    public void bpf(Collection<TVertice> visitados) {
        setVisitado(true);
        visitados.add(this);
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                vertAdy.bpf(visitados);
            }
        }
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        setVisitado(true);
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                caminoPrevio.agregarAdyacencia(adyacente);
                if (vertAdy.etiqueta.compareTo(etVertDest) == 0) {
                    todosLosCaminos.getCaminos().add(caminoPrevio.copiar());
                } else {
                    vertAdy.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                }
                caminoPrevio.eliminarAdyacencia(adyacente);
            }
        }
        setVisitado(false);
        return todosLosCaminos;
    }

    @Override
    public void bea(Collection<TVertice> visitados) {
        Queue<TVertice> cola = new LinkedList<>();
        cola.add(this);
        visitados.add(this);
        this.visitado = true;
        while (!cola.isEmpty()) {
            TVertice x = cola.remove();
            for (TAdyacencia ady : (LinkedList<TAdyacencia>) x.getAdyacentes()) {
                TVertice y = ady.getDestino();
                if (!y.getVisitado()) {
                    y.setVisitado(true);
                    cola.add(y);
                    visitados.add(y);
                }
            }
        }
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        TAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

    @Override
    public boolean tieneCiclo(LinkedList<Comparable> camino) {
        setVisitado(true);
        camino.add(this.etiqueta);
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (vertAdy.getVisitado()) {
                return true;
            }
            if (vertAdy.tieneCiclo(camino)) {
                return true;
            }
        }
        setVisitado(false);
        camino.remove(this.etiqueta);
        return false;
    }

    @Override
    public boolean conectadoCon(TVertice destino) {
        List<TVertice> conexiones = new LinkedList<>();
        bea(conexiones);
        return (conexiones.contains(destino)) ? true : false;
    }

    public void clasificacionTopologica(List<Comparable> orden) {
        setVisitado(true);
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                vertAdy.clasificacionTopologica(orden);
            }
        }
        orden.add(this.etiqueta);
    }

    public void clasificarArcos(List<TArista> arcosArbol, List<TArista> arcosRetroceso, List<TArista> arcosAvance,
            List<TArista> arcosCruzados) {

        setVisitado(true);
        for (TAdyacencia adyacencia : getAdyacentes()) {
            TVertice destino = adyacencia.getDestino();
            TArista arista = new TArista(this.etiqueta, destino.etiqueta, adyacencia.getCosto());
            if (!destino.getVisitado()) {
                arcosArbol.add(arista);
                destino.clasificarArcos(arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados);
            } else {
                if (this.numBpf <= destino.numBpf && destino.numBpf <= this.numBpf + this.cantDesc) {
                    arcosAvance.add(arista);
                } else if (destino.numBpf <= this.numBpf && this.numBpf <= destino.numBpf + destino.cantDesc) {
                    arcosRetroceso.add(arista);
                } else {
                    arcosCruzados.add(arista);
                }
            }
        }
    }

    public int cantDescendientes() {
        setVisitado(true);
        int descendientes = 0;
        for (TAdyacencia tAdyacencia : adyacentes) {
            TVertice destino = tAdyacencia.getDestino();
            if (!destino.getVisitado()) {
                descendientes += destino.cantDescendientes();
            }
        }
        this.cantDesc = descendientes;
        return descendientes + 1;
    }

    public int asignaNumBpf(int num) {
        setVisitado(true);
        this.numBpf = num;
        for (TAdyacencia tAdyacencia : adyacentes) {
            TVertice destino = tAdyacencia.getDestino();
            if (!destino.getVisitado()) {
                num = destino.asignaNumBpf(num + 1);
            }
        }
        return num;
    }

    public void puntosArt(int[] cont, List<TVertice> resultado, Comparable padre) {
        this.visitado = true;
        cont[0]++;
        this.numBpf = cont[0];
        this.numBajo = numBpf;
        List<TVertice> hijos = new LinkedList<>();
        for (TAdyacencia ady : this.adyacentes) {
            TVertice v = ady.getDestino();
            if (!v.getVisitado()) {
                v.puntosArt(cont, resultado, this.etiqueta);
                hijos.add(v);
                if (this.numBpf == 1) {
                    if (hijos.size() > 1) {
                        resultado.add(this);
                    }
                } else {
                    if (v.numBajo >= this.numBpf) {
                        resultado.add(this);
                    }
                }
                if (v.numBajo < this.numBajo)
                    this.numBajo = v.numBajo;
            } else {
                if (v.etiqueta.compareTo(padre) != 0) { // Usar etiqueta
                    if (v.numBpf < this.numBajo)
                        this.numBajo = v.numBpf;
                }
            }
        }
    }

    public List<TVertice> menosSaltos(Comparable destino) {
        Queue<TVertice> cola = new LinkedList<>();
        List<TVertice> result = new LinkedList<>();
        cola.add(this);
        this.visitado = true;
        loop:
        while (!cola.isEmpty()) {
            TVertice x = cola.remove();
            for (TAdyacencia ady : (LinkedList<TAdyacencia>) x.getAdyacentes()) {
                TVertice y = ady.getDestino();
                if (!y.getVisitado()) {
                    y.setVisitado(true);
                    cola.add(y);
                    y.predecesor = x;
                    if (y.etiqueta.compareTo(destino) == 0) {
                        result.add(0, y);
                        TVertice p = y.predecesor;
                        while (p.etiqueta.compareTo(this.etiqueta) != 0) {
                            result.add(0, p);
                            p = p.predecesor;
                        }
                        result.add(0, this);
                        break loop;
                    }
                }
            }
        }
        return result;
    }

    public TCamino caminoMasCorto(Comparable etVertDest, TCamino caminoPrevio, TCamino caminoFinal, double[] costo, double[] min) {
        setVisitado(true);
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                caminoPrevio.agregarAdyacencia(adyacente);
                costo[0] += adyacente.getCosto();
                if (vertAdy.etiqueta.compareTo(etVertDest) == 0) {
                    if (costo[0] < min[0]) {
                        min[0] = costo[0];
                        caminoFinal.getOtrosVertices().clear();
                        caminoFinal.getOtrosVertices().addAll(caminoPrevio.getOtrosVertices());
                        caminoFinal.setCostoTotal(caminoPrevio.getCostoTotal());
                    }
                } else {
                    vertAdy.caminoMasCorto(etVertDest, caminoPrevio, caminoFinal, costo, min);
                }
                costo[0] -= adyacente.getCosto();
                caminoPrevio.eliminarAdyacencia(adyacente);
            }
        }
        setVisitado(false);
        return caminoFinal;
    }
}
