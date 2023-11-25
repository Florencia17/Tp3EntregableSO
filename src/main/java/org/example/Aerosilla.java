package org.example;


import java.util.concurrent.Semaphore;


public class Aerosilla implements Runnable {
    private Semaphore sillaDisponible; // Semáforo para las sillas disponibles
    private Semaphore mutex; // Semáforo para la exclusión mutua, entre sillas
    private Semaphore mutexCima;
    private Semaphore capacidadSilla;

    private int id;

    public Aerosilla(int id, Semaphore sillaDisponible, Semaphore mutex, Semaphore capacidadSilla, Semaphore mutexCima) {
        this.id = id;
        this.sillaDisponible = sillaDisponible;
        this.mutex = mutex;
        this.capacidadSilla = capacidadSilla;
        this.mutexCima = mutexCima;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("LLega la silla: " + id);
                mutex.acquire();
                for (int i = 1; i <= 4; i++) {
                    sillaDisponible.release();
                }
                for (int i = 1; i <= 4; i++) {
                    capacidadSilla.acquire();
                }
                System.out.println("Esquiadores subiendo en la silla: " + id);
                Thread.sleep(1000);
                System.out.println("Silla: " + id + " Subiendo a la cima");
                mutex.release();
                Thread.sleep(1000);
                mutexCima.acquire();
                System.out.println("Los esquiados estan bajando de la silla: " + id);
                System.out.println("La silla " + id + " esta volviendo ");
                mutexCima.release();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}