package algoritmosVoraces;

/**
 * Clase que representa un objeto que puede ser incluido en la mochila.
 */
class Objeto {
	private static int generadorId = 1; // Generador de identificadores únicos
	public int id; // Identificador del objeto
	public int cantidad; // Cantidad de unidades del objeto
	public int peso; // Peso del objeto
	public int valor; // Valor del objeto

	/**
	 * Constructor que inicializa un objeto con cantidad, peso y valor.
	 * 
	 * @param cantidad Cantidad de unidades del objeto.
	 * @param peso     Peso del objeto.
	 * @param valor    Valor del objeto.
	 */
	public Objeto(int cantidad, int peso, int valor) {
		this.id = generadorId++;
		this.cantidad = cantidad;
		this.peso = peso;
		this.valor = valor;
	}

	// Constructor por defecto
	public Objeto() {
		this.id = generadorId++;
	}

	// Métodos getter y setter para los atributos
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Objeto [id=" + id + ", cantidad=" + cantidad + ", peso=" + peso + ", valor=" + valor + "]";
	}
}

/**
 * Clase que contiene los métodos para resolver el problema de la mochila.
 */
public class Ejercicio23 {

	/**
	 * Selecciona los objetos de mayor valor total hasta alcanzar el peso máximo
	 * permitido.
	 * 
	 * @param objetos    Arreglo de objetos disponibles.
	 * @param pesoMaximo Peso máximo que puede soportar la mochila.
	 * @return Arreglo de objetos seleccionados en la mochila.
	 * 
	 *         Complejidad: O(n^2), debido al algoritmo de ordenación (burbuja).
	 */
	public static Objeto[] agregarContainerMaxValor(Objeto[] objetos, int pesoMaximo) {
		Objeto[] mochila = new Objeto[objetos.length]; // Array para almacenar los objetos seleccionados
		int valorTotal = 0; // Variable para almacenar el valor total de los objetos seleccionados

		// Ordenar los objetos por valor (burbuja)
		for (int i = 0; i < objetos.length - 1; i++) {
			for (int j = 0; j < objetos.length - i - 1; j++) {
				if (objetos[j].getValor() < objetos[j + 1].getValor()) {
					Objeto temp = objetos[j]; // Intercambio de objetos para ordenar
					objetos[j] = objetos[j + 1];
					objetos[j + 1] = temp;
				}
			}
		}

		// Seleccionar objetos hasta alcanzar el peso máximo
		for (int i = 0; i < objetos.length && pesoMaximo > 0; i++) {
			if (objetos[i].getPeso() > pesoMaximo) {
				double fraccion = (double) pesoMaximo / objetos[i].getPeso(); // Calcular la fracción del objeto que se
																				// puede añadir
				int valorFraccionado = (int) (objetos[i].getValor() * fraccion); // Calcular el valor de la fracción del
																					// objeto
				Objeto objetoFraccionado = new Objeto(); // Crear un nuevo objeto fraccionado
				objetoFraccionado.setId(objetos[i].getId());
				objetoFraccionado.setCantidad(objetos[i].getCantidad());
				objetoFraccionado.setPeso(pesoMaximo);
				pesoMaximo -= pesoMaximo; // Actualizar el peso restante de la mochila
				objetoFraccionado.setValor(valorFraccionado); // Establecer el valor fraccionado
				valorTotal += valorFraccionado; // Actualizar el valor total
				mochila[i] = objetoFraccionado; // Añadir objeto fraccionado a la mochila
			} else {
				pesoMaximo -= objetos[i].getPeso(); // Actualizar el peso restante
				valorTotal += objetos[i].getValor(); // Actualizar el valor total
				mochila[i] = objetos[i]; // Añadir objeto completo a la mochila
			}
		}

		System.out.println("Valor total = " + valorTotal); // Imprimir el valor total de los objetos seleccionados
		return mochila; // Devolver la mochila con los objetos seleccionados
	}

	/**
	 * Selecciona los objetos más ligeros hasta alcanzar el peso máximo permitido.
	 * 
	 * @param objetos    Arreglo de objetos disponibles.
	 * @param pesoMaximo Peso máximo que puede soportar la mochila.
	 * @return Arreglo de objetos seleccionados en la mochila.
	 * 
	 *         Complejidad: O(n^2), debido al algoritmo de ordenación (burbuja).
	 */
	public static Objeto[] agregarContainerMinPeso(Objeto[] objetos, int pesoMaximo) {
		Objeto[] mochila = new Objeto[objetos.length]; // Array para almacenar los objetos seleccionados
		int valorTotal = 0; // Variable para almacenar el valor total de los objetos seleccionados

		// Ordenar los objetos por peso (burbuja)
		for (int i = 0; i < objetos.length - 1; i++) {
			for (int j = 0; j < objetos.length - i - 1; j++) {
				if (objetos[j].getPeso() > objetos[j + 1].getPeso()) {
					Objeto temp = objetos[j]; // Intercambio de objetos para ordenar
					objetos[j] = objetos[j + 1];
					objetos[j + 1] = temp;
				}
			}
		}

		// Seleccionar objetos hasta alcanzar el peso máximo
		for (int i = 0; i < objetos.length && pesoMaximo > 0; i++) {
			if (objetos[i].getPeso() > pesoMaximo) {
				double fraccion = (double) pesoMaximo / objetos[i].getPeso(); // Calcular la fracción del objeto que se
																				// puede añadir
				int valorFraccionado = (int) (objetos[i].getValor() * fraccion); // Calcular el valor de la fracción del
																					// objeto
				Objeto objetoFraccionado = new Objeto(); // Crear un nuevo objeto fraccionado
				objetoFraccionado.setId(objetos[i].getId());
				objetoFraccionado.setCantidad(objetos[i].getCantidad());
				objetoFraccionado.setPeso(pesoMaximo);
				pesoMaximo -= pesoMaximo; // Actualizar el peso restante de la mochila
				objetoFraccionado.setValor(valorFraccionado); // Establecer el valor fraccionado
				valorTotal += valorFraccionado; // Actualizar el valor total
				mochila[i] = objetoFraccionado; // Añadir objeto fraccionado a la mochila
			} else {
				pesoMaximo -= objetos[i].getPeso(); // Actualizar el peso restante
				valorTotal += objetos[i].getValor(); // Actualizar el valor total
				mochila[i] = objetos[i]; // Añadir objeto completo a la mochila
			}
		}

		System.out.println("Valor total = " + valorTotal); // Imprimir el valor total de los objetos seleccionados
		return mochila; // Devolver la mochila con los objetos seleccionados
	}

