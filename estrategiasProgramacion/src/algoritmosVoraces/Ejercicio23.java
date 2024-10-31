package algoritmosVoraces;

/**
 * La clase Item representa un objeto que tiene una cantidad, un peso y un
 * valor. Se utiliza en el contexto de la resolución del problema de la mochila
 * fraccionaria.
 */
class Item {
	int cantidad; // Cantidad disponible del ítem.
	double peso; // Peso del ítem.
	double valor; // Valor del ítem.

	/**
	 * Constructor para crear un nuevo ítem.
	 *
	 * @param cantidad La cantidad de este ítem.
	 * @param peso     El peso de este ítem.
	 * @param valor    El valor de este ítem.
	 */
	public Item(int cantidad, double peso, double valor) {
		this.cantidad = cantidad;
		this.peso = peso;
		this.valor = valor;
	}

	// Métodos de acceso (getters y setters)
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}

/**
 * La clase Ejercicio23 resuelve el problema de la mochila fraccionaria, que
 * consiste en maximizar el valor total de los ítems que se pueden transportar
 * en una mochila con capacidad limitada.
 */
public class Ejercicio23 {

	/**
	 * Método principal que se ejecuta al iniciar la aplicación. Define los ítems y
	 * la capacidad de la mochila, y llama al método mochilaFraccionaria para
	 * calcular el valor máximo que se puede obtener con los ítems disponibles.
	 *
	 * @param args Argumentos de línea de comandos (no se utilizan en este ejemplo).
	 */
	public static void main(String[] args) {
		Item[] items = { new Item(3, 210, 15), new Item(2, 230, 50), new Item(4, 150, 20), new Item(5, 40, 55),
				new Item(3, 80, 92) };
		double capacidad = 520; // Capacidad de la mochila.
		System.out.println(mochilaFraccionaria(items, capacidad)); // Imprime el valor máximo.
	}

	/**
	 * Calcula el valor máximo que se puede obtener en la mochila fraccionaria. Se
	 * ordenan los ítems en función de su valor por peso y se seleccionan hasta que
	 * se alcance la capacidad de la mochila.
	 *
	 * @param items     Arreglo de ítems disponibles.
	 * @param capacidad Capacidad máxima de la mochila.
	 * @return El valor total máximo que se puede obtener.
	 */
	public static double mochilaFraccionaria(Item[] items, double capacidad) {
		// Ordenar los ítems por valor/ peso en orden descendente (burbuja)
		for (int i = 0; i < items.length - 1; i++) {
			for (int j = 0; j < items.length - i - 1; j++) {
				double valorItemActual = items[j].getValor() / items[j].getPeso();
				double valorItemSiguiente = items[j + 1].getValor() / items[j + 1].getPeso();
				if (valorItemActual < valorItemSiguiente) {
					// Intercambiar ítems si están en el orden incorrecto.
					Item itemActual = items[j];
					items[j] = items[j + 1];
					items[j + 1] = itemActual;
				}
			}
		}

		double valorTotal = 0; // Valor total acumulado.
		double espacioDisponible = capacidad; // Espacio restante en la mochila.

		// Seleccionar ítems hasta que no haya más espacio o ítems disponibles.
		for (int i = 0; i < items.length; i++) {
			while (items[i].getCantidad() > 0 && espacioDisponible > 0) {
				if (items[i].getPeso() <= espacioDisponible) {
					valorTotal += items[i].getValor(); // Añadir el valor completo.
					espacioDisponible -= items[i].getPeso(); // Reducir el espacio disponible.
					items[i].setCantidad(items[i].getCantidad() - 1); // Reducir cantidad disponible.
				} else {
					// Añadir el valor proporcional al espacio que queda.
					valorTotal += (espacioDisponible / items[i].getPeso()) * items[i].getValor();
					espacioDisponible -= (espacioDisponible / items[i].getPeso()) * items[i].getPeso();
					items[i].setCantidad(items[i].getCantidad() - 1); // Reducir cantidad disponible.
				}
			}
		}
		return valorTotal; // Retornar el valor total acumulado.
	}
}

/**
 * Complejidad: La complejidad de este algoritmo es O(n^2 + n), donde n es el
 * número de ítems. La parte O(n^2) proviene del algoritmo de ordenamiento por
 * burbuja, y la parte O(n) se refiere a la selección de ítems hasta llenar la
 * mochila. Por lo tanto, se puede simplificar a O(n^2) en términos de
 * dominancia de términos.
 */
