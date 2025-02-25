package Reinas;

public class nReinas {

    private int N;
    private int[] tablero;

    /*
     arrays auxiliares, los uso para determinar si hay una reina en estas posiciones
     el uso de estos arrays optimizan el código, evitando recorrer todo el array numerosas veces
    */

    private boolean[] columnas;
    private boolean[] diag1;
    private boolean[] diag2;

    private int contadorSoluciones;

    public nReinas(int N) {
        this.N = N;
        this.tablero = new int[N];
        this.columnas = new boolean[N];
        this.diag1 = new boolean[2*N-1];
        this.diag2 = new boolean[2*N-1];
        contadorSoluciones = 0;
    }

    // Metodo principal de Branch and Bound para encontrar soluciones

    private boolean branchAndBound(int i) {

        // Si hemos colocado todas las reinas, contamos esta solución
        if (i == N) {
            contadorSoluciones++;
            imprimirTablero();
            return false;  // Terminamos, no seguimos buscando más soluciones
        }

        // Intentamos colocar la reina en cada columna de la fila i

        for (int j = 0; j < N; j++) {

            // Si es seguro colocar la reina en la posición (i, j)
            if (esSeguro(i, j)) {

                colocarReina(i, j);  // Colocamos la reina en el tablero

                // Llamamos recursivamente para la siguiente fila
                if (branchAndBound(i + 1)) {
                    return true;
                }

                // Si no encontramos solución, quitamos la reina y seguimos buscando
                quitarReina(i, j);
            }
        }
        return false;  // Si no se pudo colocar la reina en ninguna columna, devolvemos falso
    }

    // Metodo que verifica si es seguro colocar una reina en la posición (i, j)

    private boolean esSeguro(int i, int j) {
        // Verificamos si la columna o las diagonales ya están ocupadas con los arrays auxiliares
        return !columnas[j] && !diag1[i-j+(N-1)] && !diag2[i + j];
    }

    private void colocarReina(int i, int j) {
        tablero[i] = j;
        columnas[j] = true;  // Marcamos la columna como ocupada
        diag1[i - j + (N - 1)] = true;  // Marcamos la diagonal 1 como ocupada
        diag2[i + j] = true;  // Marcamos la diagonal 2 como ocupada
    }

    private void quitarReina(int i, int j) {
        tablero[i] = -1;
        columnas[j] = false;  // Desmarcamos la columna como ocupada
        diag1[i - j + (N - 1)] = false;  // Desmarcamos la diagonal 1
        diag2[i + j] = false;  // Desmarcamos la diagonal 2
    }

    // Metodo que imprime el tablero con las reinas colocadas

    private void imprimirTablero() {

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {
                // Imprimimos "R" si hay una reina, de lo contrario imprimimos "-"
                System.out.print(tablero[i] == j ? " R " : " - ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void resolver() {
        branchAndBound(0);  // Llamamos a Branch and Bound comenzando desde la fila 0
        System.out.println("Número total de soluciones: " + contadorSoluciones);
    }

    public static void main(String[] args) {
        new nReinas(10).resolver();
    }
}
