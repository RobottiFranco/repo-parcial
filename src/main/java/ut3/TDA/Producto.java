package ut3.TDA;

public class Producto implements IProducto {

    private final Comparable codProducto;
    private int precio;
    private int stock;
    private String nombre;

    public Producto(Comparable codProducto, String nombre, int precio, int stock) {
        this.codProducto = codProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    @Override
    public Comparable getCodProducto() {
        return this.codProducto;
    }

    @Override
    public Integer getPrecio() {
        return this.precio;
    }

    @Override
    public void setPrecio(Integer precio) {
        this.precio = precio.intValue();
    }

    @Override
    public Integer getStock() {
        return this.stock;
    }

    @Override
    public void agregarCantidadStock(Integer stock) {
        this.stock += stock.intValue();
    }

    @Override
    public void restarCantidadStock(Integer stock) {
        if (this.stock <= stock.intValue()) {
            this.stock = 0;
        } else {
            this.stock -= stock;
        }
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
