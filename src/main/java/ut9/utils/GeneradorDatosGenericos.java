package ut9.utils;

import java.util.Random;

public class GeneradorDatosGenericos {

	private static int TAMANIO_MAX = 3200;

	public static int[] generarDatosAleatorios() {
		Random rnd = new Random();
		int[] datosGenerados = new int[TAMANIO_MAX];
		boolean[] datosUtilizados = new boolean[TAMANIO_MAX];
		for (int i = 0; i < datosGenerados.length; i++) {
			int j = rnd.nextInt(TAMANIO_MAX);
			while(datosUtilizados[j]){
				j = (j + 1) % TAMANIO_MAX;
			}
			datosGenerados[j] = i;
			datosUtilizados[j] = true;
		}
		return datosGenerados;
	}

	public static int[] generarDatosAleatorios(int x) {
		int aux = TAMANIO_MAX;
		TAMANIO_MAX = x;
		int[] result = generarDatosAleatorios();
		TAMANIO_MAX = aux;
		return result;
	}

	public static int[] generarDatosAscendentes() {
		int [] copiaAscendente = new int[TAMANIO_MAX];
		for (int i = 0; i < TAMANIO_MAX; i++) {
			copiaAscendente[i] = i;
		}
		return copiaAscendente;
	}

	public static int[] generarDatosDescendentes() {
		int [] copiaDescendente = new int[TAMANIO_MAX];
		for (int i = 0; i < TAMANIO_MAX; i++) {
			copiaDescendente[i] = TAMANIO_MAX - i;
		}
		return copiaDescendente;
	}

}
