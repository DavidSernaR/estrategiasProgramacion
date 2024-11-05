package programacionDinamica;

public class mochila {

    /**
     * El programa buscara la mejor alternativa para imprimir el valor máximo que se puede obtener con los
     * elementos dados y una cierta capacidad de la mochila
     */

    // Creacion de una Matriz de memorización
    private static int[][] memo;

    public static void main(String[] args) {
        int[] valor = {2, 5, 10, 14, 15};   // Valores de los elementos
        int[] peso = {1, 3, 4, 5, 7};       // Pesos de los elementos
        int capacidad = 8;                  // Capacidad de la mochila

        System.out.println("Valor máximo en la mochila: " + knapsack(valor, peso, capacidad));
    }

    // Función para resolver el problema de la mochila
    public static int knapsack(int[] valor, int[] peso, int capacidad) {
        int n = valor.length;
        memo = new int[n + 1][capacidad + 1];

        // Inicializamos la matriz de memorización con -1 para indicar valores no calculados
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacidad; j++) {
                memo[i][j] = -1;
            }
        }

        // Llamada a la función auxiliar
        return knapsackAux(valor, peso, n, capacidad);
    }

    // Función auxiliar con memorización
    private static int knapsackAux(int[] valor, int[] peso, int i, int capacidadRestante) {
        // Caso base
        if (i == 0 || capacidadRestante == 0) {
            return 0;
        }

        // Verificacion si el dato ya esta calculado
        if (memo[i][capacidadRestante] != -1) {
            return memo[i][capacidadRestante];
        }

        // Si el peso del elemento i-1 es mayor que la capacidad restante, entonces no se incluye el elemento
        if (peso[i - 1] > capacidadRestante) {
            memo[i][capacidadRestante] = knapsackAux(valor, peso, i - 1, capacidadRestante);
        } else {
            // Caso 1: no incluimos el elemento i-1
            int exclude = knapsackAux(valor, peso, i - 1, capacidadRestante);
            // Caso 2: incluimos el elemento i-1
            int include = valor[i - 1] + knapsackAux(valor, peso, i - 1, capacidadRestante - peso[i - 1]);

            // Guardamos el máximo de ambos casos en la matriz de memorización
            memo[i][capacidadRestante] = Math.max(exclude, include);
        }
        //Retorna el valor de la matriz en la posicion
        return memo[i][capacidadRestante];
    }
    
}
