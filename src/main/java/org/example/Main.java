package org.example;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore sillaDisponible = new Semaphore(0);
        Semaphore mutex = new Semaphore(1);
        Semaphore capacidadSilla = new Semaphore(0);
        Semaphore mutexCima = new Semaphore(1);

        for (int i= 1; i <=12; i++){
            Esquiador esquiador= new Esquiador(i, sillaDisponible, capacidadSilla);
            Thread esqui = new Thread(esquiador);
            esqui.start();
        }

        for(int i = 1; i <= 2; i++)  {
            Aerosilla aerosilla= new Aerosilla(i,sillaDisponible, mutex, capacidadSilla, mutexCima);
            Thread silla = new Thread(aerosilla);
            silla.start();
      }



    }
}