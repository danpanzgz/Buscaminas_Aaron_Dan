package buscaminas;

import entrada.Teclado;

public class Buscaminas {
	public static void main(String[] args) {

		Buscaminas app = new Buscaminas();

		int filas = app.pedirFilasTablero();
		int columnas = app.pedirColumnasTablero();
		int minas = app.pedirMinasTablero(filas, columnas);

		Juego juego = new Juego(filas, columnas, minas);

		boolean salir = false;

		do {
			System.out.println(juego);

			System.out.println("MENÚ OPCIONES:");
			System.out.println("1. Descubrir ");
			System.out.println("2. Poner bandera");
			System.out.println("3. Quitar bandera");
			System.out.println("0. Salir");

			int opcion = Teclado.leerEntero("Seleccione opcion(0-3): ");

			if (opcion == 0) {
				System.out.println("Saliendo...");
				break;
			}

			int fila;
			int columna;

			// validar fila y columna
			do {
				fila = Teclado.leerEntero("Indica la fila (0-" + (filas - 1) + "): ");
			} while (fila < 0 || fila >= filas);

			do {
				columna = Teclado.leerEntero("Indica la columna (0-" + (columnas - 1) + "): ");
			} while (columna < 0 || columna >= columnas);

			switch (opcion) {
			case 1:
				juego.descubrirCasilla(fila, columna);

				if (juego.isJuegoTerminado()) {
				    System.out.println(juego);

				    if (juego.hasGanado()) {
				        System.out.println("¡Has ganado!");
				    } else {
				        System.out.println("¡Has perdido!");
				    }

				    salir = true;
				}
				break;

			case 2:
				if (juego.ponerBandera(fila, columna)) {
					System.out.println("Bandera colocada en (" + fila + "," + columna + ")");
				} else {
					System.out.println("No se puede poner bandera en esa casilla.");
				}
				break;

			case 3:
				 if (juego.quitarBandera(fila, columna)) {
                     System.out.println("Bandera quitada en (" + fila + "," + columna + ")");
                 } else {
                     System.out.println("No hay bandera en esa casilla.");
                 }
                 break;

			default:
				System.out.println("La opcion debe estar entre 0 y 3");
				break;

			}
		} while (!salir);

	}

	/**
	 * pedir filas para crear tablero.
	 * 
	 * @return filasTablero;
	 */
	public int pedirFilasTablero() {
		int filasTablero;
		do {
			filasTablero = Teclado.leerEntero("¿Cuántas filas tendra el tablero?");
		} while (filasTablero <= 0);

		return filasTablero;
	}

	/**
	 * pedir columnas para crear tablero.
	 * 
	 * @return columnasTablero
	 */
	public int pedirColumnasTablero() {
		int columnasTablero;
		do {
			columnasTablero = Teclado.leerEntero("¿Cuántas columnas tendra el tablero?");
		} while (columnasTablero <= 0);

		return columnasTablero;
	}

	/**
	 * pedir minas para crear tablero, validando que sea menor el numero de minas
	 * que de casillas.
	 * 
	 * @param filas
	 * @param columnas
	 * @return
	 */
	public int pedirMinasTablero(int filas, int columnas) {
		int minasTablero;
		do {
			minasTablero = Teclado.leerEntero("¿Cuántas minas tendrá el tablero?");
		} while (minasTablero <= 0 || minasTablero >= filas * columnas);

		return minasTablero;
	}
}
