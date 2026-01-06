package buscaminas;

import entrada.Teclado;

public class Buscaminas {
	public static void main(String[] args) {

		Buscaminas app = new Buscaminas();

		int filas = app.pedirFilasTablero();
		int columnas = app.pedirColumnasTablero();
		int minas = app.pedirMinasTablero(filas, columnas);

		Juego juego = new Juego(filas, columnas, minas);

		System.out.println(juego);

		boolean salir = false;

		do {
			System.out.println(juego);
			
			System.out.println("MENÚ OPCIONES:");
			System.out.println("1. Descubrir ");
			System.out.println("2. Poner bandera");
			System.out.println("3. Quitar bandera");
			System.out.println("0. Salir");
			int opcion = Teclado.leerEntero("Seleccione opcion(0-3)");

			if (opcion == 0) {
				System.out.println("Saliendo...");
				salir = true;
			} else {

				int fila = Teclado.leerEntero("Indica la fila");

				int columna = Teclado.leerEntero("Indica la columna");

				switch (opcion) {

				case 1:

					boolean bomba = juego.descubrirCasilla(fila, columna);
					if (bomba) {
						System.out.println(juego);
						System.out.println("!Has perdido!");
						salir = true;
					}

					break;
				case 2:
					juego.ponerBandera(fila, columna);
					break;
				case 3:
					juego.quitarBandera(fila, columna);
					break;

				default:
					System.out.println("La opcion debe estar entre 0 y 3");
					break;

				}
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
