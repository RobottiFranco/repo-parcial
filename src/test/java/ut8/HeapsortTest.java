package ut8;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ut9.tda.TClasificador;
import ut9.utils.GeneradorDatosGenericos;

public class HeapsortTest {
    @Test
    void testOrdenarPorHeapSort() {
        TClasificador c = new TClasificador();
        int[] datos = GeneradorDatosGenericos.generarDatosAleatorios(50);
        c.ordenarPorHeapSort(datos);
        for (int i = 0; i < datos.length; i++) {
            assertEquals(datos.length - i - 1, datos[i]);
        }
    }
}
