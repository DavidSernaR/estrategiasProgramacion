package programacionDinamica;

public class coeficientesBinomiales {
    /**
     * El coeficiente de permutación 𝑃(𝑛,𝑘)(también llamado variación sin repetición) 
     * es una fórmula matemática que calcula el número de formas en que se pueden organizar 
     * k elementos distintos de un conjunto de n elementos.
     * @param args
     */

    public static void main(String[] args) {
        int n = 5;  // Número total de elementos
        int k = 2;  // Número de elementos a elegir
        System.out.println("C(" + n + ", " + k + ") = " + coeficienteBinomial(n, k));
    }


    /**
     * Se hace el llamado para realizar las operaciones 
     * @param n numero total de elementos
     * @param k Numero de elementos a elegir
     * @return 
     */
    public static int coeficienteBinomial(int n, int k) {
        int[][] C = new int[n + 1][k + 1];

        // Construyendo la tabla desde abajo hacia arriba
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                // Caso base: C(i, 0) = 1 y C(i, i) = 1
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                } else {
                    // Usando la relación de recurrencia
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
            }
        }

        return C[n][k]; // El valor buscado es C(n, k)
    }

}
