package algoritmosVoraces;

/**
 * La clase Conexion representa una conexión entre dos municipios con un costo
 * asociado.
 */
class Conexion {
	int municipio1; // Primer municipio de la conexión.
	int municipio2; // Segundo municipio de la conexión.
	int costo; // Costo de la conexión.

	/**
	 * Constructor para crear una nueva conexión.
	 *
	 * @param municipio1 El primer municipio.
	 * @param municipio2 El segundo municipio.
	 * @param costo      El costo de la conexión.
	 */
	public Conexion(int municipio1, int municipio2, int costo) {
		this.municipio1 = municipio1;
		this.municipio2 = municipio2;
		this.costo = costo;
	}
}

/**
 * La clase Ejercicio25 implementa el algoritmo de Kruskal para encontrar el
 * árbol de expansión mínimo en un conjunto de municipios y conexiones.
 */
public class Ejercicio25 {

	public static int[] conjunto; // Conjunto para el algoritmo de unión-find.

	/**
	 * Inicializa el conjunto de municipios, cada uno en su propio conjunto.
	 *
	 * @param n Número total de municipios.
	 */
	public static void inicializarConjunto(int n) {
		conjunto = new int[n];
		for (int i = 0; i < n; i++) {
			conjunto[i] = i; // Cada municipio es su propio padre inicialmente.
		}
	}

	/**
	 * Encuentra el representante de un municipio en el conjunto.
	 *
	 * @param municipio Municipio del que se desea encontrar el representante.
	 * @return El representante del municipio.
	 */
	public static int encontrar(int municipio) {
		if (conjunto[municipio] != municipio) {
			conjunto[municipio] = encontrar(conjunto[municipio]); // Path compression.
		}
		return conjunto[municipio];
	}

	/**
	 * Une dos municipios en el mismo conjunto.
	 *
	 * @param municipio1 El primer municipio a unir.
	 * @param municipio2 El segundo municipio a unir.
	 */
	public static void unir(int municipio1, int municipio2) {
		int raiz1 = encontrar(municipio1);
		int raiz2 = encontrar(municipio2);
		if (raiz1 != raiz2) {
			conjunto[raiz2] = raiz1; // Unión de los conjuntos.
		}
	}

	/**
	 * Aplica el algoritmo de Kruskal para encontrar el árbol de expansión mínimo.
	 *
	 * @param n          Número de municipios.
	 * @param conexiones Conexiones disponibles entre municipios.
	 * @return Un arreglo de conexiones que forman la red óptima.
	 */
	public static Conexion[] kruskal(int n, Conexion[] conexiones) {
		inicializarConjunto(n); // Inicializa el conjunto de municipios.

		// Ordenar las conexiones por costo (burbuja)
		for (int i = 0; i < conexiones.length - 1; i++) {
			for (int j = 0; j < conexiones.length - i - 1; j++) {
				if (conexiones[i].costo > conexiones[j].costo) {
					// Intercambiar conexiones si están en el orden incorrecto.
					Conexion temp = conexiones[i];
					conexiones[i] = conexiones[j];
					conexiones[j] = temp;
				}
			}
		}

		Conexion[] redOptima = new Conexion[n - 1]; // Arreglo para la red óptima.
		int contadorConexiones = 0; // Contador de conexiones en la red óptima.

		// Construir la red óptima utilizando el algoritmo de Kruskal.
		for (Conexion conexion : conexiones) {
			if (encontrar(conexion.municipio1) != encontrar(conexion.municipio2)) {
				redOptima[contadorConexiones++] = conexion; // Agregar conexión a la red óptima.
				unir(conexion.municipio1, conexion.municipio2); // Unir municipios.

				if (contadorConexiones == n - 1) {
					break; // Se ha alcanzado el número máximo de conexiones en la red óptima.
				}
			}
		}
		return redOptima; // Retornar la red óptima.
	}

	/**
	 * Método principal que se ejecuta al iniciar la aplicación. Define los
	 * municipios y las conexiones disponibles, y llama al método kruskal para
	 * obtener la red óptima.
	 *
	 * @param args Argumentos de línea de comandos (no se utilizan en este ejemplo).
	 */
	public static void main(String[] args) {
		int municipios = 5; // Número de municipios.
		Conexion[] conexiones = { new Conexion(0, 1, 10000), new Conexion(0, 2, 30000), new Conexion(1, 2, 20000),
				new Conexion(1, 3, 25000), new Conexion(2, 3, 15000), new Conexion(3, 4, 20000) };

		Ejercicio25.inicializarConjunto(municipios); // Inicializar el conjunto.
		Conexion[] redOptima = Ejercicio25.kruskal(municipios, conexiones); // Obtener la red óptima.
		System.out.println("Conexiones en la red de fibra óptica mínima:");
		for (Conexion conexion : redOptima) {
			System.out.println("Municipio " + conexion.municipio1 + " - Municipio " + conexion.municipio2
					+ " con costo " + conexion.costo + " pesos colombianos");
		}
	}
}

/**
 * Complejidad: La complejidad del algoritmo es logarítmica debido a la
 * combinación del algoritmo de ordenamiento y las operaciones de unión y
 * encontrar en la estructura de datos. En particular, el algoritmo de Kruskal
 * tiene una complejidad de O(n log n), donde n es el número de conexiones. En
 * la práctica, esto significa que el rendimiento del algoritmo es muy eficiente
 * para conjuntos de datos grandes.
 */
