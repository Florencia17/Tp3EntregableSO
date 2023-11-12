package org.example;
import java.util.concurrent.Semaphore;


public class Aerosilla {
    private final int N; // Número de esquiadores
    private final int M; // Número de sillas
    private Semaphore sillasDisponibles; // Semáforo para las sillas disponibles
    private Semaphore mutex; // Semáforo para la exclusión mutua

    public Aerosilla(int N, int M) {
        this.N = N;
        this.M = M;
        this.sillasDisponibles = new Semaphore(M * 4); // capacidad para M sillas que son cuadruples
        this.mutex = new Semaphore(1);
    }

    public void iniciarSimulacion() {
        for (int i = 1; i <= N; i++) {
            Esquiador esquiador = new Esquiador(i, this);
            esquiador.start();
        }
    }

    public void tomarSilla() throws InterruptedException {
        sillasDisponibles.acquire();
        mutex.acquire();
        System.out.println("Esquiador " + Thread.currentThread().getId() + " toma una silla.");
        mutex.release();
    }

    public void liberarSilla() throws InterruptedException {
        mutex.acquire();
        System.out.println("Esquiador " + Thread.currentThread().getId() + " libera la silla.");
        mutex.release();
        sillasDisponibles.release();
    }
}
