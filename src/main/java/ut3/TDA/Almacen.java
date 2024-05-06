package ut3.TDA;

import java.util.ArrayList;
import java.util.Comparator;


public class Almacen implements IAlmacen {

    private String nombre, direccion, telefono;
    private Lista<IProducto> listaDeProductos = new Lista<>();

    public Almacen(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    @Override
    public Boolean agregarStock(Comparable codProducto, Integer cantidad) {

        if (listaDeProductos.buscar(codProducto) == null) {
            return false;
        }

        this.listaDeProductos.buscar(codProducto).getDato().agregarCantidadStock(cantidad.intValue());
        return true;
    }

    @Override
    public IProducto buscarPorCodigo(Comparable codProducto) {
        if (this.listaDeProductos.buscar(codProducto) == null) {
            throw new IllegalArgumentException("El producto no está en la lista");
        }
        return this.listaDeProductos.buscar(codProducto).getDato();
    }

    @Override
    public IProducto buscarPorDescripcion(String descripcion) {

        Nodo<IProducto> actual = this.listaDeProductos.getPrimero();

        while (actual != null) {
            if (actual.getDato().getNombre().equals(descripcion)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public int cantidadProductos() {
        return this.listaDeProductos.cantElementos();
    }

    @Override
    public boolean eliminarProducto(Comparable codProducto) {
        return this.listaDeProductos.eliminar(codProducto);
    }

    @Override
    public String getDireccion() {
        return this.direccion;
    }

    @Override
    public Lista<IProducto> getListaProductos() {
        return this.listaDeProductos;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String getTelefono() {
        return this.telefono;
    }

    @Override
    public String imprimirProductos() {
        return this.listaDeProductos.imprimir();
    }

    @Override
    public String imprimirSeparador(String separador) {
        return this.listaDeProductos.imprimir(separador);
    }

    @Override
    public void insertarProducto(IProducto unProducto) {

        if (this.listaDeProductos.buscar(unProducto.getCodProducto()) != null) {
            this.agregarStock(unProducto.getCodProducto(), unProducto.getStock());
            return;
        }

        Nodo<IProducto> producto = new Nodo(unProducto.getCodProducto(), unProducto);
        this.listaDeProductos.insertar(producto);
    }

    @Override
    public void listarOrdenadoPorNombre() {
        Nodo<IProducto> actual = listaDeProductos.getPrimero();

        // Convertir la lista enlazada de productos a un ArrayList para ordenar
        ArrayList<IProducto> listaProductos = new ArrayList<>();
        while (actual != null) {
            listaProductos.add(actual.getDato());
            actual = actual.getSiguiente();
        }

        // Ordenar el ArrayList usando un comparador basado en los nombres de los
        // productos
        listaProductos.sort(Comparator.comparing(IProducto::getNombre));

        // Imprimir los nombres de los productos ordenados
        System.out.println("Productos ordenados por nombre:");
        for (IProducto producto : listaProductos) {
            System.out.println(producto.getNombre());
        }
    }

    public void listarOrdenadoPorNombre2() {

        if (listaDeProductos.esVacia()) {
            System.out.println("La lista de productos está vacía.");
            return;
        }

        Nodo<IProducto> actual = listaDeProductos.getPrimero();

        Lista<IProducto> listaOrdenada = new Lista<>();
        listaOrdenada.insertar(actual.clonar());

        Nodo<IProducto> actualOrdenado = listaOrdenada.getPrimero();
        actual = actual.getSiguiente();

        loop1: while (actual != null) {

            if (listaOrdenada.getPrimero().getDato().getNombre().compareTo(actual.getDato().getNombre()) > 0) {
                listaOrdenada.insertarAlPrincipio(actual.clonar());
                actual = actual.getSiguiente();
                continue loop1;
            }

            actualOrdenado = listaOrdenada.getPrimero();
            Nodo<IProducto> nodoAInsertar = actual.clonar();

            while (actualOrdenado.getSiguiente() != null) {

                if (actualOrdenado.getSiguiente().getDato().getNombre().compareTo(actual.getDato().getNombre()) > 0) {
                    nodoAInsertar.setSiguiente(actualOrdenado.getSiguiente());
                    actualOrdenado.setSiguiente(nodoAInsertar);
                    actual = actual.getSiguiente();
                    continue loop1;
                }

                actualOrdenado = actualOrdenado.getSiguiente();

            }
            actualOrdenado.setSiguiente(nodoAInsertar);
            actual = actual.getSiguiente();
        }

        StringBuilder sb = new StringBuilder();
        actualOrdenado = listaOrdenada.getPrimero();

        while (actualOrdenado != null) {
            sb.append(actualOrdenado.getDato().getNombre() + " - STOCK: " + actualOrdenado.getDato().getStock() + "\n");
            actualOrdenado = actualOrdenado.getSiguiente();
        }

        System.out.println(sb.toString());
    }

    @Override
    public long obtenerValorStock() {

        long result = 0;
        Nodo<IProducto> actual = this.listaDeProductos.getPrimero();

        while (actual != null) {
            result += actual.getDato().getPrecio().longValue() * actual.getDato().getStock().longValue();
            actual = actual.getSiguiente();
        }

        return result;
    }

    @Override
    public Integer restarStock(Comparable codProducto, Integer cantidad) {
        IProducto p = this.buscarPorCodigo(codProducto);
        p.restarCantidadStock(cantidad.intValue());
        return p.getStock();
    }

    @Override
    public void setDireccion(String direccion) {
        this.direccion = direccion;

    }

    @Override
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
