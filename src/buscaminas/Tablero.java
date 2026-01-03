/**
 * 
 */
package buscaminas;

import java.util.Arrays;
import java.util.Random;

public class Tablero {

	private Casilla[][] tablero;
	private int numFilas;
	private int numColumnas;
	private int numMinas;

	public Tablero(int numFilas, int numColumnas, int numMinas) {
		this.numFilas = numFilas;
		this.numColumnas = numColumnas;
		this.numMinas = numMinas;

		tablero = new Casilla[numFilas][numColumnas];

		for (int i = 0; i < numFilas; i++) {
			for (int j = 0; j < numColumnas; j++) {
				tablero[i][j] = new Casilla();
			}
		}
	}

	/**
	 * 
	 */
	public void inicializarTablero() {
		Random aleatorio = new Random();
		int minasColocadas = 0;

		while (minasColocadas < numMinas) {
			int fila = aleatorio.nextInt(numFilas);
			int columna = aleatorio.nextInt(numColumnas);

			if (!tablero[fila][columna].isMina()) {
				tablero[fila][columna].setMina(true);
				tablero[fila][columna].setBlanco(false);
				minasColocadas++;
			}
		}

		for (int i = 0; i < numFilas; i++) {
			for (int j = 0; j < numColumnas; j++) {
				if (!tablero[i][j].isMina()) {
					int contador = contarMinasAlrededor(i, j);
					tablero[i][j].setNumero(contador);
					if (contador == 0) {
						tablero[i][j].setBlanco(true);
					} else {
						tablero[i][j].setBlanco(false);
					}
				}
			}
		}
	}

	private int contarMinasAlrededor(int fila, int columna) {
		int contador = 0;

		for (int i = fila - 1; i <= fila + 1; i++) {
			for (int j = columna - 1; j <= columna + 1; j++) {
				if (i >= 0 && i < numFilas && j >= 0 && j < numColumnas) {
					if (tablero[i][j].isMina()) {
						contador++;
					}
				}
			}
		}
		return contador;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < numFilas; i++) {
			for (int j = 0; j < numColumnas; j++) {
				Casilla casilla = tablero[i][j];

				if (!casilla.isVisible()) {
					builder.append(". ");
				} else if (casilla.isBlanco()) {
					builder.append("  ");
				} else if (casilla.isMina()) {
					builder.append("M ");
				} else if (casilla.isBandera()) {
					builder.append("B ");
				} else {
					builder.append(casilla.getNumero() + " ");
				}
			}
			builder.append("\n");
		}
		return builder.toString();

	}

}
