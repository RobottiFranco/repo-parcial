package ut4.main;

import ut4.TDA.Almacen;
import ut4.TDA.Producto;
import ut4.utils.ManejadorArchivosGenerico;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Almacen almacen = new Almacen("Julio");
        String[] archivo = ManejadorArchivosGenerico.leerArchivo("src\\test\\java\\ut4\\utils\\altasPrueba.txt");
        String[] linea;
        Producto nuevoProducto;
        for (String string : archivo) {
            linea = string.split(",");
            if (almacen.buscarPorCodigo(linea[0]) == null) {
                nuevoProducto = new Producto(linea[0], linea[1]);
                nuevoProducto.setPrecio(Integer.parseInt(linea[2]));
                nuevoProducto.setStock(Integer.parseInt(linea[3]));
                almacen.insertarProducto(nuevoProducto);
            } else {
                almacen.buscarPorCodigo(linea[0]).agergarStock(Integer.parseInt(linea[3]));
            }
        }

        System.out.println("existe el elemento con id : 1000073?");
        System.out.println(almacen.buscarPorCodigo("1000073") != null);

        /* stock */
        System.out.println("stock1");
        System.out.println(almacen.buscarPorCodigo("1000073").getStock());

        almacen.buscarPorCodigo("1000073").agergarStock(1000);

        System.out.println("stock2");
        System.out.println(almacen.buscarPorCodigo("1000073").getStock());

        almacen.buscarPorCodigo("1000073").restarStock(1000);

        System.out.println("stock3");
        System.out.println(almacen.buscarPorCodigo("1000073").getStock());

        /* lista productos */
        System.out.println(almacen.imprimirProductos());

        /* System.out.println(almacen.obtenerValorTotal()); */

        /* reducir stock */

        System.out.println("stock sin reducir de 1000087");
        System.out.println(almacen.buscarPorCodigo("1000087").getStock());

        archivo = ManejadorArchivosGenerico.leerArchivo("src\\test\\java\\ut4\\utils\\ventasPrueba.txt");
        for (String string : archivo) {
            linea = string.split(",");
            if (almacen.buscarPorCodigo(linea[0]) != null) {
                almacen.buscarPorCodigo(linea[0]).restarStock(Integer.parseInt(linea[1]));
            }
        }

        System.out.println("stock reducido de 1000087");
        System.out.println(almacen.buscarPorCodigo("1000087").getStock());

        /* eliminar */
        archivo = ManejadorArchivosGenerico.leerArchivo("src\\test\\java\\ut4\\utils\\elimPrueba.txt");
        for (String string : archivo) {
            if (almacen.buscarPorCodigo(string) != null) {
                System.out.println(string +" eliminado:");
                System.out.println(almacen.eliminarProducto(string));
            }
        }

        /* no se elimina correctamente */
        System.out.println("despues de eliminacion:");
        System.out.println(almacen.imprimirProductos());

    }
    // cargar los productos desde el archivo "altasprueba.txt"
    // listar los productos ordenados por codigo, junto con su cantidad existente
    // emitir el valor del stock
    // simular las ventas a partir del archivo "ventaspruebas.txt"
    // simular la eliminación de productos a partir del archivo "elimPrueba.txt"
    // listar los productos ordenados por codigo, junto con su cantidad existente

}
