package ut9.tda;

import java.util.Random;

public class TClasificador {
	public static final int METODO_CLASIFICACION_INSERCION = 1;
	public static final int METODO_CLASIFICACION_SHELL = 2;
	public static final int METODO_CLASIFICACION_BURBUJA = 3;
	public static final int METODO_CLASIFICACION_QUICKSORT = 4;

	/**
	 * Punto de entrada al clasificador
	 *
	 * @param metodoClasificacion
	 * @param orden
	 * @param tamanioVector
	 * @return Un vector del tam. solicitado, ordenado por el algoritmo solicitado
	 */
	public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
		switch (metodoClasificacion) {
			case METODO_CLASIFICACION_INSERCION:
				return ordenarPorInsercion(datosParaClasificar);
			case METODO_CLASIFICACION_SHELL:
				return ordenarPorShell(datosParaClasificar);
			case METODO_CLASIFICACION_BURBUJA:
				return ordenarPorBurbuja(datosParaClasificar);
			case METODO_CLASIFICACION_QUICKSORT:
				return ordenarPorQuickSort(datosParaClasificar);
			default:
				System.err.println("Este codigo no deberia haberse ejecutado");
				break;
		}
		return datosParaClasificar;
	}

	private void intercambiar(int[] vector, int pos1, int pos2) {
		int temp = vector[pos2];
		vector[pos2] = vector[pos1];
		vector[pos1] = temp;
	}

	/**
	 * @param datosParaClasificar
	 * @return
	 */
	private int[] ordenarPorShell(int[] datosParaClasificar) {
		int j, inc;
		int[] incrementos = new int[] { 3223, 358, 51, 10, 3, 1 };

		// for (int posIncrementoActual = 1; posIncrementoActual < incrementos.length;
		// posIncrementoActual++) { debe empezar en 0 y no en 1
		for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
			inc = incrementos[posIncrementoActual];
			if (inc < (datosParaClasificar.length / 2)) {
				for (int i = inc; i < datosParaClasificar.length; i++) {
					j = i - inc;
					// while (j >= 0) {
					// if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
					while (j >= 0 && datosParaClasificar[j] > datosParaClasificar[j + inc]) {
						intercambiar(datosParaClasificar, j, j + inc);
						// j = j--;
						j = j - inc;
					}
				}
			}

		}
		return datosParaClasificar;
	}

	/**
	 * @param datosParaClasificar
	 * @return
	 */
	protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
		if (datosParaClasificar != null) {
			// for (int i = 2; i < datosParaClasificar.length; i++) { i no debe empezar en 1
			// sino en 2
			for (int i = 1; i < datosParaClasificar.length; i++) {
				int j = i - 1;
				// while ((j >= 0) && (datosParaClasificar[j + 1] > datosParaClasificar[j])) { <
				// ordena decreciente
				while ((j >= 0) && (datosParaClasificar[j + 1] < datosParaClasificar[j])) {
					intercambiar(datosParaClasificar, j, j + 1);
					j--;
				}
			}
			return datosParaClasificar;
		}
		return null;
	}

	private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
		// datosParaClasificar = null;
		// No se debe igualar a null porque se pierden los datos
		int n = datosParaClasificar.length - 1;
		// for (int i = 0; i <= n; i++) { i no va hasta n sino n-1
		for (int i = 0; i < n; i++) {
			for (int j = n; j >= (i + 1); j--) {
				if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
					intercambiar(datosParaClasificar, j - 1, j);
				}
			}
		}
		return datosParaClasificar;
	}

	public void imprimirVector(int[] vector) {
		for (int i = 0; i < vector.length; i++) {
			System.out.print(vector[i] + " ");
		}
	}

	protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
		int[] prof = { 0 };
		quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1, prof);
		System.out.println(prof[0]);
		return datosParaClasificar;
	}

	private void quicksort(int[] entrada, int i, int j, int[] profundidad) {
		int izquierda = i;
		int derecha = j;

		int posicionPivote = encuentraPivote(izquierda, derecha, entrada);
		if (posicionPivote >= 0) {
			// int pivote = posicionPivote;
			int pivote = entrada[posicionPivote];
			while (izquierda <= derecha) {
				while ((entrada[izquierda] < pivote) && (izquierda < j)) {
					// izquierda--;
					izquierda++;
				}

				// while ((pivote < entrada[derecha]) && (derecha > i)) {
				while ((pivote <= entrada[derecha]) && (derecha > i)) {
					// derecha++;
					derecha--;
				}

				if (izquierda <= derecha) {
					intercambiar(entrada, derecha, izquierda);
					izquierda++;
					derecha--;
				}
			}

			boolean flag = false;
			if (i < derecha) {
				quicksort(entrada, i, izquierda, profundidad);
				flag = true;
			}
			if (izquierda < j) {
				// quicksort(entrada, derecha, j);
				quicksort(entrada, izquierda++, j, profundidad);
				flag = true;
			}
			if (flag) {
				profundidad[0]++;
			}
		}
	}

	// Elemento aleatorio
	public int encuentraPivote(int izq, int der, int[] entrada) {
		Random r = new Random();
		return r.nextInt(der - izq + 1) + izq;
	}

	public int[] ordenarPorHeapSort(int[] datosParaClasificar) {
		for (int i = (datosParaClasificar.length - 1) / 2; i >= 0; i--) { // Armo el heap inicial de n-1 div 2 hasta 0
			armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
		}
		for (int i = datosParaClasificar.length - 1; i >= 1; i--) {
			intercambiar(datosParaClasificar, 0, i);
			armaHeap(datosParaClasificar, 0, i - 1);
		}
		return datosParaClasificar;
	}

	private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
		if (primero < ultimo) {
			int r = primero;
			while (r <= ultimo / 2) {
				if (ultimo == 2 * r) { // r tiene un hijo solo
					if (datosParaClasificar[r] > datosParaClasificar[2 * r]) {
						intercambiar(datosParaClasificar, r, 2 * r);
					}
					r = ultimo;
				} else { // r tiene 2 hijos
					int posicionIntercambio = 0;
					if (datosParaClasificar[2 * r] > datosParaClasificar[2 * r + 1]) {
						posicionIntercambio = 2 * r + 1;
					} else {
						posicionIntercambio = 2 * r;
					}
					if (datosParaClasificar[r] > datosParaClasificar[posicionIntercambio]) {
						intercambiar(datosParaClasificar, r, posicionIntercambio);
						r = posicionIntercambio;
					} else {
						r = ultimo;
					}
				}
			}
		}
	}



	public boolean verificarOrdenAscendente(int[] datos) {
		if (datos.length == 1 || datos.length == 0) {
			return true;
		}
		for (int i = 0; i < datos.length - 1; i++) {
			if (datos[i] > datos[i + 1]) {
				return false;
			}
		}
		return true;
	}

	public boolean verificarOrdenDescendente(int[] datos) {
		if (datos.length == 1 || datos.length == 0) {
			return true;
		}
		for (int i = 0; i < datos.length - 1; i++) {
			if (datos[i] < datos[i + 1]) {
				return false;
			}
		}
		return true;
	}

}
