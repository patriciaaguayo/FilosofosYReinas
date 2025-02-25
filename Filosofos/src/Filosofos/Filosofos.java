package Filosofos;

import java.util.concurrent.Semaphore;

public class Filosofos {
    private static final int NUM_FILOSOFOS = 5;// Permite solo 5 filósofos
    private static final Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS]; // Permite solo 1 filósofo pensar
    private static final Semaphore[] tenedores = new Semaphore[NUM_FILOSOFOS]; // Permite solo 1 filósofo comer
    private static final Semaphore mayordomo = new Semaphore(NUM_FILOSOFOS - 1); // Permite solo 4 filósofos comiendo

    public static void main(String[] args) {
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            tenedores[i] = new Semaphore(1); // Permite solo 1 filósofo comer
        }
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            filosofos[i] = new Filosofo(i, tenedores[i], tenedores[(i + 1) % NUM_FILOSOFOS], mayordomo);
            new Thread(filosofos[i]).start();
        }
    }
}
