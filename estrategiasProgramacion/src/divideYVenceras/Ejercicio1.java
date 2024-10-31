package divideYVenceras;

/**
 * La clase Ejercicio1 contiene un método para calcular la suma total de los
 * elementos de un arreglo de enteros utilizando un enfoque recursivo basado en
 * la técnica de divide y vencerás.
 */
public class Ejercicio1 {

	/**
	 * El método principal que se ejecuta al iniciar la aplicación. Se inicializa un
	 * arreglo de enteros y se llama al método de suma recursiva, imprimiendo el
	 * resultado en la consola.
	 *
	 * @param args Argumentos de línea de comandos (no se utilizan en este ejemplo).
	 */
	public static void main(String[] args) {
		int[] arr = { 8, 4, 12, 23, 89, 2, 7, 6 };
		System.out.println(sumaTotalDivisionRecursiva(arr, 0, arr.length - 1));
	}

	/**
	 * Calcula la suma de los elementos de un arreglo de enteros utilizando un
	 * enfoque recursivo. Este método divide el arreglo en dos mitades hasta que
	 * alcanza la base de la recursión.
	 *
	 * @param arr      El arreglo de enteros.
	 * @param indMenor El índice del primer elemento del rango a considerar.
	 * @param indMayor El índice del último elemento del rango a considerar.
	 * @return La suma de los elementos en el rango indicado del arreglo.
	 */
	public static int sumaTotalDivisionRecursiva(int[] arr, int indMenor, int indMayor) {
		// Caso base: si el índice menor es igual al índice mayor,
		// significa que hay un solo elemento en el rango, así que se retorna su valor.
		if (indMenor == indMayor) {
			return arr[indMenor];
		}

		// Calcular el índice de la mitad del rango actual.
		int mitad = indMenor + (indMayor - indMenor + 1) / 2;

		// Llamadas recursivas para calcular la suma de las mitades izquierda y derecha.
		int mitadIzq = sumaTotalDivisionRecursiva(arr, indMenor, mitad - 1);
		int mitadDer = sumaTotalDivisionRecursiva(arr, mitad, indMayor);

		// Sumar los resultados de las dos mitades.
		int suma = mitadIzq + mitadDer;
		return suma;
	}
}

/**
 * Complejidad: La complejidad de este algoritmo es O(n), donde n es el número
 * de elementos en el arreglo. Esto se debe a que cada elemento del arreglo es
 * visitado una vez durante el proceso de suma.
 */
