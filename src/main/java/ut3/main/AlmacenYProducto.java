package ut3.main;

import ut3.TDA.Almacen;
import ut3.TDA.Producto;
import ut3.utils.ManejadorArchivosGenerico;

/**
 *
 * @author abadie
 */
public class AlmacenYProducto {

    public static void main(String[] args) {
        // TODO:
        /**
         * Instanciar almacen
         * Agregar: productos y cantidades (altas.txt)
         * Emitir listado de productos y cantidades
         * Emitir valor de stock de todo el almacen
         * Vender: restar stock de productos indicado en ventas.txt
         * Emitir valor de stock de todo el almacen
         **/

        Almacen almacen = new Almacen(null, null, null);

        long valorPreAlta = almacen.obtenerValorStock();
        System.out.println("El valor del stock del almacen antes del alta es: " + valorPreAlta);

        String[] altas = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\ut3\\main\\altas.txt");

        String[] linea;
        Producto p;
        long aumentoValorStock = 0;

        for (String producto : altas) {
            linea = producto.split(",");
            p = new Producto(linea[0], linea[1], Integer.parseInt(linea[2]), Integer.parseInt(linea[3]));
            almacen.insertarProducto(p);
            aumentoValorStock += (Long.parseLong(linea[2]) * Long.parseLong(linea[3]));
        }

        long valorPostAlta = almacen.obtenerValorStock();
        System.out.println("Aumento del valor de stock: " + (valorPostAlta - valorPreAlta));
        System.out.println("Aumento del valor de stock: " + aumentoValorStock);

        String[] ventas = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\ut3\\main\\ventas.txt");
        long disminucionValorStock = 0;
        int stockVendido = 0;

        for (String producto : ventas) {
            linea = producto.split(",");

            stockVendido = almacen.buscarPorCodigo(linea[0]).getStock();

            if (almacen.restarStock(linea[0], Integer.parseInt(linea[1])) == 0) {
                disminucionValorStock += almacen.buscarPorCodigo(linea[0]).getPrecio() * stockVendido;
            } else {
                disminucionValorStock += almacen.buscarPorCodigo(linea[0]).getPrecio() * Integer.parseInt(linea[1]);
            }
        }

        long valorPostVenta = almacen.obtenerValorStock();
        System.out.println("Disminucion del valor de stock: " + (valorPostAlta - valorPostVenta));
        System.out.println("Disminucion del valor de stock: " + disminucionValorStock);

        almacen.listarOrdenadoPorNombre2();

    }

}
