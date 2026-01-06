package buscaminas;

import entrada.Teclado;

public class Juego {
	private Tablero tablero;
	private boolean juegoTerminado;
	

	/**
	 * 
	 * @param filas
	 * @param columnas
	 * @param numMinas tablero inicial del juego
	 */
	public Juego(int filas, int columnas, int numMinas) {
		this.tablero = new Tablero(filas, columnas, numMinas);
		this.tablero.inicializarTablero();
		this.juegoTerminado = false;
	}

	
	/**
	 * 
	 * @param fila
	 * @param col
	 * @return
	 */
	public boolean descubrirCasilla(int fila, int col) {
		if (juegoTerminado)
			return false;

		Casilla casilla = tablero.getTablero()[fila][col];

		if (casilla.isBandera()) {
			System.out.println("No se puede descubrir casilla con bandera");
			return true;
		}

		if (casilla.isMina()) {
			casilla.setVisible(true);
			juegoTerminado = true;
			mostrarMinas();
			System.out.println("Has perdido");
			return false;
		}

		// decubrir casilla
		descubrirBlancoEnCascada(fila, col);

		// comprobar victroria
		if (comprobarVictoria()) {
			juegoTerminado = true;
			System.out.println("Has ganado");
			return false;
		}

		return true; // juego sigue

	}

	/**
	 * 
	 */
	// poner bandera
	public void ponerBandera() {
		Casilla casilla = tablero.getTablero()[fila][col];
		if (casilla.isVisible()) {
			System.out.println("No se puede poner bandera en casilla visible");
			return;
		}
		casilla.setBandera(true);
	}

	/**
	 * 
	 * @param fila
	 * @param col
	 */
	// quitar bandera
	public void quitarBandera(int fila, int col) {
		Casilla casilla = tablero.getTablero()[fila][col];
		casilla.setBandera(false);
	}
	
	/**
	 * 
	 * @param fila
	 * @param col
	 */
	// descubrir casillas en cascada :(
	private void descubrirBlancoCascada(int fila, int col) {
		Casilla casilla = tablero.getTablero()[fila][col] {
			if (casilla.isVisible() || casilla.isBandera()) return;
			
			if (casilla.isBlanco()) {
				for (int i = fila - 1; i <= fila + 1; i++) {
                    if (i >= 0 && i < tablero.getNumFilas() && j >= 0 && j < tablero.getNumColumnas()) {
                        descubrirBlancoEnCascada(i, j);

                    }
				}
			}
		}
		
	}

	/**
	 * 
	 */
	private void mostrarMinas() {
		for (int i = 0; i < tablero.getNumFilas(); i++) {
			for (int j = 0; j < tablero.getNumColumnas(); j++) {
				Casilla c = tablero.getTablero()[i][j];
				if (c.isMina()) {
					c.setVisible(true);
				}
			}
		}
		System.out.println(tablero.toString());
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean comprobarVictoria() {
		for (int i = 0; i < tablero.getNumFilas(); i++) {
			for (int j = 0; j < tablero.getNumColumnas(); j++) {
				Casilla casilla = tablero.getTablero()[i][j];
				if (!casilla.isMina() && !c.isVisible()) {
					return false;
				}
			}
				
		}
		return true;
	}

	@Override
	public String toString() {
		return tablero.toString();
	}

	/**
	 * 
	 * @return
	 */
	// getter del tablero por si se necesita en principal
	public Tablero getTablero() {
		return tablero;
	}

}
