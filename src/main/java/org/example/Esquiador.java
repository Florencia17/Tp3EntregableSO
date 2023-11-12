package org.example;

public class Esquiador extends Thread {
    private final int id;
    private final Aerosilla silla;


    public Esquiador(int id, Aerosilla silla) {
        this.id = id;
        this.silla = silla;

    }

    @Override
    public void run() {
        while (true) {
            try {
                silla.tomarSilla(); // El esquiador toma una silla
                Thread.sleep(1000);
                silla.liberarSilla(); // El esquiador libera la silla en la cima
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}