package ut4.TDA;

public class TElementoAB<T> implements IElementoAB<T> {

    private Comparable etiqueta;
    private TElementoAB<T> hijoIzq;
    private TElementoAB<T> hijoDer;
    private T datos;

    /**
     * @param unaEtiqueta
     * @param unosDatos
     */
    @SuppressWarnings("unchecked")
    public TElementoAB(Comparable unaEtiqueta, T unosDatos) {
        etiqueta = unaEtiqueta;
        datos = unosDatos;
    }

    public TElementoAB<T> getHijoIzq() {
        return hijoIzq;
    }

    public TElementoAB<T> getHijoDer() {
        return hijoDer;
    }

    /**
     * @param unElemento
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean insertar(TElementoAB<T> unElemento) {
        if (unElemento.getEtiqueta().compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().insertar(unElemento);
            } else {
                hijoIzq = unElemento;
                return true;
            }
        } else if (unElemento.getEtiqueta().compareTo(etiqueta) > 0) {
            if (hijoDer != null) {
                return getHijoDer().insertar(unElemento);
            } else {
                hijoDer = unElemento;
                return true;
            }
        } else {
            // ya existe un elemento con la misma etiqueta.-
            return false;
        }
    }

    /**
     * @param unaEtiqueta
     * @return
     */
    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {

        if (unaEtiqueta.equals(etiqueta)) {
            return this;
        } else if (unaEtiqueta.compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().buscar(unaEtiqueta);
            } else {
                return null;
            }
        } else if (hijoDer != null) {
            return getHijoDer().buscar(unaEtiqueta);
        } else {
            return null;
        }
    }

    public Comparable claveAnterior(Comparable etiqueta) {
        TElementoAB<T> actual = this;
        TElementoAB<T> anterior = null;

        if (actual.getEtiqueta().compareTo(etiqueta) == 0) {
            return anterior.getEtiqueta();
        }

        if (actual.getEtiqueta().compareTo(etiqueta) < 0) {
            if (actual.hijoIzq != null) {
                return actual.hijoIzq.claveAnterior(etiqueta);
            }
        }

        if (actual.getEtiqueta().compareTo(etiqueta) > 0) {
            if (actual.hijoDer != null) {
                return actual.hijoDer.claveAnterior(etiqueta);
            }
        }

        return null;
    }

    public Comparable menorClave() {
        if (this.hijoIzq != null) {
            return this.hijoIzq.menorClave();
        }
        return this.getEtiqueta();
    }

    public Comparable mayorClave() {
        if (this.hijoDer != null) {
            return this.hijoDer.menorClave();
        }
        return this.getEtiqueta();
    }

    public int obtenerBalance() {
        int alturaIzq = -1;
        int alturaDer = -1;

        if (this.hijoIzq != null) {
            alturaIzq = this.hijoIzq.obtenerAltura();
        }

        if (this.hijoDer != null) {
            alturaDer = this.hijoDer.obtenerAltura();
        }

        return alturaDer - alturaIzq;
    }

    public boolean estaBalanceado() {

        if (Math.abs(this.obtenerBalance()) > 1) {
            return false;
        }

        boolean result = true;

        if (getHijoIzq() != null) {
            result = getHijoIzq().estaBalanceado();
        }

        if (result == false) {
            return result;
        }

        if (getHijoDer() != null) {
            result = getHijoDer().estaBalanceado();
        }

        return result;
    }

    public int sumaDeClaves(int nivelBuscado, int nivelActual) {

        int suma = 0;

        if (nivelBuscado == nivelActual) {
            return (Integer) this.etiqueta;
        }
        if (hijoIzq != null) {
            suma += hijoIzq.sumaDeClaves(nivelBuscado, nivelActual + 1);
        }

        if (hijoDer != null) {
            suma += hijoDer.sumaDeClaves(nivelBuscado, nivelActual + 1);
        }
        return suma;
    }

    public boolean esIdentico(TElementoAB otroElemento) {
        if (this.etiqueta.compareTo(otroElemento.etiqueta) != 0
                && !(this.datos.equals(otroElemento.datos))) {
            return false;
        }
        boolean result = true;

        if (this.hijoIzq != null && otroElemento.hijoIzq != null) {
            result = this.hijoIzq.esIdentico(otroElemento.hijoIzq);
        } else if (this.hijoIzq != null || otroElemento.hijoIzq != null) {
            return false;
        }

        if (result == false) {
            return result;
        }

        if (this.hijoDer != null && otroElemento.hijoDer != null) {
            result = this.hijoDer.esIdentico(otroElemento.hijoDer);
        } else if (this.hijoDer != null || otroElemento.hijoDer != null) {
            return false;
        }
        return result;
    }

    /**
     * @return recorrida en inorden del subArbol que cuelga del elemento actual
     */
    @Override
    public String inOrden() {
        String result = "";

        if (this.hijoIzq != null) {
            result += hijoIzq.inOrden() + " ";
        }
        result += this.etiqueta;

        if (this.hijoDer != null) {
            result += " " + hijoDer.inOrden();
        }
        return result;
    }

    @Override
    public void inOrden(Lista<T> unaLista) {

        if (this.hijoIzq != null) {
            this.hijoIzq.inOrden(unaLista);
        }

        Nodo nodo = new Nodo(this.etiqueta, this.datos);
        unaLista.insertar(nodo);

        if (this.hijoDer != null) {
            this.hijoDer.inOrden(unaLista);
        }

    }

    @Override
    public String preOrden() {

        String result = this.etiqueta.toString();

        if (this.hijoIzq != null) {
            result += " " + hijoIzq.preOrden();
        }
        if (this.hijoDer != null) {
            result += " " + hijoDer.preOrden();
        }

        return result;
    }

    @Override
    public String postOrden() {
        String result = "";

        if (this.hijoIzq != null) {
            result += hijoIzq.postOrden() + " ";
        }
        if (this.hijoDer != null) {
            result += hijoDer.postOrden() + " ";
        }
        return result += this.etiqueta;
    }

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    /**
     * @return
     */
    public String imprimir() {
        return (etiqueta.toString());
    }

    @Override
    public T getDatos() {
        return datos;
    }

    @Override
    public void setHijoIzq(TElementoAB<T> elemento) {
        this.hijoIzq = elemento;

    }

    @Override
    public void setHijoDer(TElementoAB<T> elemento) {
        this.hijoDer = elemento;
    }

    @Override
    public int obtenerAltura() {

        int alturaIzq = -1;
        int alturaDer = -1;

        if (this.hijoIzq != null) {
            alturaIzq = this.hijoIzq.obtenerAltura();
        }

        if (this.hijoDer != null) {
            alturaDer = this.hijoDer.obtenerAltura();
        }

        return Math.max(alturaIzq, alturaDer) + 1;

    }

    @Override
    public int obtenerTamanio() {

        int tamanio = 0;

        if (hijoIzq != null) {
            tamanio += hijoIzq.obtenerTamanio();
        }

        if (hijoDer != null) {
            tamanio += hijoDer.obtenerTamanio();
        }

        return tamanio + 1;
    }

    @Override
    public int obtenerNivel(Comparable unaEtiqueta) {

        int nivel = -1;

        if (unaEtiqueta.compareTo(this.etiqueta) < 0) {
            if (hijoIzq != null) {
                nivel = hijoIzq.obtenerNivel(unaEtiqueta);
            }

        } else if (unaEtiqueta.compareTo(this.etiqueta) > 0) {
            if (hijoDer != null) {
                nivel = hijoDer.obtenerNivel(unaEtiqueta);
            }
        } else {
            return 0;
        }

        if (nivel == -1) {
            return -1;
        }

        return nivel + 1;
    }

    @Override
    public int obtenerCantidadHojas() {
        int x = 0;
        int y = 0;

        if (this.hijoIzq != null) {
            x = this.hijoIzq.obtenerCantidadHojas();
        }

        if (this.hijoDer != null) {
            y = this.hijoDer.obtenerCantidadHojas();
        }

        if (this.hijoIzq == null && this.hijoDer == null) {
            return 1;
        }

        return x + y;
    }

    @Override
    public TElementoAB eliminar(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(this.etiqueta) < 0) {
            if (this.hijoIzq != null) {
                this.hijoIzq = this.hijoIzq.eliminar(unaEtiqueta);
            }
            return this;
        }

        if (unaEtiqueta.compareTo(this.etiqueta) > 0) {
            if (this.hijoDer != null) {
                this.hijoDer = this.hijoDer.eliminar(unaEtiqueta);
            }
            return this;
        }
        return quitaElNodo();

    }

    protected TElementoAB quitaElNodo() {
        if (this.hijoIzq == null) {
            return this.hijoDer;
        }

        if (this.hijoDer == null) {
            return this.hijoIzq;
        }

        TElementoAB<T> elHijo = this.hijoIzq;
        TElementoAB<T> elPadre = this;

        while (elHijo.hijoDer != null) {
            elPadre = elHijo;
            elHijo = elHijo.hijoDer;
        }

        if (elPadre != this) {
            elPadre.hijoDer = elHijo.hijoIzq;
            elHijo.hijoIzq = this.hijoIzq;
        }

        elHijo.hijoDer = this.hijoDer;
        return elHijo;
    }

}
