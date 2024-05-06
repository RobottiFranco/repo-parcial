package ut3.Directorio;

import ut3.TDA.Lista;
import ut3.TDA.Nodo;
import ut3.utils.ManejadorArchivosGenerico;

public class DirectorioLista {

    private Lista<String> sucursales = new Lista<String>();

    public void agregar(String sucursal) {
        this.sucursales.insertar(new Nodo<String>(sucursal, sucursal));
    }

    public boolean quitarSucursal(Comparable sucursal) {
        return this.sucursales.eliminar(sucursal);
    }

    public Nodo<String> buscar(Comparable sucursal) {
        return this.sucursales.buscar(sucursal);
    }

    public String litarSucursales() {
        return this.sucursales.imprimir();
    }

    public String litarSucursales(String separador) {
        return this.sucursales.imprimir(separador);
    }

    public int cantidadDeSucursales() {
        return this.sucursales.cantElementos();
    }

    public boolean estaVacio() {
        return this.sucursales.esVacia();
    }

    public static void main(String[] args) {

        DirectorioLista d = new DirectorioLista();
        String[] archivo = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\ut3\\Directorio\\suc2.txt");

        for (String string : archivo) {
            d.agregar(string);
        }

        d.quitarSucursal("Shenzhen");
        d.quitarSucursal("Tokio");

        // d.quitarSucursal("Chicago");
        System.out.println(d.litarSucursales());
        System.out.println(d.sucursales.imprimirAlReves());
        System.out.println(d.sucursales.imprimirAlReves(" - "));

        // System.out.println(d.litarSucursales(";_"));
        // System.out.println(d.cantidadDeSucursales());
    }

}
