/**
 * build path roto
 */
package buscaminas;

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
	 * Metodo inicializar tablero, con minas aleatorias y llamando a metodo para
	 * contar minas adyacentes
	 */
	public void inicializarTablero() {
		Random aleatorio = new Random();
		int minasColocadas = 0;

		while (minasColocadas < numMinas) {
			int fila = aleatorio.nextInt(numFilas);
			int columna = aleatorio.nextInt(numColumnas);

			if (!tablero[fila][columna].isMina()) {
				tablero[fila][columna].setMina(true);
				minasColocadas++;
			}
		}

		for (int i = 0; i < numFilas; i++) {
			for (int j = 0; j < numColumnas; j++) {
				if (!tablero[i][j].isMina()) {
                    tablero[i][j].setNumero(contarMinasAlrededor(i, j));
				}
			}
		}
	}

	/**
	 * @param fila
	 * @param columna metodo para contar minas adyacentes de cada casilla
	 * @return
	 */
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
	
	public Casilla[][] getTablero() {
        return tablero;
    }

    public int getNumFilas() {
        return numFilas;
    }

    public int getNumColumnas() {
        return numColumnas;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        // Encabezado de columnas
        builder.append("   "); 
        for (int j = 0; j < numColumnas; j++) {
            builder.append(String.format("%2d ", j));
        }
        builder.append("\n");

        // Línea separadora
        builder.append("   ");
        for (int j = 0; j < numColumnas; j++) {
            builder.append("---");
        }
        builder.append("\n");

        // Filas con numeración
        for (int i = 0; i < numFilas; i++) {
            builder.append(String.format("%2d|", i));

            for (int j = 0; j < numColumnas; j++) {
                Casilla casilla = tablero[i][j];
                String simbolo;

                if (casilla.isBandera()) {
                    simbolo = "B";
                } else if (!casilla.isVisible()) {
                    simbolo = ".";
                } else if (casilla.isMina()) {
                    simbolo = "M";
                } else if (casilla.isBlanco()) {
                    simbolo = " ";
                } else {
                    simbolo = String.valueOf(casilla.getNumero());
                }

                builder.append(String.format(" %s ", simbolo));
            }
            builder.append("\n");
        }

        return builder.toString();
    }

}