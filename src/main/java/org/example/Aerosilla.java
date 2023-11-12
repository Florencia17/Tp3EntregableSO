package org.example;
import java.util.concurrent.Semaphore;


public class Aerosilla {
    private final int N; // Número de esquiadores
    private final int M; // Número de sillas cuádruples
    private Semaphore sillasDisponibles; // Semáforo para gestionar las sillas disponibles
    private Semaphore mutex; // Semáforo para garantizar la exclusión mutua

    public Aerosilla(int N, int M) {
        this.N = N;
        this.M = M;
        this.sillasDisponibles = new Semaphore(M * 4); // Inicializamos con capacidad para M sillas cuádruples
        this.mutex = new Semaphore(1); // Inicializamos el semáforo de exclusión mutua
    }

    public void iniciarSimulacion() {
        for (int i = 1; i <= N; i++) {
            Esquiador esquiador = new Esquiador(i, this);
            esquiador.start();
        }
    }

    public void tomarSilla() throws InterruptedException {
        sillasDisponibles.acquire(); // Espera si no hay sillas disponibles
        mutex.acquire(); // Entramos en la sección crítica
        System.out.println("Esquiador " + Thread.currentThread().getId() + " toma una silla.");
        mutex.release(); // Salimos de la sección crítica
    }

    public void liberarSilla() throws InterruptedException {
        mutex.acquire(); // Entramos en la sección crítica
        System.out.println("Esquiador " + Thread.currentThread().getId() + " libera la silla.");
        mutex.release(); // Salimos de la sección crítica
        sillasDisponibles.release(); // Liberamos una silla
    }
}
