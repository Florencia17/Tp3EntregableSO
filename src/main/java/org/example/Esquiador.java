package org.example;

import java.util.concurrent.Semaphore;

public class Esquiador extends Thread {
    private final int id;
    private Semaphore sillaDisponible;

    private Semaphore capacidadSilla;


    public Esquiador(int id, Semaphore sillaDisponible, Semaphore capacidadSilla) {
        this.id = id;
        this.sillaDisponible = sillaDisponible;
        this.capacidadSilla = capacidadSilla;
    }

    @Override
    public void run() {
            try {
                System.out.println("llega el esquiador: " + id + " a la fila");
                sillaDisponible.acquire(); // El esquiador toma una silla
                System.out.println("el esquiador: " + id + " subio a la silla");
                capacidadSilla.release();

                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}