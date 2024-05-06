package ut3.Directorio;

import java.util.LinkedList;

import ut3.utils.ManejadorArchivosGenerico;

public class DirectorioLinkedList {

    private LinkedList<String> sucursales = new LinkedList<>();

    public void agregar(String sucursal) {
        this.sucursales.addLast(sucursal);
    }

    public boolean quitarSucursal(Comparable sucursal) {
        return this.sucursales.remove(sucursal);
    }

    public String buscar(Comparable sucursal) {
        if (this.sucursales.indexOf(sucursal) < 0) {
            return null;
        }
        return sucursal.toString();
    }

    public String litarSucursales() {
        StringBuilder sb = new StringBuilder();

        for (String string : sucursales) {
            sb.append(string + "\n");
        }
        return sb.toString();
    }

    public String litarSucursales(String separador) {
        StringBuilder sb = new StringBuilder();

        for (String string : sucursales) {
            sb.append(string + separador);
        }
        return sb.toString();
    }

    public int cantidadDeSucursales() {
        return this.sucursales.size();
    }

    public boolean estaVacio() {
        return this.sucursales.isEmpty();
    }

    public static void main(String[] args) {

        DirectorioLinkedList d = new DirectorioLinkedList();
        String[] archivo = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\ut3\\Directorio\\suc2.txt");

        for (String string : archivo) {
            d.agregar(string);
        }

/*         d.quitarSucursal("Shenzhen"); */
/*         d.quitarSucursal("Tokio"); */
        System.out.println(d.litarSucursales());
        System.out.println(d.cantidadDeSucursales());

        System.out.println(d.litarSucursales(";_"));
        System.out.println(d.cantidadDeSucursales());



    }
}
