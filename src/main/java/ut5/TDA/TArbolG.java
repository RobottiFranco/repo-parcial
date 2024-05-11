package ut5.TDA;

public class TArbolG<T> implements IArbolG<T>{

    private TNodoAG<T> raiz;

    public TArbolG() {
        this.raiz = null;
    }

    @Override
    public TNodoAG<T> getRaiz() {
        return raiz;
    }

    @Override
    public void setRaiz(TNodoAG<T> raiz) {
        this.raiz = raiz;
    }

    @Override
    public boolean insertar(Comparable unaEtiqueta, Comparable etiquetaPadre) {
        if (etiquetaPadre == "") {
            raiz = new TNodoAG<T>(unaEtiqueta, null);
            return true;
        }
        if (raiz == null){
            return false;
        } else {
            return raiz.insertar(unaEtiqueta, etiquetaPadre);
        }
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
    public boolean buscar(Comparable elemento) {
        if (raiz == null) {
            return false;
        } else {
            return raiz.buscar(elemento);
        }
    }

    @Override
    public void vaciar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'vaciar'");
    }

    @Override
    public boolean esVacio() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esVacio'");
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
        if (raiz == null){
            return "";
        } else {
            return raiz.listarIndentadoPorNiveles();
        }
    }





}