	/**
	 * Selecciona los objetos con el mayor valor por unidad de peso hasta alcanzar
	 * el peso máximo permitido.
	 * 
	 * @param objetos    Arreglo de objetos disponibles.
	 * @param pesoMaximo Peso máximo que puede soportar la mochila.
	 * @return Arreglo de objetos seleccionados en la mochila.
	 * 
	 *         Complejidad: O(n^2), debido al algoritmo de ordenación (burbuja).
	 */
	public static Objeto[] agregarContainerValorUnidadPeso(Objeto[] objetos, int pesoMaximo) {
		Objeto[] mochila = new Objeto[objetos.length]; // Array para almacenar los objetos seleccionados
		int valorTotal = 0; // Variable para almacenar el valor total de los objetos seleccionados

		// Ordenar los objetos por valor por unidad de peso (burbuja)
		for (int i = 0; i < objetos.length - 1; i++) {
			for (int j = 0; j < objetos.length - i - 1; j++) {
				if ((double) objetos[j].getValor() / objetos[j].getPeso() < (double) objetos[j + 1].getValor()
						/ objetos[j + 1].getPeso()) {
					Objeto temp = objetos[j]; // Intercambio de objetos para ordenar
					objetos[j] = objetos[j + 1];
					objetos[j + 1] = temp;
				}
			}
		}

		// Seleccionar objetos hasta alcanzar el peso máximo
		for (int i = 0; i < objetos.length && pesoMaximo > 0; i++) {
			if (objetos[i].getPeso() > pesoMaximo) {
				double fraccion = (double) pesoMaximo / objetos[i].getPeso(); // Calcular la fracción del objeto que se
																				// puede añadir
				int valorFraccionado = (int) (objetos[i].getValor() * fraccion); // Calcular el valor de la fracción del
																					// objeto
				Objeto objetoFraccionado = new Objeto(); // Crear un nuevo objeto fraccionado
				objetoFraccionado.setId(objetos[i].getId());
				objetoFraccionado.setCantidad(objetos[i].getCantidad());
				objetoFraccionado.setPeso(pesoMaximo);
				pesoMaximo -= pesoMaximo; // Actualizar el peso restante de la mochila
				objetoFraccionado.setValor(valorFraccionado); // Establecer el valor fraccionado
				valorTotal += valorFraccionado; // Actualizar el valor total
				mochila[i] = objetoFraccionado; // Añadir objeto fraccionado a la mochila
			} else {
				pesoMaximo -= objetos[i].getPeso(); // Actualizar el peso restante
				valorTotal += objetos[i].getValor(); // Actualizar el valor total
				mochila[i] = objetos[i]; // Añadir objeto completo a la mochila
			}
		}

		System.out.println("Valor total = " + valorTotal); // Imprimir el valor total de los objetos seleccionados
		return mochila; // Devolver la mochila con los objetos seleccionados
	}

	/**
	 * Método principal para probar las funcionalidades de la mochila.
	 * 
	 * @param args Argumentos de línea de comandos.
	 */
	public static void main(String[] args) {
		Objeto[] objetos = { new Objeto(3, 210, 15), new Objeto(2, 230, 50), new Objeto(4, 150, 20),
				new Objeto(5, 40, 55), new Objeto(3, 80, 92) };

		int pesoMaximo = 520; // Peso máximo que puede soportar la mochila

		// Prueba de agregarContainerMaxValor
		System.out.println("Heurística 1: Seleccionar el objeto más valioso");
		Objeto[] mochilaMaxValor = agregarContainerMaxValor(objetos, pesoMaximo);
		for (Objeto obj : mochilaMaxValor) {
			if (obj != null) {
				System.out.println(obj);
			}
		}

		// Prueba de agregarContainerMinPeso
		System.out.println("\nHeurística 2: Seleccionar el objeto más ligero");
		Objeto[] mochilaMinPeso = agregarContainerMinPeso(objetos, pesoMaximo);
		for (Objeto obj : mochilaMinPeso) {
			if (obj != null) {
				System.out.println(obj);
			}
		}

		// Prueba de agregarContainerValorUnidadPeso
		System.out.println("\nHeurística 3: Seleccionar el objeto cuyo valor por unidad de peso sea el mayor posible");
		Objeto[] mochilaValorUnidadPeso = agregarContainerValorUnidadPeso(objetos, pesoMaximo);
		for (Objeto obj : mochilaValorUnidadPeso) {
			if (obj != null) {
				System.out.println(obj);
			}
		}
	}
}

/**
 * Complejidad: La complejidad de este algoritmo es O(n^2 + n), donde n es el
 * número de ítems. La parte O(n^2) proviene del algoritmo de ordenamiento por
 * burbuja, y la parte O(n) se refiere a la selección de ítems hasta llenar la
 * mochila. Por lo tanto, se puede simplificar a O(n^2) en términos de
 * dominancia de términos.
 */