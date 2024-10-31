package algoritmosVoraces;

/**
 * La clase Ejercicio22 simula la entrega de billetes en un cajero automático
 * utilizando un algoritmo voraz. Se basa en un conjunto de denominaciones de
 * billetes y la cantidad disponible de cada uno, con el objetivo de entregar
 * una suma específica de dinero en la menor cantidad de billetes posible.
 */
public class Ejercicio22 {

	// Arreglo que contiene las denominaciones de los billetes disponibles.
	public static int[] denominacion = { 10000, 20000, 50000, 100000 };

	// Arreglo que contiene la cantidad de billetes disponibles para cada
	// denominación.
	public static int[] cantidadDenominacion = { 300, 200, 100, 50 };

	/**
	 * Método principal que se ejecuta al iniciar la aplicación. Llama al método
	 * entregarBilletes para intentar entregar una cantidad específica de dinero y
	 * muestra el resultado en la consola.
	 *
	 * @param args Argumentos de línea de comandos (no se utilizan en este ejemplo).
	 */
	public static void main(String[] args) {
		int[] cantidadEntregada = entregarBilletes(2000000);
		if (cantidadEntregada != null) {
			for (int i = 0; i < cantidadEntregada.length; i++) {
				System.out.println("La cantidad de billetes de " + denominacion[i] + " entregados fueron: "
						+ cantidadEntregada[i]);
			}
			for (int i = 0; i < cantidadDenominacion.length; i++) {
				System.out.println(
						"La cantidad de billetes restantes de " + denominacion[i] + ": " + cantidadDenominacion[i]);
			}
		} else {
			System.out.println("El dinero solo se puede entregar en cantidades divisibles por 10000");
		}
	}

	/**
	 * Intenta entregar una cantidad específica de dinero en billetes, utilizando un
	 * enfoque voraz. Se verifica si la cantidad a entregar es divisible por 10000 y
	 * se procede a entregar los billetes de mayor denominación primero, hasta que
	 * se alcance la cantidad deseada o se agoten las denominaciones disponibles.
	 *
	 * @param cantidadBilletes La cantidad de dinero que se desea entregar.
	 * @return Un arreglo que indica la cantidad de cada denominación entregada, o
	 *         null si no es posible entregar la cantidad solicitada.
	 */
	public static int[] entregarBilletes(int cantidadBilletes) {
		int[] dineroEntregado = new int[denominacion.length];

		// Verifica que la cantidad solicitada sea divisible por 10000.
		if (cantidadBilletes % 10000 == 0) {
			for (int i = 0; i < denominacion.length; i++) {
				// Calcular la cantidad necesaria de billetes de la denominación actual.
				int cantidadNecesaria = cantidadBilletes / denominacion[i];

				// Si hay menos billetes disponibles de los necesarios, se ajusta la cantidad.
				if (cantidadNecesaria > cantidadDenominacion[i]) {
					cantidadNecesaria = cantidadDenominacion[i];
				}

				// Actualiza la cantidad de dinero que queda por entregar.
				cantidadBilletes -= cantidadNecesaria * denominacion[i];

				// Actualiza la cantidad de billetes restantes de la denominación.
				cantidadDenominacion[i] -= cantidadNecesaria;

				// Guarda la cantidad de billetes entregados.
				dineroEntregado[i] = cantidadNecesaria;
			}
			// Si aún queda dinero por entregar, retorna null.
			if (cantidadBilletes > 0) {
				return null;
			}
			return dineroEntregado; // Retorna la cantidad de billetes entregados.
		} else {
			return null; // Retorna null si la cantidad no es divisible por 10000.
		}
	}
}

/**
 * Complejidad: La complejidad de este algoritmo es O(m), donde m es el número
 * de diferentes denominaciones de billetes disponibles. En el peor de los
 * casos, el algoritmo iterará a través de todas las denominaciones para
 * calcular la cantidad de billetes a entregar.
 */
