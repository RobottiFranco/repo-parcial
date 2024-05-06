package ut4.TDA;

public class Almacen implements IAlmacen {

    private String nombre;
    private String direccion;
    private String telefono;

    private TArbolBB<Producto> productos;

    public Almacen(String nombre) {
        this.nombre = nombre;
        this.productos = new TArbolBB<Producto>();
    }

    @Override
    public void insertarProducto(Producto unProducto) {
        this.productos.insertar(new TElementoAB<Producto>(unProducto.getEtiqueta(), unProducto));
    }

    @Override
    public String imprimirProductos() {
        return this.productos.inOrden();
    }

    @Override
    public Boolean agregarStock(Comparable clave, Integer cantidad) {
        if (this.productos.buscar(clave) != null) {
            this.productos.buscar(clave).getDatos().agergarStock(cantidad);
            return true;
        }
        return false;
    }

    public double obtenerValorTotal() {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
    }

    @Override
    public Integer restarStock(Comparable clave, Integer cantidad) {
        if (this.productos.buscar(clave) != null) {
            this.productos.buscar(clave).getDatos().restarStock(cantidad);
            return cantidad;
        }
        return 0;
    }

    @Override
    public Producto buscarPorCodigo(Comparable clave) {

        if (this.productos.buscar(clave) != null) {
            return this.productos.buscar(clave).getDatos();
        }
        return null;
    }

    @Override
    public boolean eliminarProducto(Comparable clave) {
        if (this.productos.buscar(clave) != null) {
            this.productos.eliminar(clave);
            return true;
        }
        return false;
    }

}
