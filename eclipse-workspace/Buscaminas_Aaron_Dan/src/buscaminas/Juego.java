package buscaminas;

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
		tablero = new Tablero(filas, columnas, numMinas);
		tablero.inicializarTablero();
		juegoTerminado = false;
	}

	/**
	 * 
	 * @param fila
	 * @param col
	 * @return
	 */
	public void descubrirCasilla(int fila, int col) {
		if (juegoTerminado) return;

		Casilla casilla = tablero.getTablero()[fila][col];

		if (casilla.isBandera() || casilla.isVisible()) return;

		if (casilla.isMina()) {
            mostrarMinas();
			juegoTerminado = true;
			return;
		}

		// decubrir casilla en cascada
		descubrirEnCascada(fila, col);

		// comprobar victroria
		if (comprobarVictoria()) {
			juegoTerminado = true;
		}

		return;
	}

	/**
	 * 
	 * @param fila
	 * @param col
	 * @return
	 */
	// poner bandera
	public boolean ponerBandera(int fila, int col) {
		Casilla c = tablero.getTablero()[fila][col];
		if (c.isVisible() || c.isBandera())
			return false;
		c.setBandera(true);
		return true;
	}

	/**
	 * 
	 * @param fila
	 * @param col
	 * @return
	 */
	// quitar bandera
	public boolean quitarBandera(int fila, int col) {
		Casilla c = tablero.getTablero()[fila][col];
		if (!c.isBandera())
			return false;
		c.setBandera(false);
		return true;
	}

	/**
	 * 
	 * @param fila
	 * @param col
	 */
	// descubrir casillas en cascada :(
	private void descubrirEnCascada(int fila, int col) {
		if (fila < 0 || fila >= tablero.getNumFilas() || col < 0 || col >= tablero.getNumColumnas())
			return;

		Casilla casilla = tablero.getTablero()[fila][col];

		if (casilla.isVisible() || casilla.isBandera())
			return;

		casilla.setVisible(true);

		if (!casilla.isBlanco())
			return;

		for (int i = fila - 1; i <= fila + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
					descubrirEnCascada(i, j);
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	boolean comprobarVictoria() {
		for (int i = 0; i < tablero.getNumFilas(); i++) {
			for (int j = 0; j < tablero.getNumColumnas(); j++) {
				Casilla casilla = tablero.getTablero()[i][j];
				if (!casilla.isMina() && !casilla.isVisible()) {
					return false;
				}
			}

		}
		return true;
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
    }
	
	/**
	 * 
	 * @return
	 */
	public boolean isJuegoTerminado() {
	    return juegoTerminado;
	}

	
	/**
	 * 
	 * @return
	 */
	public boolean hasGanado() {
	    return comprobarVictoria();
	}

	@Override
	public String toString() {
		return tablero.toString();
	}

	/**
	 * s
	 * 
	 * @return
	 */
	// getter del tablero por si se necesita en principal
	public Tablero getTablero() {
		return tablero;
	}

}
