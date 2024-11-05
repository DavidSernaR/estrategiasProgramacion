package programacionDinamica;

public class sucecionLucas {

    public static void main(String[] args) {
        int n = 10; // Cambia este valor para obtener otro término de la sucesión
        System.out.println("El término " + n + " de la sucesión de Lucas es: " + lucasIterativo(n));
    }

    // Método iterativo para calcular el n-ésimo término de la sucesión de Lucas
    public static int lucasIterativo(int n) {
        if (n == 0) {
            return 2;
        } else if (n == 1) {
            return 1;
        }

        int prev2 = 2; // L(0)
        int prev1 = 1; // L(1)
        int current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }
}
