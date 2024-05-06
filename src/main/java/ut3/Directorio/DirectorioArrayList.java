package ut3.Directorio;

import java.util.ArrayList;

import ut3.utils.ManejadorArchivosGenerico;


public class DirectorioArrayList {
    private ArrayList<String> sucursales;

    public DirectorioArrayList() {
        this.sucursales = new ArrayList<>();
    }

    public void agregarSucursal(String ciudad) {
        sucursales.add(ciudad);
    }

    public boolean buscarSucursal(String ciudad) {
        return sucursales.contains(ciudad);
    }

    public boolean quitarSucursal(String ciudad) {
        return sucursales.remove(ciudad);
    }

    public void listarSucursales() {
        System.out.println("Lista de Sucursales:");
        for (String sucursal : sucursales) {
            System.out.println(sucursal);
        }
    }

    public int cantidadSucursales() {
        return sucursales.size();
    }

    public boolean directorioVacio() {
        return sucursales.isEmpty();
    }

    public static void main(String[] args) {
        DirectorioArrayList directorio = new DirectorioArrayList();

        String[] archivo = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\ut3\\Directorio\\suc3.txt");

        for (String string : archivo) {
            directorio.agregarSucursal(string);
        }

        System.out.println("Cantidad de sucursales: " + directorio.cantidadSucursales());
        directorio.listarSucursales();

        System.out.println("¿La sucursal 'Tulsa' está en el directorio? " + directorio.buscarSucursal("Tulsa"));

        directorio.quitarSucursal("Caracas");
        System.out.println("Cantidad de sucursales después de quitar 'Caracas': " + directorio.cantidadSucursales());
        directorio.listarSucursales();

        System.out.println("¿El directorio de sucursales está vacío? " + directorio.directorioVacio());
    }
}
