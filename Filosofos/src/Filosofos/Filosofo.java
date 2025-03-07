package Filosofos;

import java.util.concurrent.Semaphore;

class Filosofo implements Runnable {
    private final int id;
    private final Semaphore tenedorIzquierdo;
    private final Semaphore tenedorDerecho;
    private final Semaphore mayordomo;

    public Filosofo(int id, Semaphore tenedorIzquierdo, Semaphore tenedorDerecho, Semaphore mayordomo) {
        this.id = id;
        this.tenedorIzquierdo = tenedorIzquierdo;
        this.tenedorDerecho = tenedorDerecho;
        this.mayordomo = mayordomo;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                mayordomo.acquire(); // Solicita permiso al mayordomo para comer
                tenedorIzquierdo.acquire();
                tenedorDerecho.acquire();
                comer();
                tenedorDerecho.release();
                tenedorIzquierdo.release();
                mayordomo.release(); // Libera el permiso del mayordomo para que otros filósofos puedan comer
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Método para simular la acción de pensar

    private void pensar() throws InterruptedException {
        System.out.println("\n El filósofo " + (id+1) + " está pensando.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    // Método para simular la acción de comer

    private void comer() throws InterruptedException {
        System.out.println("\n Filósofo " + (id+1) + " comiendo.");
        Thread.sleep((long) (Math.random() * 1000));
    }
}

