package ut5.TDA;

public class TNodoAG<T> implements INodoAG<T>{

    private TNodoAG<T> hijoIzquierdo;
    private TNodoAG<T> hermanoDerecho;
    private Comparable etiqueta;
    private T dato;

    public TNodoAG(Comparable unaEtiqueta, T dato) {
        this.hijoIzquierdo = null;
        this.hermanoDerecho = null;
        this.etiqueta = unaEtiqueta;
        this.dato = dato;
    }

    @Override
    public TNodoAG<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    @Override
    public TNodoAG<T> getHermanoDerecho() {
        return hermanoDerecho;
    }
    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public T getDato() {
        return dato;
    }

    @Override
    public void setHijoIzquierdo(TNodoAG<T> nodo) {
       this.hijoIzquierdo = nodo;
    }

    @Override
    public void setHermanoDerecho(TNodoAG<T> nodo) {
        this.hermanoDerecho = nodo;
    }

    @Override
    public void setEtiqueta(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
    }

    @Override
    public void setDato(T dato) {
        this.dato = dato;
    }

    @Override
    public boolean insertar(Comparable unaEtiqueta, Comparable etiquetaPadre) {
        if (etiqueta == etiquetaPadre) {
            if (hijoIzquierdo == null) {
                hijoIzquierdo = new TNodoAG<T>(unaEtiqueta, null);
                return true;
            }
            TNodoAG<T> hijo = hijoIzquierdo;
            while (hijo.hermanoDerecho != null) {
                hijo = hijo.hermanoDerecho;
            }
            hijo.hermanoDerecho = new TNodoAG<T>(unaEtiqueta, null);
            return true;
        }
        if (hermanoDerecho != null){
            boolean insercionHermano = hermanoDerecho.insertar(unaEtiqueta, etiquetaPadre);
            if (insercionHermano){
                return true;
            }
        }
        if (hijoIzquierdo != null){
            boolean insercionHijo = hijoIzquierdo.insertar(unaEtiqueta, etiquetaPadre);
            if (insercionHijo){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insertar(Comparable unaEtiqueta, T dato, Comparable etiquetaPadre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public void eliminar(T elemento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public boolean buscar(Comparable unaEtiqueta) {
        if (unaEtiqueta == etiqueta) {
            return true;
        }
        if (hermanoDerecho != null){
            boolean busquedaHermano = hermanoDerecho.buscar(unaEtiqueta);
            if (busquedaHermano){
                return true;
            }
        }
        if (hijoIzquierdo != null){
            boolean busquedaHijo = hijoIzquierdo.buscar(unaEtiqueta);
            if (busquedaHijo){
                return true;
            }
        }
        return false;
    }

    @Override
    public int altura() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'altura'");
    }

    @Override
    public int nivel(T elemento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nivel'");
    }

    @Override
    public int cantidadNodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cantidadNodos'");
    }

    @Override
    public int cantidadHojas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cantidadHojas'");
    }

    @Override
    public String listarIndentadoPorNiveles() {
        StringBuilder sb = new StringBuilder();
        sb.append(etiqueta.toString());
        if (hijoIzquierdo != null){
            sb.append("\n"+indentNewLines(hijoIzquierdo.listarIndentadoPorNiveles()));
        }
        if (hermanoDerecho != null){
            sb.append("\n"+ hermanoDerecho.listarIndentadoPorNiveles());
        }
        return sb.toString();
    }

    private static String indentNewLines(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] lines = input.split("\n");

        for (String line : lines) {
            stringBuilder.append("    ").append(line).append("\n");
        }

        return stringBuilder.toString();
    }



}
